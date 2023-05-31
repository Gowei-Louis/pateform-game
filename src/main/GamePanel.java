package main;
import entity.Player;
import object.Object;
import tile.TileManager;

import java.awt.*;
import java.io.IOException;
import javax.swing.*;


/**
 * GamePanel est la classe qui gère les différents parametre du jeu, comme l'indique tous ses attributs.
 * Elle s'occupe également de lancer le jeu et de le mettre en pause, d'afficher au bon moment chaque 'repaint'.
 */
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
    KeyHandler keyHandler = new KeyHandler(this);
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


    //game state
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;


    /**
     *
     */
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(keyHandler);
        this.tileManager = new TileManager(this);
    }

    /**En démarrant le thread, l'exécution du code se poursuivra de manière concurrente avec le reste du programme.
     * Le thread exécutera la méthode run() qui a été implémentée dans la classe, ou dans une classe qui a été passée
     * comme argument au constructeur Thread. Le code spécifique à l'exécution du jeu peut être inclus dans la méthode run().
     */

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * On peint notre fenêtre, avec un interval défini par drawInterval.
     * On récuperer le temps actuel, et on ajoute le temps de l'intervalle pour connaitre le timing du prochain affichage.
     * Si nextDrawTime n'est égal à System.nanoTime(), on fait une pause, autrement on repaint.
     * Le premier bloc de code à l'intérieur du try calcule la différence entre nextDrawTime (heure prévue pour le prochain tirage) et System.nanoTime() (heure actuelle en nanosecondes). Cette différence est ensuite convertie en millisecondes en la divisant par 1000000 et stockée dans la variable remainingTime.
     * Si remainingTime est inférieur à zéro, ce qui signifie que le temps restant est de 0 et donc le Thread ne sera plus en sleep
     * la prochaine update et le prochain affichage peuvent donc avoir lieu (soit update(), repaint()).
     * Si le remainingTime n'est pas 0, le Thread est mis en sleep pour le temps restant jusqu'au prochain passage.
     *
     * Il est important de noter que vous ne devez jamais appeler directement la méthode run() pour démarrer un thread.
     * La méthode start() doit être utilisée pour démarrer un thread, et elle appelle ensuite la méthode run() de manière asynchrone
     * dans un nouveau thread.
     *
     */
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
                System.out.println(nextDrawTime);
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

    /**
     * Appeler dans le main, cette méthode permet de lancer le jeu.
     * Elle s'occupe de mettre en place les objets du jeu (assetSetter.setObject(), et de lancer la musique.
     * Elle met également le gameState à playState, qui permettra de mettre à jour les instances du jeu donc sur 'Play'.
     * @throws IOException
     */
    public void setupGame() throws IOException {
        assetSetter.setObject();
        playMusic(0);
        gameState = playState;
    }

    /**
     * La méthode update de la classe GamePanel est celle qui appelera les méthodes update de chaque classe instancié dans le jeu,
     * pour mettre a jour les informations des objets instanciés. Elle s'occupe également de mettre à jour le gameState.
     * Si le gameState est égal à playState, soit 1, on met à jour le joueur, autrement cela signifie que le jeu est en pause.
     * Est donc que les instances ne sont jamais mise a jour, donc l'affichage peut afficher autant de fois qu'il le veux, les instances du jeu sont statics et ne répondent plus.
     */
    public void update(){
        if(gameState == playState){
            player.update();

        }
        if(gameState == pauseState){
            //pause menu probably
        }
    }

    /**
     * La méthode paintComponent est appelée chaque fois que le composant doit être redessiné.
     * L'object Graphics 'g' est récupèrer pour pouvoir modifier (peindre) le composant. Ainsi dans cette méthode
     * on appel toutes les méthodes draw des classes qui on besoin d'être repaint dans la mesure ou leurs états ont changés.
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g) {
        long drawStart = 0;
        drawStart = System.nanoTime();
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        tileManager.draw(g2d);
        assetSetter.draw(g2d);
        player.draw(g2d);
        ui.draw(g2d);

        long drawEnd = System.nanoTime();
        long passed = drawEnd - drawStart;

        if(keyHandler.debugKey){
            g2d.setColor(Color.WHITE);
            g2d.drawString("Draw time :" + passed/100000 + "ms" ,10, 400);
            System.out.println("Draw time :" + passed/100000+ "ms");
        }
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
