import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserController implements ActionListener {
    private UserView userView;
    private DataAdapter dataAdapter;
    private User user = new User();
    private String userName;

    public UserController(UserView userView, DataAdapter dataAdapter) {
        this.dataAdapter = dataAdapter;
        this.userView = userView;


        userView.getBtnAddUser().addActionListener(this);

    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == userView.getBtnAddUser())
            addUser();
    }


    private void addUser() {
        // TODO: make "default" password
        userName = userView.getTxtUserName().getText().trim();

        if (userName.length() == 0) {
            JOptionPane.showMessageDialog(null, "Invalid username! Please provide a non-empty username!");
            return;
        }
        
        String displayName = userView.getTxtDisplayName().getText().trim();

        if (displayName.length() == 0) {
            JOptionPane.showMessageDialog(null, "Invalid display name! Please provide a non-empty display name!");
            return;
        }

        int userID;
        try {
            userID = Integer.parseInt(userView.getTxtUserID().getText());
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid user ID! Please provide a valid user ID!");
            return;
        }

        // Setting the password as default
        String password = "password";

        if (password.length() == 0) {
            JOptionPane.showMessageDialog(null, "Invalid password! Please provide a non-empty password!");
            return;
        }

        boolean role = false;
        if (userView.getBoxIsManager().isSelected()) {
            role = true;
        }


        // Done all validations! Make an object for this product!

        user.setUserName(userName);
        user.setDisplayName(displayName);
        user.setUserID(userID);
        user.setPassword(password);
        user.setRole(role);

        // Store the user to the database

        dataAdapter.addUser(user);
    }

}