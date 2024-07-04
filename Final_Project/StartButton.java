package test;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StartButton extends JButton {
    Font font = new Font("Arial", Font.ITALIC, 50);
    public StartButton(String text, GamePanel panel) {
        super(text);
        this.setFont(font);
        Border raisedBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createLineBorder(Color.BLACK, 2)
        );
        this.setBorder(raisedBorder);
        this.setBackground(Color.WHITE);
        this.setBounds(250, 75, 200, 75);
        this.setFocusable(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Main.frame.setVisible(true);
                Main.menu.setVisible(false);
                GamePanel.blackTime = -1;
                GamePanel.whiteTime = -1;
                GamePanel.countdown = 31;
                GamePanel.reset();
                panel.repaint();
                GamePanel.isBlack = true;
                GamePanel.stepcount =0;
                GamePanel.ghostmode=false;
                GamePanel.bombmode=false;
                GamePanel.label.setText("PLAYER:BLACK");
                GamePanel.blackTimer.start();
                GamePanel.countdownTimer.start();
                if (SettingFrame.checkBox1.isSelected()) {
                    GamePanel.timerLabel11.setVisible(false);
                }
                else{
                    GamePanel.timerLabel11.setVisible(true);
                }
                if (SettingFrame.checkBox2.isSelected()) {
                    GamePanel.timerLabel12.setVisible(false);
                }
                else{
                    GamePanel.timerLabel12.setVisible(true);
                }
                if (SettingFrame.checkBox3.isSelected()) {
                    GamePanel.timerLabel2.setVisible(false);
                    GamePanel.countdown = 100000;
                }
                else{
                    GamePanel.timerLabel2.setVisible(true);
                }
                if (SettingFrame.checkBox4.isSelected()) {
                    GameFrame.panel.gobackbutton.setVisible(false);
                }
                else{
                    GameFrame.panel.gobackbutton.setVisible(true);
                }
                GamePanel.blackTimer.getActionListeners()[0].actionPerformed(null);
                GamePanel.whiteTimer.getActionListeners()[0].actionPerformed(null);
                GamePanel.countdownTimer.getActionListeners()[0].actionPerformed(null);
            }
        });
    }
}
