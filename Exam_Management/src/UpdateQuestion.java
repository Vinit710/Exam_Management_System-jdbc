import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateQuestion extends JFrame implements ActionListener {
    private JTextField idField;
    private JTextField nameField;
    private JTextField opt1Field;
    private JTextField opt2Field;
    private JTextField opt3Field;
    private JTextField opt4Field;
    private JTextField answerField;

    public UpdateQuestion() {
        setTitle("Update Question");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2));

        JLabel idLabel = new JLabel("Question ID:");
        idField = new JTextField();
        panel.add(idLabel);
        panel.add(idField);

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        panel.add(nameLabel);
        panel.add(nameField);

        JLabel opt1Label = new JLabel("Option 1:");
        opt1Field = new JTextField();
        panel.add(opt1Label);
        panel.add(opt1Field);

        JLabel opt2Label = new JLabel("Option 2:");
        opt2Field = new JTextField();
        panel.add(opt2Label);
        panel.add(opt2Field);

        JLabel opt3Label = new JLabel("Option 3:");
        opt3Field = new JTextField();
        panel.add(opt3Label);
        panel.add(opt3Field);

        JLabel opt4Label = new JLabel("Option 4:");
        opt4Field = new JTextField();
        panel.add(opt4Label);
        panel.add(opt4Field);

        JLabel answerLabel = new JLabel("Answer:");
        answerField = new JTextField();
        panel.add(answerLabel);
        panel.add(answerField);

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(this);
        panel.add(updateButton);

        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Update")) {
            updateQuestion();
        }
    }

    public void updateQuestion() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qems", "root", "root");

            String id = idField.getText();
            String name = nameField.getText();
            String opt1 = opt1Field.getText();
            String opt2 = opt2Field.getText();
            String opt3 = opt3Field.getText();
            String opt4 = opt4Field.getText();
            String answer = answerField.getText();

            String updateQuery = "UPDATE question SET name = ?, opt1 = ?, opt2 = ?, opt3 = ?, opt4 = ?, answer = ? WHERE id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setString(1, name);
            updateStatement.setString(2, opt1);
            updateStatement.setString(3, opt2);
            updateStatement.setString(4, opt3);
            updateStatement.setString(5, opt4);
            updateStatement.setString(6, answer);
            updateStatement.setString(7, id);

            int rowsAffected = updateStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Question updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update question.");
            }

            updateStatement.close();
            connection.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error occurred while updating question: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UpdateQuestion updateQuestionUI = new UpdateQuestion();
            updateQuestionUI.setVisible(true);
        });
    }
}
