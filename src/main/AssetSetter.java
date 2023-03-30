package main;

import object.HealthItemObject;

import java.awt.*;
import java.io.IOException;

public class AssetSetter {

    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    public void setObject() throws IOException {
        gamePanel.objects[0] = new HealthItemObject(gamePanel.tileSize* 26 , gamePanel.tileSize* 7,gamePanel);
        gamePanel.objects[1] = new HealthItemObject(gamePanel.tileSize* 26 , gamePanel.tileSize* 4, gamePanel);
        gamePanel.objects[1] = new HealthItemObject(gamePanel.tileSize* 20 , gamePanel.tileSize* 46, gamePanel);
        gamePanel.objects[1] = new HealthItemObject(gamePanel.tileSize* 5 , gamePanel.tileSize* 40, gamePanel);
    }

    public void draw(Graphics2D g2d){
        for (int i = 0; i < gamePanel.objects.length; i++) {
            if(gamePanel.objects[i] != null){
                gamePanel.objects[i].draw(g2d, gamePanel);
            }
        }
    }
}
