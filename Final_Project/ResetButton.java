package test;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ResetButton extends JButton {
    Font font = new Font("Arial", Font.PLAIN, 50);
    public ResetButton(String text, GamePanel panel) {
        super(text);
        this.setFont(font);
        Border raisedBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createLineBorder(Color.BLACK, 2)
        );
        this.setBorder(raisedBorder);
        this.setBackground(Color.WHITE);
        this.setBounds(700, 400, 200, 100);
        this.setFocusable(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(GamePanel.countdown<=30){
                    GamePanel.countdown = 31;
                }
                GamePanel.blackTime = -1;
                GamePanel.whiteTime = -1;
                GamePanel.stepcount = 0;
                GamePanel.blackTimer.getActionListeners()[0].actionPerformed(null);
                GamePanel.whiteTimer.getActionListeners()[0].actionPerformed(null);
                GamePanel.countdownTimer.getActionListeners()[0].actionPerformed(null);
                GamePanel.countdownTimer.start();
                GamePanel.whiteTimer.stop();
                GamePanel.blackTimer.start();
                GamePanel.reset();
                panel.repaint();
                GamePanel.isBlack = true;
                GamePanel.stepcount = 0;
                GamePanel.label.setText("PLAYER:BLACK");
            }
        });
    }
}
