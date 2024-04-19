import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddNewQuestion extends JFrame implements ActionListener {
    private JTextField idField;
    private JTextField nameField;
    private JTextField opt1Field;
    private JTextField opt2Field;
    private JTextField opt3Field;
    private JTextField opt4Field;
    private JTextField answerField;

    public AddNewQuestion() {
        setTitle("Add New Question");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2));

        JLabel idLabel = new JLabel("ID:");
        idField = new JTextField();
        panel.add(idLabel);
        panel.add(idField);

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        panel.add(nameLabel);
        panel.add(nameField);

        JLabel opt1Label = new JLabel("Opt1:");
        opt1Field = new JTextField();
        panel.add(opt1Label);
        panel.add(opt1Field);

        JLabel opt2Label = new JLabel("Opt2:");
        opt2Field = new JTextField();
        panel.add(opt2Label);
        panel.add(opt2Field);

        JLabel opt3Label = new JLabel("Opt3:");
        opt3Field = new JTextField();
        panel.add(opt3Label);
        panel.add(opt3Field);

        JLabel opt4Label = new JLabel("Opt4:");
        opt4Field = new JTextField();
        panel.add(opt4Label);
        panel.add(opt4Field);

        JLabel answerLabel = new JLabel("Answer:");
        answerField = new JTextField();
        panel.add(answerLabel);
        panel.add(answerField);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(this);
        panel.add(addButton);

        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add")) {
            addData();
        }
    }

    public void addData() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qems", "root", "root");

            String id = idField.getText();
            String name = nameField.getText();
            String opt1 = opt1Field.getText();
            String opt2 = opt2Field.getText();
            String opt3 = opt3Field.getText();
            String opt4 = opt4Field.getText();
            String answer = answerField.getText();

            String query = "INSERT INTO question(id,name,opt1,opt2,opt3,opt4,answer) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, opt1);
            preparedStatement.setString(4, opt2);
            preparedStatement.setString(5, opt3);
            preparedStatement.setString(6, opt4);
            preparedStatement.setString(7, answer);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            JOptionPane.showMessageDialog(this, "Question added successfully!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error occurred while adding question: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AddNewQuestion addNewQuestionUI = new AddNewQuestion();
            addNewQuestionUI.setVisible(true);
        });
    }
}

