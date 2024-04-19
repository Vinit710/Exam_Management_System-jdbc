import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginAdmin extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public loginAdmin() {
        setTitle("Admin Login");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        panel.add(usernameLabel);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        panel.add(passwordLabel);
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        panel.add(loginButton);

        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Login")) {
            login();
        }
    }

    public void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.equals("qems") && password.equals("admin")) {
            JOptionPane.showMessageDialog(this, "Login successful! Welcome, Admin.");
            adminHome adminHome = new adminHome();
            adminHome.setVisible(true);
            adminHome.initComponents();
            dispose(); // Close the login window
        } else {
            JOptionPane.showMessageDialog(this, "Incorrect username or password.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            loginAdmin loginAdminUI = new loginAdmin();
            loginAdminUI.setVisible(true);
        });
    }
}
