package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Entity {

	  public int worldX, worldY;
	    public int speed;
	    public BufferedImage up, down, left, right;
	    public String direction;
		public boolean collisionOn;
		
		public Rectangle solidArea; 
		public Entity() {
		    solidArea = new Rectangle(0, 0, 48, 48); // Default size, adjust as needed
		}
	}