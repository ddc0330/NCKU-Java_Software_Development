package test;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    private Image backgroundImage;
    StartButton startbutton = new StartButton("Start" , GameFrame.panel);
    Ghostmode ghostmode = new Ghostmode("Ghost",GameFrame.panel);

    Bombmode bombmode=new Bombmode("Bomb",GameFrame.panel);
    SettingButton settingbutton = new SettingButton("Setting");

    public MenuPanel() {
        backgroundImage = new ImageIcon("resources/pics/aaaa.jpg").getImage();
        this.add(startbutton);
        this.add(ghostmode);
        this.add(settingbutton);
        this.add(bombmode);
        this.setLayout(null);
        this.setBounds(0, 0, 500, 300);
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 繪製背景圖片
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
