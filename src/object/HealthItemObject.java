package object;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * Cette classe représente un objet de type "HealthItem" (objet de santé) dans le jeu.
 * Il s'agit d'un sous-classe de la classe "Object".
 */
public class HealthItemObject extends Object {

    /**
     * Le panneau de jeu associé à l'objet.
     */
    public GamePanel gamePanel;

    /**
     * L'icône de l'objet de santé (image statique partagée par toutes les instances).
     */
    public static final BufferedImage icon;

    static {
        try {
            // Chargement de l'icône à partir du fichier heart.png
            icon = ImageIO.read(Objects.requireNonNull(HealthItemObject.class.getResourceAsStream("/object_data/heart.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Constructeur de la classe HealthItemObject.
     *
     * @param worldX     La position X de l'objet dans le monde.
     * @param worldY     La position Y de l'objet dans le monde.
     * @param gamePanel  Le panneau de jeu associé à l'objet.
     * @throws IOException Si une erreur se produit lors de la lecture de l'image de l'objet.
     */
    public HealthItemObject(int worldX, int worldY, GamePanel gamePanel) throws IOException {
        super(worldX, worldY);
        name = "healthItem";
        this.gamePanel = gamePanel;

        // Lecture de l'image de l'objet à partir du fichier heart.png
        InputStream inputStream = getClass().getResourceAsStream("/object_data/heart.png");
        if (inputStream == null) {
            throw new NullPointerException("Le fichier heart.png est introuvable dans le dossier object_data.");
        }
        image = ImageIO.read(inputStream);

        // Mise à l'échelle de l'image à la taille du tuile du panneau de jeu
        UtilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
    }
}
