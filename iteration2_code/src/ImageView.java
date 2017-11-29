import javax.swing.*;

public class ImageView extends JFrame {
    // TODO: get "upload image" working
    public ImageView() {
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(400, 300);

        JPanel panel = new JPanel();
        ImageIcon icon = new ImageIcon("image.jpg");
        JLabel label = new JLabel();
        label.setIcon(icon);
        panel.add(label);

        this.getContentPane().add(panel);
    }
}
