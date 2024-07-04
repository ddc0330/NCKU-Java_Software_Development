package test;

public class Main{
    //初始化
    public static GameFrame frame;
    public static MenuFrame menu;
    public static SettingFrame setting;

    //main
    public static void main(String[] args) {
        menu = new MenuFrame();
        frame = new GameFrame();
        setting = new SettingFrame(GameFrame.panel);
        menu.setLocationRelativeTo(null); //frame出現在螢幕中央
        frame.setLocationRelativeTo(null);
        setting.setLocationRelativeTo(null);
        menu.setVisible(true); //顯示frame
        frame.setVisible(false);
        setting.setVisible(false);
    }
}