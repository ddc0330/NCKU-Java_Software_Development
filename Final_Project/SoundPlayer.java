package test;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;

public class SoundPlayer {
    private static Clip clip;
    private FloatControl volumeControl;
    // 加載音效文件
    public void loadSound(String soundFileName) {
        try {
            // 獲取音效文件的 URL
            URL soundFile = getClass().getResource(soundFileName);
            if (soundFile == null) {
                System.err.println("音效文件未找到: " + soundFileName);
                return;
            }
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);

            // 打開音效剪輯
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public void setVolume(float volume) {
        if (volumeControl != null) {
            // 設置音量
            volumeControl.setValue(volume);
        }
    }
    // 播放
    public static void playSound() {
        if (clip != null) {
            stopSound(); // 在播放新音效之前停止當前音效
            clip.setFramePosition(0); // 將音效播放位置設置到起點
            clip.start(); // 開始播放
        }
    }
    // 停止音效
    public static void stopSound() {
        if (clip != null && clip.isRunning()) {
            clip.stop(); // 停止播放
        }
    }
}
