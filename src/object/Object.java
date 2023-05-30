package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Cette classe représente un objet du jeu.
 */
public class Object {

    /**
     * La position X de l'objet dans le monde.
     */
    public int worldX;

    /**
     * La position Y de l'objet dans le monde.
     */
    public int worldY;

    /**
     * L'image de l'objet.
     */
    public BufferedImage image;

    /**
     * Le nom de l'objet.
     */
    public String name;

    /**
     * Indique si l'objet est en collision avec d'autres objets.
     */
    public boolean collision = false;

    /**
     * La zone solide de l'objet représentée par un rectangle.
     * Par défaut, la taille du rectangle est de 48x48.
     */
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);

    /**
     * La position X par défaut de la zone solide de l'objet.
     */
    public int solidAreaDefaultX = 0;

    /**
     * La position Y par défaut de la zone solide de l'objet.
     */
    public int solidAreaDefaultY = 0;

    /**
     * Constructeur de la classe Object.
     *
     * @param worldX La position X de l'objet dans le monde.
     * @param worldY La position Y de l'objet dans le monde.
     */
    public Object(int worldX, int worldY) {
        this.worldX = worldX;
        this.worldY = worldY;
    }

    /**
     * Dessine l'objet sur le panneau de jeu.
     *
     * @param g2d        Le contexte graphique 2D.
     * @param gamePanel  Le panneau de jeu.
     */
    public void draw(Graphics2D g2d, GamePanel gamePanel) {
        int screenX = worldX - gamePanel.player.worldX + gamePanel.screenWidth / 2;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.screenHeight / 2;

        if (worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.screenWidth / 2 && worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.screenWidth / 2 &&
                worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.screenHeight / 2 && worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.screenHeight / 2) {
            g2d.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
        }
    }
}
