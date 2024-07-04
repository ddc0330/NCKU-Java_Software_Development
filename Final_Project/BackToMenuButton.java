package test;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BackToMenuButton extends JButton {
    Font font = new Font("Arial", Font.PLAIN, 50);
    public BackToMenuButton(String text) {
        super(text);
        this.setFont(font);
        Border raisedBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createLineBorder(Color.BLACK, 2)
        );
        this.setBorder(raisedBorder);
        this.setBackground(Color.WHITE);
        this.setBounds(700, 500, 200, 100);
        this.setFocusable(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Main.frame.setVisible(false);
                Main.menu.setVisible(true);
                GamePanel.blackTimer.stop();
                GamePanel.whiteTimer.stop();
                GamePanel.countdownTimer.stop();
                GamePanel.reset();
            }
        });
    }
}
