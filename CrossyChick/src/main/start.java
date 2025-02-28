package main;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class start extends JFrame {
	    private static AudioInputStream stream;
		private static Clip clip;
		private JButton btnStart;
		private JButton instrBtn;
	    private JLabel lblGameTitle;
	    private JPanel pnlBg;
	    private String audioFileName = "Intro Music.wav";
	    private JToggleButton soundBtn;
	    
	    
	    // Variables in Instructions JFrame
	    private JPanel pnlInstr;
	    private JLabel lblTitleName;
	    private JLabel lblHow;
	    private JLabel lblInstr1;
	    private JLabel lblInstr2;
	    private JLabel lblText1;
	    private JLabel lblText2;
	    private JLabel lblText3;
	    

	    public start() {
	        initComponents();
	         playAudio();
	        
	    }

	    private void initComponents() {
	        
	    	// Background Color
	        pnlBg = new JPanel();
	        pnlBg.setBackground(new Color(101, 221, 255));
	        pnlBg.setLayout(null); 

	        // Game Title "Cross the Road"
	        lblGameTitle = new JLabel(new ImageIcon("crosstheroadtxt.png"));
	        lblGameTitle.setBounds(180, -5, lblGameTitle.getPreferredSize().width, lblGameTitle.getPreferredSize().height); 

	        // START Button
	        btnStart = new JButton(new ImageIcon("startBtn.png"));
	        
	        btnStart.setBorder(null);
	        btnStart.setContentAreaFilled(false);
	        btnStart.setFocusPainted(false);
	        btnStart.setBounds(690, 400, btnStart.getPreferredSize().width, btnStart.getPreferredSize().height);
	        
	        // huhuhuhu
	        btnStart.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                stopAudio(); // Stop background music
	                dispose();   // Close start screen
	                new GameFrame(); // Open the game
	            }
	        });
	        
	        // INSTRUCTIONS Button
	        instrBtn = new JButton(new ImageIcon("instrBtn.png")); 
	        
	        instrBtn.setBorder(null);
	        instrBtn.setContentAreaFilled(false);
	        instrBtn.setFocusPainted(false);                   
	        instrBtn.setBounds(20, 700, 100, 50);
	        
	        // Action if the Instruction Button is clicked
	        instrBtn.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                JFrame instrFrame = new JFrame("Instructions");
	                instrFrame.setSize(500, 300); 
	                instrFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	                instrFrame.setLocationRelativeTo(null); 


	                pnlInstr = new JPanel();
	                pnlInstr.setBackground(Color.WHITE); 
	                pnlInstr.setLayout(null); 


	                lblTitleName = new JLabel(new ImageIcon("CTR.png")); 
	                lblTitleName.setBounds(80, 10, lblTitleName.getPreferredSize().width, lblTitleName.getPreferredSize().height);
	                
	                lblHow = new JLabel("How to Play:");
	                lblHow.setBounds(20, 55, 200, 30); 
	                
	                lblInstr1 = new JLabel("1. Use arrow keys to move.");
	                lblInstr1.setBounds(20, 80, 300, 30);
	                
	                lblInstr2 = new JLabel("2. Avoid obstacles to win!");
	                lblInstr2.setBounds(20, 100, 300, 30);
	                
	                // AYAW MAGNEWLINE KAINIS 
	                lblText1 = new JLabel("In Cross the Road you have to dodge traffic, hop across logs, ");
	                lblText1.setBounds(20, 140, 500, 30);
	                
	                lblText2 = new JLabel("sidestep trains and collect coins.");
	                lblText2.setBounds(20, 160, 500, 30);
	                
	                lblText3 = new JLabel("Make sure you don’t stay still for too long or you’re toast!");
	                lblText3.setBounds(20, 200, 500, 30);

	            	
	                pnlInstr.add(lblTitleName);
	                pnlInstr.add(lblHow);
	                pnlInstr.add(lblInstr1);
	                pnlInstr.add(lblInstr2);
	                pnlInstr.add(lblText1);
	                pnlInstr.add(lblText2);
	                pnlInstr.add(lblText3);
	                
	                instrFrame.add(pnlInstr);
	                instrFrame.setVisible(true);
	               
	            }
	        });
	        

	        pnlBg.add(lblGameTitle);
	        pnlBg.add(btnStart);
	        pnlBg.add(instrBtn); 

	        setContentPane(pnlBg);
	        setSize(1500, 800); 
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null); 
	        setResizable(false);
	    }
	    
	    
	    // AUDIO
	    public void playAudio() {
			
			try {
			File audioPath = new File(audioFileName);
			start.stream = AudioSystem.getAudioInputStream(audioPath);
			start.clip = AudioSystem.getClip();
			start.clip.open(start.stream); 
			start.clip.start();
			} // Try Catch end
			catch(Exception e) {
				e.printStackTrace();
			}
		}

	   public void stopAudio() {
		   if (clip != null && clip.isRunning()) {
	            clip.stop();
	            clip.close();
	   }
	   }
}
