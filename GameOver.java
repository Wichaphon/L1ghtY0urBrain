import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class GameOver extends JFrame implements ActionListener {
    private ImageIcon imageIcon;
    private Timer timer;
    private Image gameOver, peat, del, ppond;
    private int xVel = 5;
    private int x = (384 / 2) + 20;
    private int y = 125;
    private JLabel textHighscore, numHighscore, dummy, returnmenu, returncountdown;
    private JPanel contentPane;
    private double rotationAngle = 0;
    private int countdownSeconds = 5;

    private BottonSound musicBotton;
    private BackgroundSound musicBackground;

    public GameOver() {
        musicBotton = new BottonSound();
        musicBotton.playSoundBotton("Sound\\gameoversound.wav", 5000);
        imageIcon = new ImageIcon("Image/Logo.png");
        this.setTitle("L1ght Y0ur Brain (Beta 1.0)");
        this.setIconImage(imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
        this.setSize(960, 768);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gameOver = new ImageIcon("Image/GameOverFile/gameover.png").getImage();
        peat = new ImageIcon("Image/GameOverFile/peat.png").getImage();
        del = new ImageIcon("Image/GameOverFile/del.png").getImage();
        ppond = new ImageIcon("Image/GameOverFile/ppond.png").getImage();
        timer = new Timer(20, this);  // Adjusted timer frequency
        timer.start();
        

        Timer countdownTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countdownSeconds--;

                if (countdownSeconds <= 0) {
                    timer.stop();  // หยุด Timer ที่เคยสร้างไว้
                    ((Timer) e.getSource()).stop();  // หยุด Timer นับถอยหลัง
                    SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        App game = new App();
                    }
                });
                    dispose();  // ปิดหน้าต่าง
                    
                } else {
                    returncountdown.setText(Integer.toString(countdownSeconds));
                }
            }
        });
        countdownTimer.start();

        Timer rotationTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rotationAngle += 0.1;  
                repaint();
            }
        });
        rotationTimer.start();

        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                // Set rendering hints for smoother text rendering
                g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                drawRotatedImage(g2d, peat, 100, 370, rotationAngle);
                drawRotatedImage(g2d, del, 400, 370, rotationAngle);
                drawRotatedImage(g2d, ppond, 700, 370, rotationAngle);

                g2d.drawImage(gameOver, x, y, this);
            }
        };

        contentPane.setLayout(new BorderLayout());
        contentPane.setBackground(Color.decode("#2b2b2b"));
        // Disable double-buffering to reduce text shaking
        contentPane.setDoubleBuffered(false);
        this.add(contentPane, BorderLayout.CENTER);
        ImageIcon gameIcon = new ImageIcon("Image/MenuIcon.png");
        gameIcon.setImage(gameOver);
        setDetail();

        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void setDetail() {
        textHighscore = new JLabel("Your Highscore:");
        numHighscore = new JLabel(getHighscoreFromSaveFile());
        returnmenu = new JLabel("Return to menu in ");
        returncountdown = new JLabel("5");
        dummy = new JLabel("");
        textHighscore.setForeground(Color.WHITE);
        numHighscore.setForeground(Color.WHITE);
        returnmenu.setForeground(Color.WHITE);
        returncountdown.setForeground(Color.red);
        textHighscore.setFont(new Font("pixellet", Font.BOLD, 30));
        numHighscore.setFont(new Font("pixellet", Font.BOLD, 30));
        returnmenu.setFont(new Font("pixellet", Font.BOLD, 23));
        returncountdown.setFont(new Font("pixellet", Font.BOLD, 30));
        textHighscore.setBounds(270, 200, 500, 150);
        numHighscore.setBounds(590, 200, 200, 150);
        returnmenu.setBounds(300, 550, 313, 150);
        returncountdown.setBounds(600, 550, 200, 150);

        contentPane.add(textHighscore);
        contentPane.add(numHighscore);
        contentPane.add(returnmenu);
        contentPane.add(returncountdown);
        contentPane.add(dummy);
    }

    private String getHighscoreFromSaveFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("HighScoreSave.txt"));
            String highscore = reader.readLine();
            reader.close();
            return highscore != null ? highscore : "0";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (x >= 750 - gameOver.getWidth(null) || x <= 300) {
            xVel *= -1;
        }

        x += xVel;
        repaint();
    }

    private void drawRotatedImage(Graphics2D g2d, Image image, int x, int y, double angle) {
        int imageWidth = image.getWidth(this);
        int imageHeight = image.getHeight(this);

        g2d.rotate(angle, x + imageWidth / 2, y + imageHeight / 2);
        g2d.drawImage(image, x, y, this);
        g2d.rotate(-angle, x + imageWidth / 2, y + imageHeight / 2);
    }

    public static void main(String[] args) {
        GameOver x = new GameOver();
    }
}
