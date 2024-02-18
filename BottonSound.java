import java.io.File;
import javax.sound.sampled.*;
import javax.swing.Timer;  

public class BottonSound {
    private File soundFile;
    private Clip clip;

    public void playSoundBotton(String filePath, int durationInMillis) {
        try {
            soundFile = new File(filePath);
            if (soundFile.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundFile);
                clip = AudioSystem.getClip();
                clip.open(audioInput);

                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(6.0f);

                clip.start();

                Timer timer = new Timer(durationInMillis, e -> close());
                timer.setRepeats(false);
                timer.start();
            } else {
                System.out.println("File not found: " + soundFile.getAbsolutePath());
            }
        } catch (Exception e) {
            System.out.println("Error playing sound button:");
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (clip != null) {
                clip.stop();
                clip.close();
            } else {
                System.out.println("No sound button to close.");
            }
        } catch (Exception e) {
            System.out.println("Error closing sound button:");
            e.printStackTrace();
        }
    }
}
