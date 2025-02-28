package main;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
	  public GameFrame() {
	        setTitle("Crossy Chicken");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setResizable(false);
	        setLayout(new BorderLayout());

	        GamePanel gamePanel = new GamePanel(); 
	        add(gamePanel);

	        pack();
	        setLocationRelativeTo(null);
	        setVisible(true);
	    }
	}

