import java.io.File;
import javax.sound.sampled.*;

public class BackgroundSound {
    private File soundFile;
    private Clip clip;

    public void playBackgroundSound(String filePath) {
        try {
            soundFile = new File(filePath);
            if (soundFile.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundFile);
                clip = AudioSystem.getClip();
                clip.open(audioInput);

                // Set volume
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(6.0f);

                // Loop the background sound
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                System.out.println("File not found: " + soundFile.getAbsolutePath());
            }
        } catch (Exception e) {
            System.out.println("Error playing background sound:");
            e.printStackTrace();
        }
    }

    public void stopBackgroundSound() {
        try {
            if (clip != null) {
                clip.stop();
                clip.close();
            } else {
                System.out.println("No background sound to stop.");
            }
        } catch (Exception e) {
            System.out.println("Error stopping background sound:");
            e.printStackTrace();
        }
    }
}