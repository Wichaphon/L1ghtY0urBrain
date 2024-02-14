
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JPanel;
import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.SwingConstants;

public class disPlay{
    static disPlay gameDisPlay;
    static final int tileSize = 16 * 3;
    static final int ScreeenVertical = 16; //sleep 768
    static final int ScreenHorizontal = 20; //stand 960
    static final int ScreenWidth = tileSize * ScreenHorizontal; 
    static final int ScreeenHeight = tileSize * ScreeenVertical;  
    
    // public disPlay(){
    //     JFrame window = new JFrame();
    //     window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     window.setResizable(false);
    //     window.setTitle("L1ght Y0ur Brain");
    
    //     window.setLocationRelativeTo(null);
    //     window.setBackground(Color.RED);
    //     window.setVisible(true);

    //     window.setPreferredSize(new Dimension(ScreenWidth, ScreeenHeight));
    //     window.pack();
        

    //     window.setIconImage(new ImageIcon("Image/lyblg.png").getImage().getScaledInstance(256, 256, Image.SCALE_SMOOTH));
        
    // }

    public disPlay() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("L1ght Y0ur Brain");

        // Create a JPanel to serve as the content pane
        JPanel contentPane = new JPanel();
        contentPane.setPreferredSize(new Dimension(ScreenWidth, ScreeenHeight));

        // Set the background color for the content pane
        contentPane.setBackground(Color.decode("#2b2b2b"));
        

        // Set the content pane for the JFrame
        window.setContentPane(contentPane);

        window.pack();
        window.setIconImage(new ImageIcon("Image/lyblg.png").getImage().getScaledInstance(256, 256, Image.SCALE_SMOOTH));
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}

