import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteQuestion extends JFrame implements ActionListener {
    private JTextField idField;

    public DeleteQuestion() {
        setTitle("Delete Question");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        JLabel idLabel = new JLabel("Question ID:");
        idField = new JTextField();
        panel.add(idLabel);
        panel.add(idField);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this);
        panel.add(deleteButton);

        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Delete")) {
            deleteQuestion();
        }
    }

    public void deleteQuestion() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qems", "root", "root");

            String id = idField.getText();

            // Check if the question exists in the database
            String selectQuery = "SELECT * FROM question WHERE id = ?";
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            selectStatement.setString(1, id);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                // Delete the question from the database
                String deleteQuery = "DELETE FROM question WHERE id = ?";
                PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
                deleteStatement.setString(1, id);

                int rowsAffected = deleteStatement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Question with ID " + id + " deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete question with ID " + id);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Question with ID " + id + " not found.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error occurred while deleting data: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DeleteQuestion deleteQuestionUI = new DeleteQuestion();
            deleteQuestionUI.setVisible(true);
        });
    }
}
