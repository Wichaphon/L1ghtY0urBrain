import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class App extends JFrame implements ActionListener {
    private JButton[] menuButtons = new JButton[2];
    private ImageIcon[] btnIcons = new ImageIcon[2];
    private JLabel gameLabel;
    private Icon gameIcon;
    private ImageIcon imageIcon;
    private Image[] bgSquares = new Image[6];
    private Timer timer;
    private int[] x = new int[6];
    private int[] y = new int[6];
    private int[] xVel = new int[6];
    private int[] yVel = new int[6];
    private double rotationAngle = 0.0;

    private BottonSound musicBotton;
    private BackgroundSound musicBackground;

    public App() {
        musicBackground = new BackgroundSound();
        musicBackground.playBackgroundSound("Sound/menuBgSound.wav");
        imageIcon = new ImageIcon("Image/Logo.png");
        this.setIconImage(imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
        this.setSize(960, 768);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (int i = 0; i < 6; i++) {
            bgSquares[i] = new ImageIcon("Image/BgSquareAM/bgpad" + (i + 1) + ".png").getImage();
            x[i] = (int) (Math.random() * (960 - bgSquares[i].getWidth(null)));
            y[i] = (int) (Math.random() * (768 - bgSquares[i].getHeight(null)));
            xVel[i] = (int) (Math.random() * 5 + 1);
            yVel[i] = (int) (Math.random() * 5 + 1);
        }

        timer = new Timer(10, this);
        timer.start();

        JPanel contentPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                for (int i = 0; i < bgSquares.length; i++) {
                    int centerX = x[i] + bgSquares[i].getWidth(null) / 2;
                    int centerY = y[i] + bgSquares[i].getHeight(null) / 2;

                    g2d.rotate(Math.toRadians(rotationAngle), centerX, centerY);
                    g2d.drawImage(bgSquares[i], x[i], y[i], null);
                    g2d.rotate(-Math.toRadians(rotationAngle), centerX, centerY);
                }
            }
        };
        contentPanel.setLayout(null);
        contentPanel.setBackground(Color.decode("#2b2b2b"));
        this.add(contentPanel, BorderLayout.CENTER);

        gameLabel = new JLabel();
        gameIcon = new ImageIcon("Image/MenuIcon.png");
        gameLabel.setBounds(220, 30, 512, 384);
        gameLabel.setIcon(gameIcon);
        contentPanel.add(gameLabel);

        setupMenuButtons(contentPanel);

        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < bgSquares.length; i++) {
            if (x[i] >= 960 - bgSquares[i].getWidth(null) || x[i] < 0) {
                xVel[i] *= -1;
            }
            x[i] += xVel[i];

            if (y[i] >= 768 - bgSquares[i].getHeight(null) || y[i] < 0) {
                yVel[i] *= -1;
            }
            y[i] += yVel[i];
        }

        rotationAngle += 15.0;

        if (rotationAngle >= 360) {
            rotationAngle -= 360;
        }

        repaint();
    }

    private void setupMenuButtons(JPanel contentPanel) {
        for (int i = 0; i < menuButtons.length; i++) {
            String imgPath = "Image/gameMenu" + (i + 1) + ".png";
            btnIcons[i] = new ImageIcon(imgPath);

            menuButtons[i] = new JButton();
            menuButtons[i].setBounds(384, (400 + (i * 85)), 200, 55);
            Image img = btnIcons[i].getImage().getScaledInstance(menuButtons[i].getWidth(),
                    menuButtons[i].getHeight(), Image.SCALE_SMOOTH);
            menuButtons[i].setIcon(new ImageIcon(img));

            contentPanel.add(menuButtons[i]);
            menuButtons[i].addActionListener(new ButtonHandler());
            menuButtons[i].addMouseListener(new MouseOverHandler());
        }
    }

    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton) e.getSource();
            musicBotton = new BottonSound();
            
            if (clickedButton == menuButtons[0]) {
                musicBotton.playSoundBotton("Sound/clicksound.wav", 500);
                musicBackground.stopBackgroundSound();
                DisPlay game = new DisPlay();
                dispose();
            } else if (clickedButton == menuButtons[1]) {
                musicBotton.playSoundBotton("Sound/clicksound.wav", 500);
                musicBackground.stopBackgroundSound();
                dispose();
            }
        }
    }

    private class MouseOverHandler extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e) {
            JButton source = (JButton) e.getSource();
            musicBotton = new BottonSound();
            musicBotton.playSoundBotton("Sound/Pop.wav", 500);
            for (int i = 0; i < menuButtons.length; i++) {
                if (source == menuButtons[i]) {
                    menuButtons[i].setIcon(new ImageIcon(
                            (new ImageIcon("Image/gameMenu" + (i + 3) + ".png")).getImage().getScaledInstance(
                                    menuButtons[i].getWidth(), menuButtons[i].getHeight(), Image.SCALE_SMOOTH)));
                    break;
                }
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JButton source = (JButton) e.getSource();
            for (int i = 0; i < menuButtons.length; i++) {
                if (source == menuButtons[i]) {
                    menuButtons[i].setBounds(384, (400 + (i * 85)), 200, 55);
                    Image img = btnIcons[i].getImage().getScaledInstance(menuButtons[i].getWidth(),
                            menuButtons[i].getHeight(), Image.SCALE_SMOOTH);
                    menuButtons[i].setIcon(new ImageIcon(img));
                    break;
                }
            }
        }
    }
}
