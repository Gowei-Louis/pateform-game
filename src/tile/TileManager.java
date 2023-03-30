package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gamePanel;
    public Tile[] tile;
    public int[][] mapTileNumber;

    public TileManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        tile = new Tile[10];
        this.mapTileNumber = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];
        getTileImage();
        loadMap();
    }

    public void loadMap(){

        try{
            InputStream is = getClass().getResourceAsStream("/maps/world02.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;

            while(col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow){
                String line = br.readLine();
                while(col < gamePanel.maxWorldCol){
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNumber[col][row] = num;
                    col++;
                }
                if(col == gamePanel.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e){
            e.printStackTrace();
    }
    }

    public void getTileImage() {

        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass.png")));


            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png")));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].collision = true;
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water.png")));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/earth.png")));


            tile[4] = new Tile();
            tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree.png")));
            tile[4].collision = true;


            tile[5] = new Tile();
            tile[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/sand.png")));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2d){
        int wordCol = 0;
        int wordRow = 0;


        while(wordCol < gamePanel.maxWorldCol && wordRow < gamePanel.maxWorldRow){

            int tileNumber = mapTileNumber[wordCol][wordRow];

            int worldX = wordCol * gamePanel.tileSize;
            int worldY = wordRow * gamePanel.tileSize;
            int screenX = worldX - gamePanel.player.worldX + gamePanel.screenWidth/2;
            int screenY = worldY - gamePanel.player.worldY + gamePanel.screenHeight/2;

            if(worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.screenWidth/2 && worldX  - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.screenWidth/2 &&
               worldY  + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.screenHeight/2 && worldY  - gamePanel.tileSize  < gamePanel.player.worldY + gamePanel.screenHeight/2){

                g2d.drawImage(tile[tileNumber].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);

            }


            wordCol++;

            if(wordCol == gamePanel.maxWorldCol){
                wordCol = 0;
                wordRow++;
            }
        }





    }


}
