package test;
import javax.swing.*;

public class MenuFrame extends JFrame{
    public MenuFrame() {
        SoundPlayer music2 = new SoundPlayer();
        music2.loadSound("/sounds/music2.wav");
        music2.setVolume(-25.0f);
        SoundPlayer.playSound();
        this.setTitle("Menu");
        this.setSize(700, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MenuPanel menupanel = new MenuPanel();
        menupanel.setLayout(null);
        this.add(menupanel);
    }
}