package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    /**
     * Indique si la touche de déplacement "haut" est enfoncée.
     */
    public boolean upPressed;

    /**
     * Indique si la touche de déplacement "bas" est enfoncée.
     */
    public boolean downPressed;

    /**
     * Indique si la touche de déplacement "gauche" est enfoncée.
     */
    public boolean leftPressed;

    /**
     * Indique si la touche de déplacement "droite" est enfoncée.
     */
    public boolean rightPressed;

    /**
     * Indique si la touche de débogage est enfoncée.
     */
    public boolean debugKey;

    /**
     * Indique si le jeu est en pause.
     */
    public boolean pause;

    /**
     * Référence vers le GamePanel associé.
     */
    GamePanel gamePanel;

    /**
     * Constructeur de la classe KeyHandler.
     *
     * @param gamePanel Le GamePanel associé.
     */
    public KeyHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Méthode vide, pas d'action nécessaire ici.
    }

    

    /**
     * Gère les actions à effectuer lorsqu'une touche est enfoncée.
     * Cette méthode est appelée automatiquement lorsqu'une touche du clavier est enfoncée.
     *
     * @param e l'événement KeyEvent représentant la touche enfoncée.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        // Touche de déplacement "haut"
        if (keyCode == KeyEvent.VK_Z) {
            upPressed = true;
        }
        // Touche de déplacement "bas"
        else if (keyCode == KeyEvent.VK_S) {
            downPressed = true;
        }
        // Touche de déplacement "gauche"
        else if (keyCode == KeyEvent.VK_Q) {
            leftPressed = true;
        }
        // Touche de déplacement "droite"
        else if (keyCode == KeyEvent.VK_D) {
            rightPressed = true;
        }

        // Touche de débogage
        if (keyCode == KeyEvent.VK_T) {
            debugKey = true;
        }

        // Pause du jeu
        if (keyCode == KeyEvent.VK_P) {
            gamePanel.gameState = gamePanel.gameState == gamePanel.pauseState ? gamePanel.playState : gamePanel.pauseState;
        }
    }

    /**
     * Gère les actions à effectuer lorsqu'une touche est relâchée.
     * Cette méthode est appelée automatiquement lorsqu'une touche du clavier est relâchée.
     *
     * @param e l'événement KeyEvent représentant la touche relâchée.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        // Touche de déplacement "haut"
        if (keyCode == KeyEvent.VK_Z) {
            upPressed = false;
        }
        // Touche de déplacement "bas"
        if (keyCode == KeyEvent.VK_S) {
            downPressed = false;
        }
        // Touche de déplacement "gauche"
        if (keyCode == KeyEvent.VK_Q) {
            leftPressed = false;
        }
        // Touche de déplacement "droite"
        if (keyCode == KeyEvent.VK_D) {
            rightPressed = false;
        }

        // Touche de débogage
        if (keyCode == KeyEvent.VK_T) {
            debugKey = false;
        }
    }
}
