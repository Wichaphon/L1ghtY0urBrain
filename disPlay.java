
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
// import javax.swing.Timer;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.Dimension;
import javax.swing.Timer;
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

    private JFrame window; 
    private JPanel contentPane;
    private JPanel bottomPane;
    private JPopupMenu content;
    private JLabel textScore, numScore; // label"score:"  and labelscore
    private JLabel textHighscore, numHighscore; // label"Highscore:" and Labelhighscore
    private JButton btnQuit; // quit button
    private JLabel textQuit;
    JButton purPad, greenPad, redPad, cyanPad, orgPad, yellPad; // All pads (6 pads)
    private Icon left, right, up, down;
    private PadType type; // enum Padtype
    private LinkedHashMap<PadType,Integer> pattern = new LinkedHashMap<>();
    private LinkedHashMap<PadType, Integer> ans = new LinkedHashMap<>();
    private ArrayList <PadType> list_pad = new ArrayList<>(); // list of Padtype 
    private ArrayList <Integer> list_amount = new ArrayList<>(); // list of amount to click in one pad
    private ArrayList <PadType> pad = new ArrayList<>();
    ArrayList <Integer> idx = new ArrayList<>();
    private int countgen = 4; // count for generate pattern (level will increase from countgen)
    private int cntrandom = 0;
    private int cntpatt = 1;
    private int amount;
    private int cntidx = 0;
    private int j; //for blinking
    private int cntamount = 0;
    
    
    private Timer blinkTimer;

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
        btnQuit.setBounds(435, 620, 70, 50);
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
        purPad = new JButton(new ImageIcon("Image\\Finalpad\\pupad1.png"));
        purPad.setBounds(145, 152 ,200, 200);
        purPad.setBorderPainted(false);
        purPad.setFocusPainted(false);
        purPad.setOpaque(false);
        purPad.setContentAreaFilled(false);
        purPad.setBorder(BorderFactory.createEmptyBorder());
        
        purPad.addMouseListener(this);
        // set greenpad
        greenPad = new JButton(new ImageIcon("Image\\Finalpad\\greenpad1.png"));
        greenPad.setBounds(260, 265, 200, 200);
        greenPad.setBorderPainted(false);
        greenPad.setFocusPainted(false);
        greenPad.setOpaque(false);
        greenPad.setContentAreaFilled(false);
        greenPad.setBackground(Color.decode("#2b2b2b"));
        greenPad.setBorder(BorderFactory.createEmptyBorder());
        
        // set redpad
        redPad = new JButton(new ImageIcon("Image\\Finalpad\\redpad1.png"));
        redPad.setBounds(370, 379, 200, 200);
        redPad.setBorderPainted(false);
        redPad.setFocusPainted(false);
        redPad.setOpaque(false);
        redPad.setContentAreaFilled(false);
        redPad.setBackground(Color.decode("#2b2b2b"));
        redPad.setBorder(BorderFactory.createEmptyBorder());
        // set cyanpad
        cyanPad = new JButton(new ImageIcon("Image\\Finalpad\\cyanpad1.png"));
        cyanPad.setBounds(370, 150, 200, 200);
        cyanPad.setBorderPainted(false);
        cyanPad.setFocusPainted(false);
        cyanPad.setOpaque(false);
        cyanPad.setContentAreaFilled(false);
        cyanPad.setBackground(Color.decode("#2b2b2b"));
        cyanPad.setBorder(BorderFactory.createEmptyBorder());
        // set orangepad
        orgPad = new JButton(new ImageIcon("Image\\Finalpad\\orgpad1.png"));
        orgPad.setBounds(480, 265, 200, 200);
        orgPad.setBorderPainted(false);
        orgPad.setFocusPainted(false);
        orgPad.setOpaque(false);
        orgPad.setContentAreaFilled(false);
        orgPad.setBackground(Color.decode("#2b2b2b"));
        orgPad.setBorder(BorderFactory.createEmptyBorder());
        // set yellowpad
        yellPad = new JButton(new ImageIcon("Image\\Finalpad\\yellpad1.png"));
        yellPad.setBounds(590, 150,200, 200);
        yellPad.setBorderPainted(false);
        yellPad.setFocusPainted(false);
        yellPad.setOpaque(false);
        yellPad.setContentAreaFilled(false);
        yellPad.setBackground(Color.decode("#2b2b2b"));
        yellPad.setBorder(BorderFactory.createEmptyBorder());
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
        for (Map.Entry<PadType, Integer> patt : pattern.entrySet()){
            type = patt.getKey();
            int n = (int)patt.getValue();
            cntamount += n;
            pad.add(type);
            idx.add(n);
        }
        System.out.println("countamount : " + cntamount);
        startBlinkingEffect();
        // int n = idx.get(0);
        // for (int i = 0; i < idx.get(i); i++){
        //     startBlinkingEffect(pad.get(i), idx.get(i));
        //     // for (double x = 1; x <= 10000000; x+=.01);
        // }
        // for (int i = 0; i < idx.size(); i++){
        //     System.out.println(pad.get(i));
        //     System.out.println(idx.get(i));
        //     startBlinkingEffect(pad.get(i), idx.get(i));
            
        // }
        
        // startBlinkingEffect(pad.get(1), idx.get(1));
        // cntTimer = 1;
        // Timer timer = new Timer(1000 * n * 2, new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent arg0) {
        //         startBlinkingEffect(pad.get(1), idx.get(1));
        //         // cntTimer++;
        //     }
        // });
        // // timer.setRepeats(false);
        // if (cntTimer == pad.size() && cntTimer == idx.size()){
        //     timer.stop();
        //     // timer.setRepeats(false);
        // }
        // timer.setRepeats(false);
        // timer.start();
        // Timer timer = new Timer(1000 * n * 2, e -> {

        //     startBlinkingEffect(pad.get(1), idx.get(1));
        // });
        // timer.setRepeats(false);
        // timer.setInitialDelay(1000);
        // timer.start();
    }
    void gamePhase(){
        System.out.println("in gamePhase");
        purPad.setCursor(new Cursor(Cursor.HAND_CURSOR));
        redPad.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cyanPad.setCursor(new Cursor(Cursor.HAND_CURSOR));
        orgPad.setCursor(new Cursor(Cursor.HAND_CURSOR));
        yellPad.setCursor(new Cursor(Cursor.HAND_CURSOR));
        greenPad.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        PadActionListener action = new PadActionListener();
        
        redPad.addActionListener(action);
        cyanPad.addActionListener(action);
        greenPad.addActionListener(action);
        purPad.addActionListener(action);
        orgPad.addActionListener(action);
        yellPad.addActionListener(action);

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
                // System.out.println("Count: " + cntrandom);
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
        
        // System.out.println(pattern);
        
    }
    private PadType randomPadtype(ArrayList<PadType> list){
        Random rand = new Random();
        int i = rand.nextInt(list.size());
        PadType type = list.get(i);
        if (cntrandom <= 6){
            // System.out.println("remove: " + list.get(i));
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
        // System.out.println( "Listamount after random: "+ list);
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
    }
    @Override
    public void mouseExited(MouseEvent e) {
        JButton input = (JButton)e.getSource();
        if (input == btnQuit){
            btnQuit.setFont(new Font("Unispace", Font.BOLD,18));
        }
    }
    private void startBlinkingEffect() {
        // because it has 2 if that make blinking then n should * 2 
        // PadType pad = pad.get(0);
        // int count = (int) n.get(0);
        j = 0;
        amount = idx.get(j) * 2;
        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("type = " + pad.get(j));
                System.out.println("amount = " + amount);
                System.out.println("cntpatt = " + cntpatt);
                
                if (cntpatt % 2 != 0){
                    if (pad.get(j) == PadType.RED){
                        redPad.setIcon(new ImageIcon("Image\\Finalpad\\redpad2.png"));
                        System.out.println("change REDpad to light already");
                    }
                    else if(pad.get(j) == PadType.CYAN){
                        cyanPad.setIcon(new ImageIcon("Image\\Finalpad\\cyanpad2.png"));
                        System.out.println("change CYANpad to light already");
                    }
                    else if (pad.get(j) == PadType.LIGHT_GREEN){
                        greenPad.setIcon(new ImageIcon("Image\\Finalpad\\greenpad2.png"));
                        System.out.println("change GREENpad to light already");
                    }
                    else if (pad.get(j) == PadType.ORANGE){
                        orgPad.setIcon(new ImageIcon("Image\\Finalpad\\orgpad2.png"));
                        System.out.println("change ORGpad to light already");
                    }
                    else if (pad.get(j) == PadType.PURPLE){
                        purPad.setIcon(new ImageIcon("Image\\Finalpad\\purpad2.png"));
                        System.out.println("change PURpad to light already");
                    }
                    else if (pad.get(j) == PadType.YELLOW){
                        yellPad.setIcon(new ImageIcon("Image\\Finalpad\\yellpad2.png"));
                        System.out.println("change YELLpad to light already");
                    }
                }
                if (cntpatt % 2 == 0){
                    if (pad.get(j) == PadType.RED){
                        redPad.setIcon(new ImageIcon("Image\\Finalpad\\redpad1.png"));
                        System.out.println("REDback to default");
                    }
                    else if(pad.get(j) == PadType.CYAN){
                        cyanPad.setIcon(new ImageIcon("Image\\Finalpad\\cyanpad1.png"));
                        System.out.println("CYANback to default");
                    }
                    else if (pad.get(j) == PadType.LIGHT_GREEN){
                        greenPad.setIcon(new ImageIcon("Image\\Finalpad\\greenpad1.png"));
                        System.out.println("GREENback to default");
                    }
                    else if (pad.get(j) == PadType.ORANGE){
                        orgPad.setIcon(new ImageIcon("Image\\Finalpad\\orgpad1.png"));
                        System.out.println("ORGback to default");
                    }
                    else if (pad.get(j) == PadType.PURPLE){
                        purPad.setIcon(new ImageIcon("Image\\Finalpad\\pupad1.png"));
                        System.out.println("PURback to default");
                    }
                    else if (pad.get(j) == PadType.RED){
                        redPad.setIcon(new ImageIcon("Image\\Finalpad\\redpad1.png"));
                        System.out.println("REDback to default");
                    }
                    else if (pad.get(j) == PadType.YELLOW){
                        yellPad.setIcon(new ImageIcon("Image\\Finalpad\\yellpad1.png"));
                        System.out.println("YELLback to default");
                    }

                }       
                
                if (cntpatt == amount){
                    j++;
                    cntidx++;
                    if (cntidx > idx.size() - 1){
                        // j = 0;
                        blinkTimer.stop();
                        
                        // cntidx = 0;
                    }
                    try {
                        amount = idx.get(j) * 2;
                        
                    } catch (Exception x) {
                        // TODO: handle exception
                        System.err.println(x);
                        
                        gamePhase();
                        // return;
                    }
                    cntpatt = 1;
                }
                
                else {
                    cntpatt++;
                }
            }
        };
        blinkTimer = new Timer(1000, action);
        blinkTimer.start();
        // return;
    }
    private class PadActionListener implements ActionListener{
        int redcnt = 0;
        int cyancnt = 0;
        int greencnt = 0;
        int purcnt = 0;
        int orgcnt = 0;
        int yellcnt = 0;
        int totalcnt = 0;
        
        PadActionListener(){
            redcnt = 0;
            cyancnt = 0;
            greencnt = 0;
            purcnt = 0;
            orgcnt = 0;
            yellcnt = 0;
            totalcnt = 0;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            
            System.out.println("redcnt: " + redcnt);
            System.out.println("cyancnt: " + cyancnt);
            System.out.println("greencnt: " + greencnt);
            System.out.println("purcnt: " + purcnt);
            System.out.println("orgcnt: " + orgcnt);
            System.out.println("yellcnt: " + yellcnt);
            System.out.println("totalcnt before click: " + totalcnt);
            JButton input = (JButton)e.getSource();
            PadType clickedPad = null;
            if (countgen >= 6 && ans.isEmpty()){
                redcnt = 0;
                cyancnt = 0;
                greencnt = 0;
                purcnt = 0;
                orgcnt = 0;
                yellcnt = 0;
                totalcnt = 0;
            }

            if (input == redPad) {
                redcnt += 1;
                clickedPad = PadType.RED;
                ans.put(clickedPad, redcnt);
                
            }
            else if (input == cyanPad) {
                cyancnt += 1;
                clickedPad = PadType.CYAN;
                ans.put(clickedPad, cyancnt);
                
            } 
            else if (input == greenPad) {
                greencnt += 1;
                clickedPad = PadType.LIGHT_GREEN;
                ans.put(clickedPad, greencnt);
                
            } 
            else if (input == orgPad) {
                orgcnt += 1;
                clickedPad = PadType.ORANGE;
                ans.put(clickedPad, orgcnt);
                
            } 
            else if (input == purPad) {
                purcnt += 1;
                clickedPad = PadType.PURPLE;
                ans.put(clickedPad, purcnt);
                
            } 
            else if (input == yellPad) {
                yellcnt += 1;
                clickedPad = PadType.YELLOW;
                ans.put(clickedPad, yellcnt);
                
            }
            totalcnt = redcnt + cyancnt + greencnt + orgcnt + purcnt + yellcnt;
            System.out.println("ans list : " + ans);
            System.out.println("total cnt :" + totalcnt);

            if (totalcnt == cntamount){
                if (ans.equals(pattern)){
                    if (blinkTimer != null && blinkTimer.isRunning()){
                        blinkTimer.stop();
                    }
                    System.out.println("You get 10 point");
                    int score = Integer.parseInt(numScore.getText()) + 10;
                    int highscore = Integer.parseInt(numHighscore.getText());
                    numScore.setText("" + score);
                    if (score >= highscore){
                        numHighscore.setText("" + score);
                    }
                    countgen++;
                    System.out.println("restart elements");
                    list_pad.clear();
                    System.out.println("listpad clear: " + list_pad);
                    list_amount.clear();
                    System.out.println("listamount clear: " + list_amount);
                    pattern.clear();
                    System.out.println("pattern clear: " + pattern);
                    pad.clear();
                    System.out.println("pad clear: " + pad);
                    idx.clear();
                    System.out.println("idx clear: " + idx);
                    ans.clear();
                    System.out.println("ans clear: " + ans);
                    totalcnt = 0; 
                    System.out.println("totalcnt clear: " + totalcnt);
                    redcnt = 0; 
                    cyancnt = 0; 
                    greencnt = 0; 
                    purcnt = 0; 
                    orgcnt = 0; 
                    yellcnt = 0;
                    cntamount = 0;
                    cntidx = 0;
                    j = 0;
                    clickedPad = null;
                    e.setSource(null);
                    patternShow();
                }
                else{
                    numScore.setText("0");
                    System.out.println("Fail");
                    list_pad.clear();
                    list_amount.clear();
                    pattern.clear();
                    pad.clear();
                    idx.clear();
                    j = 0;
                    countgen = 1;
                    ans.clear();
                    totalcnt = 0; 
                    redcnt = 0; 
                    cyancnt = 0; 
                    greencnt = 0; 
                    purcnt = 0; 
                    orgcnt = 0; 
                    yellcnt = 0;
                    cntamount = 0;
                    cntidx = 0;
                    window.dispose();
                }
            }
            else if (totalcnt > cntamount){
                if (blinkTimer != null && blinkTimer.isRunning()){
                    blinkTimer.stop();
                }
                numScore.setText("0");
                System.out.println("Fail");
                list_pad.clear();
                list_amount.clear();
                pattern.clear();
                pad.clear();
                idx.clear();
                j = 0;
                countgen = 1;
                ans.clear();
                totalcnt = 0; 
                redcnt = 0; 
                cyancnt = 0; 
                greencnt = 0; 
                purcnt = 0; 
                orgcnt = 0; 
                yellcnt = 0;
                cntamount = 0;
                cntidx = 0;
                window.dispose();
            }
        }       
    }

}

