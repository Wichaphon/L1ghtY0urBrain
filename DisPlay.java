
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
// import javax.swing.Timer;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;
import java.awt.Dimension;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DisPlay implements MouseListener{
    static DisPlay gameDisPlay;
    static final int tileSize = 16 * 3;
    static final int ScreeenVertical = 16; //sleep 768
    static final int ScreenHorizontal = 20; //stand 960
    static final int ScreenWidth = tileSize * ScreenHorizontal; 
    static final int ScreeenHeight = tileSize * ScreeenVertical;

    JFrame window; 
    JPanel contentPane;
    JPanel bottomPane;
    JPopupMenu content;
    JLabel textScore, numScore; // label"score:"  and labelscore
    JLabel textHighscore, numHighscore; // label"Highscore:" and Labelhighscore
    JButton btnQuit; // quit button
    JLabel textQuit;
    JButton purPad, greenPad, redPad, cyanPad, orgPad, yellPad; // All pads (6 pads)
    Icon left, right, up, down;
    PadType type; // enum Padtype
    LinkedHashMap<PadType,Integer> pattern = new LinkedHashMap<>();
    ArrayList <PadType> list_pad = new ArrayList<>(); // list of Padtype 
    ArrayList <Integer> list_amount = new ArrayList<>(); // list of amount to click in one pad
    int countgen = 1; // count for generate pattern (level will increase from countgen)
    int cntrandom = 1;
    
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

    public DisPlay() {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("L1ght Y0ur Brain");
        window.getContentPane().setBackground(Color.decode("#2b2b2b"));
        window.setSize(new Dimension(ScreenWidth, ScreeenHeight));
        window.setVisible(true);
       
        // Create a JPanel to serve as the content pane
        contentPane = new JPanel();
        contentPane.setBounds(0, 0,950, 100);
        contentPane.setBackground(Color.decode("#2b2b2b"));
        contentPane.setLayout(null);
        // contentPane.setPreferredSize(new Dimension(ScreenWidth, ScreeenHeight));
        // contentPane.setLayout(new FlowLayout());

        bottomPane = new JPanel();
        bottomPane.setBounds(0, 500,950, 150);
        bottomPane.setBackground(Color.WHITE);
        bottomPane.setLayout(null);
        // bottomPane.setVisible(true);

        // Set the content pane for the JFrame
        // window.setContentPane(contentPane);
        // window.pack();
        window.setIconImage(new ImageIcon("Image/lyblg.png").getImage().getScaledInstance(256, 256, Image.SCALE_SMOOTH));
        window.setLocationRelativeTo(null);
        window.add(contentPane);
        // window.add(bottomPane);
        // contentPane.setVisible(true);
        stageDetails();
        
    }
    void stageDetails(){
        // set textscore and numscore
        textScore = new JLabel("score: ");
        numScore = new JLabel("0");
        textScore.setForeground(Color.WHITE);
        numScore.setForeground(Color.WHITE);
        textScore.setFont(new Font("Unispace", Font.BOLD,24 ));
        numScore.setFont(new Font("Unispace", Font.BOLD, 24));
        textScore.setBounds(768, 30, 150, 100);
        numScore.setBounds(860, 30,150, 100);

        // set textHighScore and numHighScore
        textHighscore = new JLabel("Highscore:");
        numHighscore = new JLabel("0");
        textHighscore.setForeground(Color.WHITE);
        numHighscore.setForeground(Color.WHITE);
        textHighscore.setFont(new Font("Unispace", Font.BOLD,24 ));
        numHighscore.setFont(new Font("Unispace", Font.BOLD, 24));
        textHighscore.setBounds(700, 0, 150, 100);
        numHighscore.setBounds(860, 0,150, 100);
        // set Quit button
        btnQuit = new JButton("Quit");
        btnQuit.setFont(new Font("Unispace", Font.BOLD,18 ));
        btnQuit.setBounds(415, 620, 70, 50);
        btnQuit.setPreferredSize(new Dimension(70,50));
        btnQuit.addMouseListener(this);
        btnQuit.setForeground(Color.WHITE);
        btnQuit.setBackground(Color.decode("#2b2b2b"));
        btnQuit.setBorder(BorderFactory.createEmptyBorder());
        btnQuit.setFocusPainted(false);
        btnQuit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // set All pads
        // set purplepad
        UIManager.put("Button.select", Color.decode("#2b2b2b"));
        purPad = new JButton();
        ImageIcon img = new ImageIcon("Image\\Allpads1\\purpad.png");
        RotatedIcon r1 = new RotatedIcon(img,45);
        purPad.setIcon(r1);
        purPad.setBounds(150, 150, 150, 150);
        purPad.setBorderPainted(false);
        purPad.setFocusPainted(false);
        purPad.setOpaque(false);
        purPad.setContentAreaFilled(false);
        // purPad.setBorder(BorderFactory.);
        purPad.setCursor(new Cursor(Cursor.HAND_CURSOR));
        purPad.addMouseListener(this);;
        // set greenpad
        greenPad = new JButton();
        img = new ImageIcon("Image\\Allpads1\\greenpad.png");
        r1 = new RotatedIcon(img, 45);
        greenPad.setIcon(r1);
        greenPad.setBounds(260, 265, 150, 150);
        greenPad.setBorderPainted(false);
        greenPad.setFocusPainted(false);
        greenPad.setOpaque(false);
        greenPad.setContentAreaFilled(false);
        greenPad.setBackground(Color.decode("#2b2b2b"));
        greenPad.setBorder(BorderFactory.createEmptyBorder());
        greenPad.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // set redpad
        redPad = new JButton();
        img = new ImageIcon("Image\\Allpads1\\redpad.png");
        r1 = new RotatedIcon(img, 45);
        redPad.setIcon(r1);
        redPad.setBounds(370, 379, 150, 150);
        redPad.setBorderPainted(false);
        redPad.setFocusPainted(false);
        redPad.setOpaque(false);
        redPad.setContentAreaFilled(false);
        redPad.setBackground(Color.decode("#2b2b2b"));
        redPad.setBorder(BorderFactory.createEmptyBorder());
        redPad.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // set cyanpad
        cyanPad = new JButton();
        img = new ImageIcon("Image\\Allpads1\\cyanpad.png");
        r1 = new RotatedIcon(img,45.0);
        cyanPad.setIcon(r1);
        cyanPad.setBounds(370, 150, 150, 150);
        cyanPad.setBorderPainted(false);
        cyanPad.setFocusPainted(false);
        cyanPad.setOpaque(false);
        cyanPad.setContentAreaFilled(false);
        cyanPad.setBackground(Color.decode("#2b2b2b"));
        cyanPad.setBorder(BorderFactory.createEmptyBorder());
        cyanPad.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // set orangepad
        orgPad = new JButton();
        img = new ImageIcon("Image\\Allpads1\\orangepad.png");
        r1 = new RotatedIcon(img, 45);
        orgPad.setIcon(r1);
        orgPad.setBounds(480, 265, 150, 150);
        orgPad.setBorderPainted(false);
        orgPad.setFocusPainted(false);
        orgPad.setOpaque(false);
        orgPad.setContentAreaFilled(false);
        orgPad.setBackground(Color.decode("#2b2b2b"));
        orgPad.setBorder(BorderFactory.createEmptyBorder());
        orgPad.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // set yellowpad
        yellPad = new JButton();
        img = new ImageIcon("Image\\Allpads1\\yellowpad.png");
        r1 = new RotatedIcon(img, 45);
        yellPad.setIcon(r1);
        yellPad.setBounds(590, 150,150, 150);
        yellPad.setBorderPainted(false);
        yellPad.setFocusPainted(false);
        yellPad.setOpaque(false);
        yellPad.setContentAreaFilled(false);
        yellPad.setBackground(Color.decode("#2b2b2b"));
        yellPad.setBorder(BorderFactory.createEmptyBorder());
        yellPad.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // add to contentPane
        contentPane.add(textScore);
        contentPane.add(numScore);
        contentPane.add(textHighscore);
        contentPane.add(numHighscore);
        contentPane.add(btnQuit);
        contentPane.add(purPad);
        contentPane.add(cyanPad);
        contentPane.add(yellPad);
        contentPane.add(greenPad);
        contentPane.add(orgPad);
        contentPane.add(redPad);
        // try {
        //     Thread.sleep(2000);
        // } catch (Exception e) {
        //     // TODO: handle exception
        // }
        patternShow();
        
        
        

    }
    void patternShow(){
        genPattern();
        System.out.println(pattern);
        int n = 0;
        for (Map.Entry<PadType, Integer> patt : pattern.entrySet()){
            // try {
                // TimeUnit.SECONDS.sleep(2);
                if (patt.getKey() == PadType.RED){
                    n = (int)patt.getValue();
                    ImageIcon img = new ImageIcon("Image\\LightPad\\lightredpad.png");
                    RotatedIcon r = new RotatedIcon(img, 45);
                    for (int i = 0; i < n; i++){
                        Timer time = new Timer();
                        TimerTask task = new TimerTask() {
                            @Override
                            public void run() {
                                System.out.println("delay 1 s");
                                time.cancel();
                                // TODO Auto-generated method stub
                                // throw new UnsupportedOperationException("Unimplemented method 'run'");
                            }
                            
                        };
                        time.schedule(task, 1000);
                        redPad.setIcon(r);
                    }
                    
                    // img = new ImageIcon("Image\\Allpads1\\redpad.png");
                    // r = new RotatedIcon(img, 45);
                    // redPad.setIcon(r);
                }
                else if (patt.getKey() == PadType.CYAN){
                    n = (int)patt.getValue();
                    for (int i = 0; i < n; i++){
                        ImageIcon img = new ImageIcon("Image\\LightPad\\lightcyanpad.png");
                        RotatedIcon r = new RotatedIcon(img, 45);
                        cyanPad.setIcon(r);
                        // cyanPad.setBounds(370, 379, 200, 200);
                        // cyanPad.setSize(new Dimension(180,180));
                        // cyanPad.setBounds(370, 150, 150, 150);
                        // contentPane.add(cyanPad);
                    }
                }
                else if (patt.getKey() == PadType.LIGHT_GREEN){
                    n = (int)patt.getValue();
                    for (int i = 0; i < n; i++){
                        ImageIcon img = new ImageIcon("Image\\LightPad\\lightgreenpad.png");
                        RotatedIcon r = new RotatedIcon(img, 45);
                        greenPad.setIcon(r);
                        // greenPad.setBounds(370, 379, 200, 200);
                        // greenPad.setSize(new Dimension(180,180));
                        // greenPad.setBounds(260, 265, 150, 150);
                        // contentPane.add(greenPad);
                    }
                }
                else if (patt.getKey() == PadType.ORANGE){
                    n = (int)patt.getValue();
                    for (int i = 0; i < n; i++){
                        ImageIcon img = new ImageIcon("Image\\LightPad\\lightorgpad.png");
                        RotatedIcon r = new RotatedIcon(img, 45);
                        orgPad.setIcon(r);
                        // orgPad.setBounds(480, 265, 150, 150);
                        // contentPane.add(orgPad);
                        
                    }
    
                }
                else if (patt.getKey() == PadType.PURPLE){
                    n = (int)patt.getValue();
                    for (int i = 0; i < n; i++){
                        ImageIcon img = new ImageIcon("Image\\LightPad\\lightpurpad.png");
                        RotatedIcon r = new RotatedIcon(img, 45);
                        purPad.setIcon(r);
                        // purPad.setBounds(370, 379, 200, 200);
                        // purPad.setSize(new Dimension(180,180));
                        // purPad.setBounds(150, 150, 150, 150);
                        // contentPane.add(purPad);
                    }
    
                }
                else if (patt.getKey() == PadType.YELLOW){
                    n = (int)patt.getValue();
                    for (int i = 0; i < n; i++){
                        ImageIcon img = new ImageIcon("Image\\LightPad\\lightyellpad.png");
                        RotatedIcon r = new RotatedIcon(img, 45);
                        yellPad.setIcon(r);
                        // purPad.setBounds(370, 379, 200, 200); 
                        // yellPad.setSize(new Dimension(180,180));
                        // yellPad.setBounds(590, 150,150, 150);
                        // contentPane.add(yellPad);
                    }
    
                }
            // } catch (InterruptedException e) {
            //     // TODO Auto-generated catch block
            //     e.printStackTrace();
            // }
        } 
    }
    private void genPattern(){
        // add PadType to list_pad
        list_pad.add(PadType.RED);
        list_pad.add(PadType.CYAN);
        list_pad.add(PadType.LIGHT_GREEN);
        list_pad.add(PadType.ORANGE);
        list_pad.add(PadType.YELLOW);
        list_pad.add(PadType.PURPLE);

        // list_amount add amount by condition n = 2, n = 3, n = 4
        int cnt = countgen;
        cntrandom = countgen;
        
        System.out.println("----------Round: " + countgen + "---------");
        if (cnt == 2){
            list_amount.add(2);
            pattern.put(randomPadtype(list_pad), randomAmount(list_amount));
        }
        else if (cnt == 3){
            list_amount.add(2);
            list_amount.add(1);
            pattern.put(randomPadtype(list_pad), 2);
            pattern.put(randomPadtype(list_pad), 1);
        }
        else if (cnt == 4){
            list_amount.add(2);
            list_amount.add(2);
            pattern.put(randomPadtype(list_pad),2);
            pattern.put(randomPadtype(list_pad), 2);
        }
        else{
            for (int i = 1; i <= cntrandom; i++){
                list_amount.add(i);
            }
            while (cntrandom > 0){
                System.out.println("Count: " + cntrandom);
                int n = randomAmount(list_amount);
                pattern.put(randomPadtype(list_pad),n);
                // System.out.println(pattern);
                if (n == cntrandom){
                    break;
                }
                cntrandom -= n;
                if (cntrandom < n){
                    if (cntrandom < 0){
                        cntrandom = cntrandom * -1;
                    }
                    pattern.put(randomPadtype(list_pad), cntrandom);
                    break;
                }
            }
            
        }
        // System.out.println("LastMap: " + pattern);

        // reset all list
        // list_amount.clear();
        // pattern.clear();
        // System.out.println(pattern);
        
    }
    private PadType randomPadtype(ArrayList<PadType> list){
        Random rand = new Random();
        int i = rand.nextInt(list.size());
        PadType type = list.get(i);
        if (cntrandom <= 6){
            System.out.println("remove: " + list.get(i));
            list.remove(i);   
        }
        // System.out.println("Listpad after random: " + list);
        return type;
    }
    private Integer randomAmount(ArrayList<Integer> list){
        Random rand = new Random();
        // System.out.println( "Listamount before random: "+ list);
        int i = rand.nextInt(list.size());
        Integer n = list.get(i);
        System.out.println( "Listamount after random: "+ list);
        return n;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       JButton input = (JButton)e.getSource();
       if (input == btnQuit){
            window.dispose();
            System.out.println("from btnQuit: dispose window");
        }
       else if (input == purPad){
            System.out.println("from purPad: click purPad") ;
        }
        
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        JButton input = (JButton)e.getSource();
        if (input == btnQuit){
            btnQuit.setFont(new Font("Unispace", Font.BOLD,20 ));
        }


        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }
    @Override
    public void mouseExited(MouseEvent e) {
        JButton input = (JButton)e.getSource();
        if (input == btnQuit){
            btnQuit.setFont(new Font("Unispace", Font.BOLD,18));
        }
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }
    

}

