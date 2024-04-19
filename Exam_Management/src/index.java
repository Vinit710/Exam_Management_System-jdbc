import javax.swing.*;



public class index extends JFrame {

    public index() {
        initComponents();
    }

    public void initComponents() {
        setTitle("Exam Management System");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Create buttons for options
        JButton studentButton = new JButton("STUDENT");
        JButton adminButton = new JButton("ADMIN");
        JButton exitButton = new JButton("EXIT");

        // Set layout to null for absolute positioning
        setLayout(null);

        // Set positions and sizes for buttons
        studentButton.setBounds(50, 30, 100, 30);
        adminButton.setBounds(160, 30, 100, 30);
        exitButton.setBounds(270, 30, 100, 30);

        // Add buttons to the frame
        add(studentButton);
        add(adminButton);
        add(exitButton);

        // Action listener for student button
        studentButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "You selected STUDENT.");
            // Add code for student functionality here
        });

        // Action listener for admin button
        adminButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "You selected ADMIN.");
            loginAdmin loginAdmin = new loginAdmin();
            loginAdmin.setVisible(true);
        });

        // Action listener for exit button
        exitButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Exiting application...");
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            index index = new index();
            index.setVisible(true);
        });
    }
}
