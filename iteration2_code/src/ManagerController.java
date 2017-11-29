import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerController implements ActionListener {
    private ManagerView managerView;
    private DataAdapter dataAdapter;
    private LoginView loginView;
    private User user;

    // TODO: make a user report function

    public ManagerController(ManagerView managerView, DataAdapter dataAdapter, LoginView loginView) {
        this.dataAdapter = dataAdapter;
        this.managerView = managerView;
        this.loginView = loginView;

        managerView.getBtnChangePassword().addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == managerView.getBtnChangePassword())
            changePassword();
    }

    public void changePassword() {
        String userName = null;
        userName = loginView.getTxtUsername().getText().trim();
        user = dataAdapter.loadUser(userName);
        String newPassword = JOptionPane.showInputDialog(null, "What is your new password? ");
        user.setPassword(newPassword);
        JOptionPane.showMessageDialog(null, user.getPassword());
        dataAdapter.changePassword(user);
    }
}