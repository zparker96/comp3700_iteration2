import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {
    private LoginView loginView;
    private DataAdapter dataAdapter;
    private User user;

    public LoginController(LoginView loginView, DataAdapter dataAdapter) {
        this.dataAdapter = dataAdapter;
        this.loginView = loginView;

        loginView.getBtnLogin().addActionListener(this);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginView.getBtnLogin())
            loadUser();
    }

    private void loadUser() {
        String userName = null;
        String password = null;

        try {
            userName = loginView.getTxtUsername().getText().trim();
            password = loginView.getTxtPassword().getText().trim();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid Username or Password.");
            return;
        }

        user = dataAdapter.loadUser(userName);

        if (user == null || !user.getPassword().equals(password)) {
            JOptionPane.showMessageDialog(null, "Wrong Username or Password.");
            return;
        }
        else {
            if (user.getRole()) {
                Application.getInstance().getManagerView().setVisible(true);
            }
            else {
                Application.getInstance().getCashierView().setVisible(true);
            }
            return;
        }
    }
}