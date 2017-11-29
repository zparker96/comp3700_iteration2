import javax.swing.*;
import java.awt.*;

public class UserView extends JFrame{
    private JTextField txtUserName  = new JTextField(30);
    private JTextField txtDisplayName  = new JTextField(30);
    private JTextField txtUserID  = new JTextField(30);
    private JTextField txtPassword  = new JTextField(30);

    private JCheckBox boxIsManager = new JCheckBox("Is Manager?");

    private JButton btnAddUser = new JButton("Add User");

    public UserView() {
        this.setTitle("Add a New User");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setSize(500, 300);

        JPanel panelProductID = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelProductID.add(new JLabel("Username: "));
        panelProductID.add(txtUserName);
       // txtProductID.setHorizontalAlignment(JTextField.RIGHT);
        this.getContentPane().add(panelProductID);

        JPanel panelProductName = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelProductName.add(new JLabel("Display Name: "));
        panelProductName.add(txtDisplayName);
        this.getContentPane().add(panelProductName);

        JPanel panelProductInfo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelProductInfo.add(new JLabel("User ID: "));
        panelProductInfo.add(txtUserID);
       // txtProductPrice.setHorizontalAlignment(JTextField.RIGHT);
        this.getContentPane().add(panelProductInfo);

//        JPanel panelPassword = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        panelPassword.add(new JLabel("Password: "));
//        panelPassword.add(txtPassword);
       // txtProductQuantity.setHorizontalAlignment(JTextField.RIGHT);
//        this.getContentPane().add(panelPassword);

        JPanel panelIsManager = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelIsManager.add(boxIsManager);
        this.getContentPane().add(panelIsManager);

        JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelButton.add(btnAddUser);
        this.getContentPane().add(panelButton);
    }

    public JButton getBtnAddUser() {
        return btnAddUser;
    }

    public JTextField getTxtUserName() {
        return txtUserName;
    }

    public JTextField getTxtDisplayName() {
        return txtDisplayName;
    }

    public JTextField getTxtUserID() {
        return txtUserID;
    }

    public JTextField getTxtPassword() {
        return txtPassword;
    }

    public JCheckBox getBoxIsManager() { return boxIsManager; }


}
