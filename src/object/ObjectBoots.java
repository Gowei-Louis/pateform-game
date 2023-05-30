package object;

import java.io.InputStream;

/**
 * Cette classe représente un objet de type "ObjectBoots" (objet de bottes) dans le jeu.
 * Il s'agit d'un sous-classe de la classe "Object".
 */
public class ObjectBoots extends Object {

    /**
     * Constructeur de la classe ObjectBoots.
     *
     * @param worldX La position X de l'objet dans le monde.
     * @param worldY La position Y de l'objet dans le monde.
     */
    public ObjectBoots(int worldX, int worldY) {
        super(worldX, worldY);

        // Lecture de l'image de l'objet à partir du fichier boots.png
        InputStream inputStream = getClass().getResourceAsStream("/object_data/boots.png");
        if (inputStream == null) {
            throw new NullPointerException("Le fichier boots.png est introuvable dans le dossier object_data.");
        }

        // Affectation du nom de l'objet
        name = "boots";
    }

}
