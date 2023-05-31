package main;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

/**
 * Cette classe gère la lecture des sons du jeu.
 */
public class Sound {

    Clip clip;
    URL[] path = new URL[30];

    /**
     * Constructeur de la classe Sound.
     * Initialise les chemins des fichiers audio.
     */
    public Sound(){
        path[0] = getClass().getResource("/sounds/band.wav");
        path[1] = getClass().getResource("/sounds/get.wav");
    }

    /**
     * Charge un fichier audio dans le lecteur de son.
     * @param i L'indice du chemin du fichier audio à charger.
     */
    public void setFile(int i){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(path[i]);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Joue le son actuellement chargé.
     */
    public void playSound(){
        clip.start();
    }

    /**
     * Joue le son actuellement chargé en boucle.
     */
    public void loopSound(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Arrête la lecture du son.
     */
    public void stop(){
        clip.stop();
    }

}
