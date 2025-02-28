package main;

import entity.Entity;
import entity.Obstacle;

public class CollisionChecker {
	
	GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                break;
            default:
                return;
        }

        // **Check for collision with unsafe tiles (tile[1] and tile[2])**
        if (gp.tileM.tile[tileNum1] != null && gp.tileM.tile[tileNum1].collision ||
        	    gp.tileM.tile[tileNum2] != null && gp.tileM.tile[tileNum2].collision) {
        	    entity.collisionOn = true;
        	    gp.gameOver = true; // Set game over
        	    gp.loadGameOverImage(); // Load Game Over image
        }
    }

    public void checkObstacleCollision(Entity entity) {
        for (Obstacle obstacle : gp.obstacles) {
            if (entity.worldX < obstacle.x + gp.tileSize &&
                entity.worldX + entity.solidArea.width > obstacle.x &&
                entity.worldY < obstacle.y + gp.tileSize &&
                entity.worldY + entity.solidArea.height > obstacle.y) {
                
                gp.gameOver = true; // Set game over
                gp.loadGameOverImage(); // Load Game Over image
                return;
            }
        }
    }
}