package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;
    public int health = 100;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        screenX = gamePanel.screenWidth / 2 -(gamePanel.tileSize/2);
        screenY = gamePanel.screenHeight/ 2- (gamePanel.tileSize/2);
        setDefaultValues();
        getPlayerImage();

        solidArea = new Rectangle();
        solidArea.x = 8; // collision area is 8 pixels from the left and right of the player
        solidArea.y = 16; // collision area is 16 pixels from the top and bottom of the player
        solidArea.width = gamePanel.tileSize - (16*2); // collision area is 16 pixels from the top and bottom of the player
        solidArea.height = gamePanel.tileSize - (16*2); // collision area is 16 pixels from the top and bottom of the player
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void setDefaultValues() {
        worldX = gamePanel.tileSize * 23; // center of the word map is 23,21 according to the map01.txt
        worldY = gamePanel.tileSize * 21;
        speed = 3;
        direction = "down";

    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_1.png")));
            left2 =  ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_2.png")));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pickUpItem(int objectIndex){
        if(objectIndex != 999){

            String objectName = gamePanel.objects[objectIndex].name;
            switch (objectName) {
                case "healthItem":
                    this.health += 10;
                    gamePanel.playSoundEffect(1);
                    gamePanel.ui.showMessage("Votre vie augmente de 10");
            }


            //delete the object from the gamePanel.objects array (also removes it from the screen)
            gamePanel.objects[objectIndex] = null;
        }
    }

    public void update(){

        if(keyHandler.upPressed||keyHandler.downPressed||keyHandler.leftPressed||keyHandler.rightPressed) {
            if(keyHandler.upPressed) {
                direction = "up";
            }
            if(keyHandler.downPressed) {
                direction = "down";
            }
            if(keyHandler.leftPressed) {
                direction = "left";
            }
            if(keyHandler.rightPressed){
                direction = "right";
            }


            //checking for collision, blocking movement if there is a
            collisionOn = false;
            gamePanel.collisionChecker.checkTile(this);
            //checking for collision with other objects
            int objectIndex = gamePanel.collisionChecker.checkObject(this, true);
            pickUpItem(objectIndex);

            if(!collisionOn){
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }

            }
            spriteCounter++;
        }

        if(spriteCounter > 12) {
            spriteNumber = spriteNumber == 1 ? 2 : 1;
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2d){
        BufferedImage image = switch (direction) {
            case "up" -> spriteNumber == 1 ? up1 : up2;
            case "down" -> spriteNumber == 1 ? down1 : down2;
            case "left" -> spriteNumber == 1 ? left1 : left2;
            case "right" -> spriteNumber == 1 ? right1 :right2;
            default -> null;
        };

        g2d.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }

}

