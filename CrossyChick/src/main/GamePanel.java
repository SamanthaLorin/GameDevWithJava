package main;

import java.awt.Dimension;
import java.awt.Font;

import entity.Player;
import entity.Entity;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.KeyHandler;
import tile.TileManager;

// import for obstacle
import entity.Obstacle;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

public class GamePanel extends JPanel implements Runnable {
	//screen settings, 
    final int originalTileSize = 20; //32x32 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; //48x48 tile
    public final int maxScreenCol = 12;
    public final int maxScreenRow = 16;
    public final int screenWidth = tileSize * maxScreenCol; //768
    public final int screenHeight = tileSize * maxScreenRow; //576

    //world settings
    public final int maxWorldCol = 16;
    public final int maxWorldRow = 35;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    
    public double zoomFactor = 2.0; // zoom 

    public boolean gameOver = false; // game over   
    private BufferedImage gameOverImage;
    
    // scoring system
    private int score = 0;  
    private int highestY;

    
    //FPS
    int FPS = 60;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread; //para tuloy tuloy siyang mag-run
    public Player player = new Player(this, keyH);
    TileManager tileM = new TileManager(this);
    public CollisionChecker cChecker = new CollisionChecker(this);


    public GamePanel() {
    	
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.requestFocusInWindow();
        highestY = player.getY(); 
        startGameThread();
        loadGameOverImage();
        
        
        
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {

        double drawInterval = 1000000000 / FPS; 
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            
            if(delta >= 1) {
            	update();
            	repaint();
            	delta--;
            }
            
            if(timer >= 1000000000) {
            	
            	drawCount = 0;
            	timer = 0;
            }
        }

    }
    
    
    // obstacle
    public ArrayList<Obstacle> obstacles = new ArrayList<>();
    private Random rand = new Random();

    public void spawnObstacle() {
        for (int row = 0; row < maxWorldRow; row++) {
            for (int col = 0; col < maxWorldCol; col++) {
                if (tileM.mapTileNum[col][row] == 3 && rand.nextFloat() < 0.02) { 
                    int x = col * tileSize;
                    int y = row * tileSize;
                    boolean movingLeft = rand.nextBoolean();
                    obstacles.add(new Obstacle(this, x, y, movingLeft));
                }
            }
        }
    }

    
   

    public int getZoomedTileSize() {
        return (int) (originalTileSize * scale * zoomFactor);
    }
    
    public void update() {
        player.update();
       
        if (gameOver) return;
        player.update();
        cChecker.checkTile(player);
        cChecker.checkObstacleCollision(player);
        
        if (player.getY() < highestY) {
            score++;
            highestY = player.getY();
        }
        
        // Randomize obstacles 
        if (obstacles.size() < 20 && rand.nextInt(100) < 2) {
            spawnObstacle();
        }

        for (Obstacle obs : obstacles) {
            obs.update();
        }
    } 
       
    
    // GameOverImage
    public void loadGameOverImage() {
        try {
            gameOverImage = ImageIO.read(getClass().getResourceAsStream("/others/GameOver.png")); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void paintComponent(Graphics g) {
    	 super.paintComponent(g);
    	    Graphics2D g2 = (Graphics2D) g;

    	    g2.setColor(new Color(34, 177, 76)); // fill the rest of the map
    	    g2.fillRect(0, 0, getWidth(), getHeight());
    	    tileM.draw(g2);
    	    player.draw(g2);
    	    
    	    for (Obstacle obs : obstacles) {
                obs.draw(g2);
            }
    	    
    	    g2.setColor(Color.BLACK);
            g2.setFont(new Font("Arial", Font.BOLD, 20));
            g2.drawString("Score: " + score, 20, 30);
    	    
    	    if (gameOver) { 
    	        g2.drawImage(gameOverImage, 160, 50, screenWidth/2, screenHeight/2, null); 
    	        return; 
    	    }
    	    
    	    
    	    
    	    g2.dispose();
    	    
    	    
    	}
    
}