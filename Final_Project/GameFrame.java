package test;
import javax.swing.*;
public class GameFrame extends JFrame {
    //初始化
    public static GamePanel panel = new GamePanel();
    public GameFrame() {
        this.setTitle("五子棋");
        this.setSize(1000, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(null); //這樣button才能set bounds
        this.add(panel);
        this.setLayout(null);
    }
}