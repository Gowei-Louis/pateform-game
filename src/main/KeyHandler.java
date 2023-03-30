package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {


    public boolean upPressed, downPressed, leftPressed, rightPressed = false, debugKey=false, pause=false;
    GamePanel gamePanel;


    public KeyHandler(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_Z) {
            upPressed = true;
        }
        else if(keyCode == KeyEvent.VK_S) {
            downPressed = true;
        }
        else if(keyCode == KeyEvent.VK_Q) {
            leftPressed = true;
        }
        else if(keyCode == KeyEvent.VK_D) {
            rightPressed = true;
        }

        //DEBUG

        if(keyCode == KeyEvent.VK_T){
            debugKey = true;
        }

        //PAUSING THE GAME

        if (keyCode == KeyEvent.VK_P){
            gamePanel.gameState = gamePanel.gameState == gamePanel.pauseState ? gamePanel.playState : gamePanel.pauseState;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if(keyCode == KeyEvent.VK_Z) {
            upPressed = false;
        }
        if(keyCode == KeyEvent.VK_S) {
            downPressed = false;
        }
        if(keyCode == KeyEvent.VK_Q) {
            leftPressed = false;
        }
        if(keyCode == KeyEvent.VK_D) {
            rightPressed = false;
        }
        //DEBUG
        if(keyCode == KeyEvent.VK_T){
            debugKey = false;
        }
    }
}
