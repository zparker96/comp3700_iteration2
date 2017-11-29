import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CashierController implements ActionListener {
    private CashierView cashierView;
    private DataAdapter dataAdapter;
    private LoginView loginView;
    private User user;
    //private ImageView imageView;

    public CashierController(CashierView cashierView, DataAdapter dataAdapter, LoginView loginView) { //, ImageView imageView) {
        this.dataAdapter = dataAdapter;
        this.cashierView = cashierView;
        this.loginView = loginView;
        //this.imageView = imageView;

        cashierView.getBtnChangePassword().addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cashierView.getBtnChangePassword())
            changePassword();
       // if (e.getSource() == cashierView.getBtnChangePhoto())
            //Application.getInstance().getImageView().setVisible(true);
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

//    public void setImage() {
//        JPanel panel = new JPanel();
//        ImageIcon icon = new ImageIcon("image.jpg");
//        JLabel label = new JLabel();
//        label.setIcon(icon);
//        panel.add(label);
//    }
}