package test;
import javax.swing.*;

public class SettingFrame extends JFrame {
    static JCheckBox checkBox1 = new JCheckBox("NO BLACK TIME");
    static JCheckBox checkBox2 = new JCheckBox("NO WHITE TIME");
    static JCheckBox checkBox3 = new JCheckBox("NO COUNT DOWN TIME");
    static JCheckBox checkBox4 = new JCheckBox("NO GO BACK BUTTON");
    public SettingFrame(GamePanel panel) {
        setTitle("setting");
        setSize(220, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 設置框架布局
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        // 添加勾選框到框架，並將它們置中
        checkBox1.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        checkBox2.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        checkBox3.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        checkBox4.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        add(checkBox1);
        add(checkBox2);
        add(checkBox3);
        add(checkBox4);
        // 創建按鈕
        JButton button = new JButton("Back to Menu");
        add(button);
        // 設置按鈕點擊事件
        button.addActionListener(e -> {
            Main.setting.setVisible(false);
            Main.menu.setVisible(true);
        });
        // 設置按鈕置中
        button.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        // 設置框架可見
        setVisible(true);
    }
}
