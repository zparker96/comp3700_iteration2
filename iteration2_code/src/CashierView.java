import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CashierView extends JFrame {

    private JButton btnCheckout;
    private JButton btnChangePassword;
    private JButton btnChangePhoto;

    public CashierView() {
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(400, 300);
        //manage product, create new user, view report, change password, change profile photo
        btnCheckout = new JButton("Checkout");
        btnCheckout.setPreferredSize(new Dimension(150, 40));

        btnChangePassword = new JButton("Change Password");
        btnChangePassword.setPreferredSize(new Dimension(150, 40));
        btnChangePhoto = new JButton("Change Profile Photo");
        btnChangePhoto.setPreferredSize(new Dimension(180, 40));


        JLabel title = new JLabel("Cashier Home");
        title.setFont(new Font("Sans Serif", Font.BOLD, 24));
        JPanel panelTitle = new JPanel();
        panelTitle.setPreferredSize(new Dimension(200, 0));
        panelTitle.add(title);
        this.getContentPane().add(panelTitle);

        JPanel panelButton = new JPanel();
        panelButton.add(btnCheckout);

        panelButton.add(btnChangePassword);
        panelButton.add(btnChangePhoto);

        this.getContentPane().add(panelButton);

        btnCheckout.addActionListener(new ActionListener() { // when controller is simple, we can declare it on the fly
            public void actionPerformed(ActionEvent e) {
                Application.getInstance().getCheckoutScreen().setVisible(true);
            }
        });
        btnChangePassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public JButton getBtnChangePassword() {
        return btnChangePassword;
    }
    public JButton getBtnChangePhoto() { return btnChangePhoto; }

}

