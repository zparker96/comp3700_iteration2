import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerView extends JFrame {

    private JButton btnManage;
    private JButton btnCreateUser;
    private JButton btnViewReport;
    private JButton btnChangePassword;
    private JButton btnChangePhoto;
    private JLabel title;

    public ManagerView() {
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(400, 300);
        //manage product, create new user, view report, change password, change profile photo
        btnManage = new JButton("Manage Product");
        btnManage.setPreferredSize(new Dimension(150, 40));
        btnCreateUser = new JButton("Create New User");
        btnCreateUser.setPreferredSize(new Dimension(150, 40));
        btnViewReport = new JButton("View Report");
        btnViewReport.setPreferredSize(new Dimension(150, 40));
        btnChangePassword = new JButton("Change Password");
        btnChangePassword.setPreferredSize(new Dimension(150, 40));
        btnChangePhoto = new JButton("Change Profile Photo");
        btnChangePhoto.setPreferredSize(new Dimension(180, 40));

        JLabel title = new JLabel("Manager Home");
        title.setFont(new Font("Sans Serif", Font.BOLD, 24));
        JPanel panelTitle = new JPanel();
        panelTitle.setPreferredSize(new Dimension(200, 0));
        panelTitle.add(title);
        this.getContentPane().add(panelTitle);

        JPanel panelButton = new JPanel();
        panelButton.add(btnManage);
        panelButton.add(btnCreateUser);
        panelButton.add(btnViewReport);
        panelButton.add(btnChangePassword);
        panelButton.add(btnChangePhoto);

        this.getContentPane().add(panelButton);

        btnManage.addActionListener(new ActionListener() { // when controller is simple, we can declare it on the fly
            public void actionPerformed(ActionEvent e) {
                Application.getInstance().getProductView().setVisible(true);
            }
        });
        btnCreateUser.addActionListener(new ActionListener() { // when controller is simple, we can declare it on the fly
            public void actionPerformed(ActionEvent e) {
                Application.getInstance().getUserView().setVisible(true);
            }
        });


    }

    public JButton getBtnChangePassword() {
        return btnChangePassword;
    }

}
