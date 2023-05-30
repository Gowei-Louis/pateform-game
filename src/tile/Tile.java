package tile;

import java.awt.image.BufferedImage;

/**
 * Cette classe représente une tuile dans le jeu.
 */
public class Tile {

    /**
     * L'image associée à la tuile.
     */
    public BufferedImage image;

    /**
     * Indique si la tuile est en collision avec d'autres objets.
     */
    public boolean collision = false;

}
