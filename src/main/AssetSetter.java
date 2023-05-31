package main;

import object.HealthItemObject;

import java.awt.*;
import java.io.IOException;

/**
 * Classe chargée de configurer les éléments du jeu.
 * Elle initialise et dessine les objets du jeu, tels que les objets de récupération de santé.
 */
public class AssetSetter {

    GamePanel gamePanel;

    /**
     * Constructeur de la classe AssetSetter.
     *
     * @param gamePanel le panneau de jeu associé à l'AssetSetter.
     */
    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    /**
     * Méthode pour configurer les objets du jeu.
     * Cette méthode crée et place les objets de récupération de santé dans le jeu.
     *
     * @throws IOException si une erreur se produit lors de la lecture des ressources.
     */
    public void setObject() throws IOException {
        gamePanel.objects[0] = new HealthItemObject(gamePanel.tileSize * 26, gamePanel.tileSize * 7, gamePanel);
        gamePanel.objects[1] = new HealthItemObject(gamePanel.tileSize * 26, gamePanel.tileSize * 4, gamePanel);
        gamePanel.objects[1] = new HealthItemObject(gamePanel.tileSize * 20, gamePanel.tileSize * 46, gamePanel);
        gamePanel.objects[1] = new HealthItemObject(gamePanel.tileSize * 5, gamePanel.tileSize * 40, gamePanel);
    }

    /**
     * Méthode pour dessiner les objets du jeu.
     * Cette méthode parcourt les objets du jeu et les dessine sur le panneau de jeu.
     *
     * @param g2d le contexte graphique utilisé pour le dessin.
     */
    public void draw(Graphics2D g2d) {
        for (int i = 0; i < gamePanel.objects.length; i++) {
            if (gamePanel.objects[i] != null) {
                gamePanel.objects[i].draw(g2d, gamePanel);
            }
        }
    }
}
