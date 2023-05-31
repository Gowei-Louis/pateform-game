package main;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Cette classe fournit des méthodes utilitaires pour le traitement des images.
 */
public class UtilityTool {

    /**
     * Redimensionne une image à la taille spécifiée.
     * @param original L'image d'origine à redimensionner.
     * @param width La largeur souhaitée de l'image redimensionnée.
     * @param height La hauteur souhaitée de l'image redimensionnée.
     * @return L'image redimensionnée.
     */
    public static BufferedImage scaleImage(BufferedImage original, int width, int height){

        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2d = scaledImage.createGraphics();
        g2d.drawImage(original, 0, 0, width, height, null);

        return scaledImage;
    }
}
