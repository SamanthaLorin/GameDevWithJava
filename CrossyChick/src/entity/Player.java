package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

	//Instantiate the class para accessible siya sa player
    GamePanel gp;
    KeyHandler keyH;
    
    public final int screenX;
    public final int screenY;
    
    
    
    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        setDefaultValues();
        getPlayerImage();
        
        
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 8;
        worldY = gp.tileSize * 31;
        speed = 2;
        direction = "up";
        
    }
    
    public void resetPosition() {
        worldX = gp.tileSize * 8;  
        worldY = gp.tileSize * 31; 
        gp.zoomFactor = 1.0;  
    }

    
    
    public void getPlayerImage() {
        
        try {
            System.out.println("i am trying");
            //System.out.println("Resource path: " + getClass().getResourceAsStream("C:/Users/Samantha Lorin/Downloads/graphics and assets/cat - avatar1.png"));
            
            
            up = ImageIO.read(getClass().getResourceAsStream("/player/cat-up-and-down.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/player/cat-up-and-down.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/player/cat-left-removebg-preview.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/player/cat-right-removebg-preview.png"));
            
            if (up == null) {
                System.out.println("file not found");
            } else {
                System.out.println("file found!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void update() {
        if (keyH.upPressed == true) {
            direction = "up";
            worldY -= 10;
        } else if (keyH.downPressed == true) {
            direction = "down";
            worldY += 10;
        } else if (keyH.leftPressed == true) {
            direction = "left";
            worldX -= 10;
        } else if (keyH.rightPressed == true) {
            direction = "right";
            worldX += 10;
        }

        
        if (worldX < 0) worldX = gp.worldWidth - gp.tileSize; 
        if (worldX > gp.worldWidth - gp.tileSize) worldX = 0;
        if (worldY < 0) worldY = gp.worldHeight - gp.tileSize;
        if (worldY > gp.worldHeight - gp.tileSize) worldY = 0;

        if (keyH.zoomInPressed) {
            gp.zoomFactor = Math.min(3.0, gp.zoomFactor + 0.1); 
        } 
        if (keyH.zoomOutPressed) {
            gp.zoomFactor = Math.max(1.0, gp.zoomFactor - 0.1); 
        }
        
        int playerLeft = worldX;
        int playerRight = worldX + gp.tileSize;
        int playerTop = worldY;
        int playerBottom = worldY + gp.tileSize;

        int leftCol = playerLeft / gp.tileSize;
        int rightCol = playerRight / gp.tileSize;
        int topRow = playerTop / gp.tileSize;
        int bottomRow = playerBottom / gp.tileSize;
        
        
        
    }
     
    

    public void draw(Graphics2D g2) {
        /*
        g2.setColor(Color.white);
        //PANOO ma-get yung variable from another package??? WAG NA TE ITIGIL NA NATIN TO 
        //do this: field.variable
        g2.fillRect(x, y, gp.tileSize, gp.tileSize); di na to kailangan sice we are going to use image
         */
        BufferedImage image = null;
       
        
        switch(direction){
            case "up":
                image = up;
                break;
            case "down":
            	image = down;
            	break;
            case "left":
            	image = left;
            	break;
            case "right":
            	image = right;
            	break;
        }
        //yung g2 para siya magpakita ng mga graphics
        
        int drawSize = (int) (gp.tileSize * gp.zoomFactor); 
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

    
    }

	public int getY() {
		return worldY;
	}
    
    
    
}