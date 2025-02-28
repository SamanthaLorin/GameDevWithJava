package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.imageio.ImageIO;
import main.GamePanel;

public class Obstacle {
	 private GamePanel gp;
	    private BufferedImage image;
	    public int x, y;
	    private int speed;
	    private String direction;
		private boolean movingLeft;

	    public Obstacle(GamePanel gp, int x, int y, boolean movingLeft) {
	        this.gp = gp;
	        this.x = x;
	        this.y = y;
	        this.movingLeft = movingLeft;
	        this.speed = new Random().nextInt(3) + 2; 
	        loadCarImage();
	    }
	    

	    private void loadCarImage() {
	        try {
	            int variant = new Random().nextInt(5); // Picks a number from 0 to 4
	            String direction = movingLeft ? "L" : "R";
	            
	            // Correct path formatting
	            String fileName = variant == 0 
	                ? "/obstacles/car-obstacle-" + direction + ".png"
	                : "/obstacles/car-obstacle-" + direction + variant + ".png";

	            image = ImageIO.read(getClass().getResourceAsStream(fileName));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public void update() {
	        if (movingLeft) {
	            x -= speed;
	            if (x < -gp.tileSize) {
	                x = gp.screenWidth;
	            }
	        } else {
	            x += speed;
	            if (x > gp.screenWidth) {
	                x = -gp.tileSize;
	            }
	        }
	    }
	    
	    
	    
	    public void draw(Graphics2D g2) {
	    int screenX = x - gp.player.worldX + gp.player.screenX;
	    int screenY = y - gp.player.worldY + gp.player.screenY;

	    	    
	    if (x + gp.tileSize > gp.player.worldX - gp.player.screenX &&
	    	x - gp.tileSize < gp.player.worldX + gp.player.screenX &&
	    	y + gp.tileSize > gp.player.worldY - gp.player.screenY &&
	    	y - gp.tileSize < gp.player.worldY + gp.player.screenY) {
	    	        
	    	        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	    	    }
	    	}
}


