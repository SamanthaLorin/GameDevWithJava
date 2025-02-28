package main;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		 SwingUtilities.invokeLater(new Runnable() {
		        public void run() {
		            new start().setVisible(true);
		        }
		    });
		
		
	    }
}

