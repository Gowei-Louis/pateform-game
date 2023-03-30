package main;

import object.HealthItemObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.text.DecimalFormat;

public class UI {

    public Font ARIAL_20;
    public Font ARIAL_30;
    int messageCounter = 0;
    GamePanel gamePanel;
    double playTime;
    DecimalFormat df = new DecimalFormat("#0.00");


    BufferedImage healthIcon;

    public boolean messageOn = false;
    public String message = "";

    public UI(GamePanel gamePanel){
        this.gamePanel= gamePanel;
        this.ARIAL_20  = new Font("Arial", Font.BOLD, 20);
        this.ARIAL_30  = new Font("Arial", Font.BOLD, 30);
        healthIcon = HealthItemObject.icon;

    }

    public void showMessage(String message){
        this.message = message;
        messageOn = true;
    }

    public void draw(Graphics2D g2d){
        g2d.setFont(ARIAL_20);
        g2d.setColor(Color.WHITE);
        g2d.drawImage(healthIcon, gamePanel.tileSize/2, gamePanel.tileSize/2, gamePanel.tileSize, gamePanel.tileSize, null);
        g2d.drawString(String.valueOf(gamePanel.player.health), 65, 55);

        //60 fps so, 60 frames per second
        playTime+= (double)1/60;
        g2d.drawString("Temps : "+df.format(playTime)+"s", gamePanel.tileSize*12,55);


        if(messageOn){
            g2d.drawString(message, gamePanel.screenWidth/2 - 100, gamePanel.tileSize*3);
            messageCounter++;
        }
        if (messageCounter > 120){
            messageOn = false;
            messageCounter = 0;
        }
    }
}
