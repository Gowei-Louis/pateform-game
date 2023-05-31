package main;

import javax.swing.*;
import java.io.IOException;

/**
 * Classe principale du jeu.
 * Elle initialise et lance l'application du moteur de jeu.
 */
public class main {
    public static void main(String[] args) throws IOException {
        // Création de la fenêtre principale
        JFrame window = new JFrame("2D Game Engine");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Game Engine");

        // Création du panneau de jeu
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();
        window.add(new GamePanel());
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        // Configuration et lancement du jeu
        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}
