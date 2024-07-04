package test;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SettingButton extends JButton {
    Font font = new Font("Arial", Font.ITALIC, 50);
    public SettingButton(String text) {
        super(text);
        this.setFont(font);
        this.setBounds(250, 300, 200, 75);
        Border raisedBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createLineBorder(Color.BLACK, 2)
        );
        this.setBorder(raisedBorder);
        this.setBackground(Color.WHITE);
        this.setFocusable(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Main.setting.setVisible(true);
                Main.menu.setVisible(false);
            }
        });
    }
}
