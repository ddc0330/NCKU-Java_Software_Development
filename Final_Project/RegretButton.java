package test;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class RegretButton extends JButton {
    Font font = new Font("Arial", Font.PLAIN, 50);
    public RegretButton(String text, GamePanel panel) {
        super(text);
        this.setBounds(700, 300, 200, 100);
        this.setFocusable(false);
        Border raisedBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createLineBorder(Color.BLACK, 2)
        );
        this.setBorder(raisedBorder);
        this.setBackground(Color.WHITE);
        this.setFont(font);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(GamePanel.countdown<=30){
                    GamePanel.countdown = 30;
                }
                GamePanel.countdownTimer.getActionListeners()[0].actionPerformed(null);
                if(GamePanel.stepcount>0) {
                    GamePanel.stepcount--;
                    GamePanel.board[GamePanel.goback[GamePanel.stepcount][1]][GamePanel.goback[GamePanel.stepcount][0]] = 0;
                    panel.repaint();
                    GamePanel.isBlack = !GamePanel.isBlack;
                    GamePanel.label.setText(GamePanel.isBlack ? "PLAYER:BLACK" : "PLAYER:WHITE");
                }
            }
        });
    }
}
