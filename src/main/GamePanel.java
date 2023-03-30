package main;
import entity.Player;
import object.Object;
import tile.TileManager;

import java.awt.*;
import java.io.IOException;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS

    final int originalTileSize = 16; // 16x16 original tile imported from source folder
    final int scale = 3 ; // 3x scale on each original tile

    public final int tileSize = originalTileSize * scale; // (16 * 3)48x48 tile

    //ratio 4/3
    public final int maxScreenCol = 16; // number of column of tile
    public final int maxScreenRow = 12; // number of row of tile
    public final int screenHeight = maxScreenRow * tileSize; // will make the x resolution 16 tiles wide
    public final int screenWidth = maxScreenCol * tileSize; // will make the y resolution 12 tiles high

    TileManager tileManager;
    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    int FPS = 60;

    //word maps
    public int maxWorldCol = 50;
    public int maxWorldRow = 50;

    public CollisionChecker collisionChecker = new CollisionChecker(this);

    public Player player = new Player(this, keyHandler);

    public Object[] objects = new Object[20];
    public AssetSetter assetSetter = new AssetSetter(this);

    // SYSTEM SETTINGS
    public Sound music = new Sound();
    public Sound soundEffect = new Sound();

    public UI ui = new UI(this);


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(keyHandler);
        this.tileManager = new TileManager(this);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        long drawInterval = 1000000000/FPS; // draw every 0.0166 seconds
        long nextDrawTime = System.nanoTime() + drawInterval;


        while(gameThread != null) {

            update();

            repaint();
            try {
                double remainingTime = (nextDrawTime - System.nanoTime());
                remainingTime = remainingTime / 1000000;

                if( remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long)remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
               // TODO Auto-generated catch block
            }
        }

    }

    public void setupGame() throws IOException {
        assetSetter.setObject();
        playMusic(0);
    }

    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        tileManager.draw(g2d);
        assetSetter.draw(g2d);
        player.draw(g2d);
        ui.draw(g2d);

    }

    public void playMusic(int i){
        music.setFile(i);
        music.playSound();
        music.loopSound();
    }

    public void stopMusic(){
        music.stop();
    }

    public void playSoundEffect(int i){
        soundEffect.setFile(i);
        soundEffect.playSound();
    }
}
