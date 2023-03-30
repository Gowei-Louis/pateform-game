package object;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class HealthItemObject extends Object {



    public GamePanel gamePanel;
    public static final BufferedImage icon;

    static {
        try {
            icon = ImageIO.read(Objects.requireNonNull(HealthItemObject.class.getResourceAsStream("/object_data/heart.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public HealthItemObject(int worldX, int worldY, GamePanel gamePanel) throws IOException {
        super(worldX, worldY);
        name = "healthItem";
        this.gamePanel = gamePanel;

        InputStream inputStream = getClass().getResourceAsStream("/object_data/heart.png");
        if (inputStream == null) {
            throw new NullPointerException("Le fichier heart.png est introuvable dans le dossier object_data.");
        }

        image = ImageIO.read(inputStream);
        UtilityTool.scaleImage(image,gamePanel.tileSize, gamePanel.tileSize);

    }










}
