package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import tile.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * Cette classe représente le joueur dans le jeu.
 */
public class Player extends Entity{

    /**
     * Le panneau de jeu auquel le joueur est associé.
     */
    GamePanel gamePanel;

    /**
     * Le gestionnaire des entrées clavier.
     */
    KeyHandler keyHandler;

    /**
     * La position X de l'écran où le joueur est dessiné.
     */
    public final int screenX;

    /**
     * La position Y de l'écran où le joueur est dessiné.
     */
    public final int screenY;

    /**
     * La santé du joueur.
     */
    public int health = 100;

    /**
     * Constructeur de la classe Player.
     * @param gamePanel Le panneau de jeu.
     * @param keyHandler Le gestionnaire des entrées clavier.
     */
    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        screenX = gamePanel.screenWidth / 2 -(gamePanel.tileSize/2);
        screenY = gamePanel.screenHeight/ 2- (gamePanel.tileSize/2);
        setDefaultValues();
        getPlayerImage();

        solidArea = new Rectangle();
        solidArea.x = 8; // La zone de collision est à 8 pixels à gauche et à droite du joueur
        solidArea.y = 16; // La zone de collision est à 16 pixels en haut et en bas du joueur
        solidArea.width = gamePanel.tileSize - (16*2); // La zone de collision est à 16 pixels en haut et en bas du joueur
        solidArea.height = gamePanel.tileSize - (16*2); // La zone de collision est à 16 pixels en haut et en bas du joueur
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    /**
     * Initialise les valeurs par défaut du joueur.
     */
    public void setDefaultValues() {
        worldX = gamePanel.tileSize * 23; // Le centre de la carte du monde est en 23,21 selon le fichier map01.txt
        worldY = gamePanel.tileSize * 21;
        speed = 3;
        direction = "down";
    }

    /**
     * Récupère les images du joueur.
     */
    public void getPlayerImage() {
        up1 = setup("boy_up_1");
        up2 = setup("boy_up_2");
        down1 = setup("boy_down_1");
        down2 = setup("boy_down_2");
        left1 = setup("boy_left_1");
        left2 =  setup("boy_left_2");
        right1 = setup("boy_right_1");
        right2 = setup("boy_right_2");
    }

    /**
     * Charge une image du joueur à partir d'un fichier.
     * @param imageName Le nom de l'image à charger.
     * @return L'image chargée.
     */
    public BufferedImage setup(String imageName){
        BufferedImage image = null;
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/"+imageName+".png")));
            image = UtilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }

    /**
     * Ramasse un objet à la position spécifiée dans le monde.
     * @param objectIndex L'index de l'objet dans le tableau des objets du GamePanel.
     */
    public void pickUpItem(int objectIndex){
        if(objectIndex != 999){

            String objectName = gamePanel.objects[objectIndex].name;
            switch (objectName) {
                case "healthItem":
                    this.health += 10;
                    gamePanel.playSoundEffect(1);
                    gamePanel.ui.showMessage("Votre vie augmente de 10");
            }

            // Supprime l'objet du tableau gamePanel.objects (le supprime également de l'écran)
            gamePanel.objects[objectIndex] = null;
        }
    }

    /**
     * Met à jour l'état du joueur en fonction des entrées clavier et de la collision.
     */
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

            // Vérification des collisions, blocage du mouvement s'il y a collision
            collisionOn = false;
            gamePanel.collisionChecker.checkTile(this);

            // Vérification des collisions avec les autres objets
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

    /**
     * Dessine le joueur sur le panneau de jeu.
     * @param g2d Le contexte graphique.
     */
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
