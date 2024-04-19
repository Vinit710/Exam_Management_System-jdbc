import javax.swing.*;

public class adminHome extends JFrame {

    public adminHome() {
        initComponents();
    }

    public void initComponents() {
        setTitle("Admin Home");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Create buttons for options
        JButton addQuestionButton = new JButton("Add New Question");
        JButton updateQuestionButton = new JButton("Update Question");
        JButton viewQuestionsButton = new JButton("View All Questions");
        JButton deleteQuestionButton = new JButton("Delete Question");
        JButton viewResultsButton = new JButton("View All Student Results");
        JButton logoutButton = new JButton("Logout");
        JButton exitButton = new JButton("Exit");

        // Set layout to null for absolute positioning
        setLayout(null);

        // Set positions and sizes for buttons
        addQuestionButton.setBounds(50, 30, 200, 30);
        updateQuestionButton.setBounds(50, 70, 200, 30);
        viewQuestionsButton.setBounds(50, 110, 200, 30);
        deleteQuestionButton.setBounds(50, 150, 200, 30);
        viewResultsButton.setBounds(50, 190, 200, 30);
        logoutButton.setBounds(50, 230, 200, 30);
        exitButton.setBounds(50, 270, 200, 30);

        // Add buttons to the frame
        add(addQuestionButton);
        add(updateQuestionButton);
        add(viewQuestionsButton);
        add(deleteQuestionButton);
        add(viewResultsButton);
        add(logoutButton);
        add(exitButton);

        // Action listener for add question button
        addQuestionButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "You selected: Add New Question");
            AddNewQuestion addNewQuestion = new AddNewQuestion();
            addNewQuestion.setVisible(true);
            addNewQuestion.addData();
        });

        // Action listener for update question button
        updateQuestionButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "You selected: Update Question");
            UpdateQuestion updateQuestion = new UpdateQuestion();
            updateQuestion.setVisible(true);
            updateQuestion.updateQuestion();
        });

        // Action listener for view questions button
        viewQuestionsButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "You selected: View All Questions");
            ViewAllQuestion viewAllQuestion = new ViewAllQuestion();
            viewAllQuestion.setVisible(true);
            viewAllQuestion.displayQuestions();
        });

        // Action listener for delete question button
        deleteQuestionButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "You selected: Delete Question");
            DeleteQuestion deleteQuestion = new DeleteQuestion();
            deleteQuestion.setVisible(true);
            deleteQuestion.deleteQuestion();
        });

        // Action listener for view results button
        viewResultsButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "You selected: View All Student Results");
            // Add code for viewing all student results here
        });

        // Action listener for logout button
        logoutButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Logging out...");
            // Add code for logout here
        });

        // Action listener for exit button
        exitButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Exiting application...");
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            adminHome adminHome = new adminHome();
            adminHome.setVisible(true);
        });
    }
}

