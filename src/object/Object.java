package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Object {


    public int worldX, worldY;
    public BufferedImage image;

    public String name;
    public boolean collision = false;
    public Rectangle solidArea = new Rectangle(0,0, 48,48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;


    public Object(int worldX, int worldY) {
        this.worldX = worldX;
        this.worldY = worldY;
    }

    public void draw(Graphics2D g2d, GamePanel gamePanel) {
        int screenX = worldX - gamePanel.player.worldX + gamePanel.screenWidth/2;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.screenHeight/2;

        if(worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.screenWidth/2 && worldX  - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.screenWidth/2 &&
                worldY  + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.screenHeight/2 && worldY  - gamePanel.tileSize  < gamePanel.player.worldY + gamePanel.screenHeight/2){
            g2d.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
        }



    }
}
