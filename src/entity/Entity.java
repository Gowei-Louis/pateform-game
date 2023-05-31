package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Cette classe représente une entité dans le jeu.
 */
public class Entity {

    /**
     * La position X de l'entité dans le monde.
     */
    public int worldX;

    /**
     * La position Y de l'entité dans le monde.
     */
    public int worldY;

    /**
     * La vitesse de déplacement de l'entité.
     */
    public int speed;

    /**
     * Les images pour l'animation de déplacement vers le haut.
     */
    public BufferedImage up1, up2;

    /**
     * Les images pour l'animation de déplacement vers le bas.
     */
    public BufferedImage down1, down2;

    /**
     * Les images pour l'animation de déplacement vers la gauche.
     */
    public BufferedImage left1, left2;

    /**
     * Les images pour l'animation de déplacement vers la droite.
     */
    public BufferedImage right1, right2;

    /**
     * La direction actuelle de l'entité.
     */
    public String direction;

    /**
     * Le compteur d'images pour l'animation.
     */
    public int spriteCounter = 0;

    /**
     * Le nombre total d'images pour l'animation.
     */
    public int spriteNumber = 1;

    /**
     * La zone solide de collision de l'entité.
     */
    public Rectangle solidArea;

    /**
     * La position X par défaut de la zone solide de collision.
     */
    public int solidAreaDefaultX = 0;

    /**
     * La position Y par défaut de la zone solide de collision.
     */
    public int solidAreaDefaultY = 0;

    /**
     * Indique si la collision est activée pour l'entité.
     */
    public boolean collisionOn = false;
}
