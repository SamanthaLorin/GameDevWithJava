package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;


import javax.imageio.ImageIO;

import main.GamePanel;


//kapag inimport mo yung class, makukuha mo yung mga variables nito allowing code reusability

public class TileManager {
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		tile = new Tile[10];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();
		generateRandomMap();
		shiftMapUp();
		//loadMap("/map/map.txt");
	}
	
	public void getTileImage() {
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/background/grass.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/background/bush.png"));
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/background/chair.png"));
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/background/gray.png"));
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/background/white.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	public void loadMap(String filePath) {
		 try {
		        InputStream is = getClass().getResourceAsStream(filePath);
		        if (is == null) {
		            System.out.println("Resource not found: " + filePath);
		            return; 
		        }
		        BufferedReader br = new BufferedReader(new InputStreamReader(is)); 
		        
		        int col = 0;
		        int row = 0;
		        
		        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
		            String line = br.readLine();
		            if (line == null) break; 
		            
		            String numbers[] = line.split(" ");
		            for (col = 0; col < gp.maxWorldCol; col++) {
		                int num = Integer.parseInt(numbers[col]);
		                mapTileNum[col][row] = num;
		            }
		            row++;
		            col = 0; 
		        }
		        br.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
		*/
	
	public void generateRandomMap() {
		 Random rand = new Random();
		    for (int row = 0; row < gp.maxWorldRow; row++) {
		        boolean isRoad = rand.nextFloat() < 0.2; 
		        for (int col = 0; col < gp.maxWorldCol; col++) {
		            if (isRoad) {
		                mapTileNum[col][row] = 3; 
		            } else {
		                int randTile = rand.nextInt(10);
		                if (randTile < 6) { 
		                    mapTileNum[col][row] = 0; 
		                } else { 
		                    mapTileNum[col][row] = rand.nextInt(2) + 1; 
		                }
		            }
		        }
		    }
		}
	
	public void shiftMapUp() {
		  for (int row = 0; row < gp.maxWorldRow - 1; row++) {
		        for (int col = 0; col < gp.maxWorldCol; col++) {
		            mapTileNum[col][row] = mapTileNum[col][row + 1];
		        }
		    }

		    Random rand = new Random();
		    int newRow = gp.maxWorldRow - 1;
		    boolean isRoad = rand.nextFloat() < 0.3; 

		    if (isRoad) {
		        for (int col = 0; col < gp.maxWorldCol; col++) {
		            mapTileNum[col][newRow] = 3; 
		        }
		    } else {
		        for (int col = 0; col < gp.maxWorldCol; col++) {
		            int randTile = rand.nextInt(10);
		            if (randTile < 6) { 
		                mapTileNum[col][newRow] = 0;
		            } else { 
		                mapTileNum[col][newRow] = rand.nextInt(2) + 1; 
		            }
		        }
		    }
		}

	
	public void draw(Graphics2D g2) {
		int worldCol = 0;
		int worldRow = 0;
		
		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			int tileNum = mapTileNum[worldCol][worldRow];
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
					worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
					worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
					worldY - gp.tileSize < gp.player.worldY + gp.player.screenY ) {
				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize,gp.tileSize,null);
			}
			worldCol++;
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
			
			
		
		}
	}
}