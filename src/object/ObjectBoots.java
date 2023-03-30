package object;

import java.io.InputStream;

public class ObjectBoots extends Object{

    public ObjectBoots(int worldX, int worldY) {
        super(worldX, worldY);
        InputStream inputStream = getClass().getResourceAsStream("/object_data/boots.png");
        if (inputStream == null) {
            throw new NullPointerException("Le fichier boots.png est introuvable dans le dossier object_data.");
        }
        name = "boots";
    }

}
