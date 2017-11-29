import javax.swing.*;

public class LoginView extends JFrame{

    private JTextField txtUsername  = new JTextField(20);
    private JTextField txtPassword  = new JTextField(20);
    private JButton btnLogin = new JButton("Login");

    public LoginView() {

        this.setTitle("LoginView");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setSize(350, 200);

        JPanel panelUsername = new JPanel();
        panelUsername.add(new JLabel("Username: "));
        panelUsername.add(txtUsername);
        this.getContentPane().add(panelUsername);

        JPanel panelPassword = new JPanel();
        txtPassword = new JPasswordField(20);
        panelPassword.add(new JLabel("Password: "));
        panelPassword.add(txtPassword);
        this.getContentPane().add(panelPassword);

        JPanel panelButton = new JPanel();
        panelButton.add(btnLogin);
        this.getContentPane().add(panelButton);
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }
    public JTextField getTxtUsername() {
        return txtUsername;
    }
    public JTextField getTxtPassword() {
        return txtPassword;
    }
}