import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewAllQuestion extends JFrame {
    private JTextArea textArea;

    public ViewAllQuestion() {
        setTitle("View All Questions");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        initComponents();
        displayQuestions();
    }

    public void initComponents() {
        JPanel panel = new JPanel(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
    }

    public void displayQuestions() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Establish database connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qems", "root", "root");

            // SQL query to retrieve all questions
            String query = "SELECT * FROM question";
            preparedStatement = connection.prepareStatement(query);

            // Execute the query
            resultSet = preparedStatement.executeQuery();

            // Process the results and append to the text area
            StringBuilder stringBuilder = new StringBuilder();
            while (resultSet.next()) {
                // Retrieve question details from the result set
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String opt1 = resultSet.getString("opt1");
                String opt2 = resultSet.getString("opt2");
                String opt3 = resultSet.getString("opt3");
                String opt4 = resultSet.getString("opt4");
                String answer = resultSet.getString("answer");

                // Append question details to the text area
                stringBuilder.append("ID: ").append(id).append("\n");
                stringBuilder.append("Name: ").append(name).append("\n");
                stringBuilder.append("Opt1: ").append(opt1).append("\n");
                stringBuilder.append("Opt2: ").append(opt2).append("\n");
                stringBuilder.append("Opt3: ").append(opt3).append("\n");
                stringBuilder.append("Opt4: ").append(opt4).append("\n");
                stringBuilder.append("Answer: ").append(answer).append("\n\n");
            }
            textArea.setText(stringBuilder.toString()); // Set text to the text area
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error occurred while fetching questions: " + e.getMessage());
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error occurred while closing resources: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ViewAllQuestion viewAllQuestionsUI = new ViewAllQuestion();
            viewAllQuestionsUI.setVisible(true);
        });
    }
}
