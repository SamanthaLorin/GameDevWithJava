package main;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed;
   
    public void keyTyped(KeyEvent e){
        
    }
    
    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode(); // get uni code for space bar
        
        
        if(code == KeyEvent.VK_UP) {
        	upPressed = true;
        }
        
        if(code == KeyEvent.VK_DOWN) {
        	downPressed = true;
        }
        
        if(code == KeyEvent.VK_LEFT) {
        	leftPressed = true;
        }
        
        if(code == KeyEvent.VK_RIGHT) {
    	   rightPressed = true;
        }
    }
    
    public void keyReleased(KeyEvent e){
        int code = e.getKeyCode();
        
        if(code == KeyEvent.VK_UP) {
        	upPressed = false;
        }
        
        if(code == KeyEvent.VK_DOWN) {
        	downPressed = false;
        }
        
        if(code == KeyEvent.VK_LEFT) {
        	leftPressed = false;
        }
        
        if(code == KeyEvent.VK_RIGHT) {
    	   rightPressed = false;
        }
    }
}