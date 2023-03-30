package main;

import javax.swing.*;
import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        JFrame window = new JFrame("2D Game Engine");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Game Engine");


        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.add(new GamePanel());
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();


    }
}

