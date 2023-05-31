package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gamePanel;
    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    /**
     * On passe en parametre l'entité (entity) que l'ont veut tester pour les collisions.
     * On va ensuite tester si l'entité est en collision en fonction de la direction de celle-ci avec une tile (tuile) comme un arbre par exemple.
     * Si l'entité est en collision avec une tile, on va mettre la variable 'collisionOn' de l'entité à true.
     * Si l'entité n'est pas en collision avec une tile, on va mettre la variable 'collisionOn' de l'entité à false.
     * Donc l'entité pourra se déplacer sans bloquage.
     *
     *
     * @param entity
     */
    public void checkTile(Entity entity){

        // square collision box into the entity
        int entityLeftWordX = entity.worldX + entity.solidArea.x;
        int entityRightWordX = entity.worldX + entity.solidArea.x + entity.solidArea.width;

        int entityTopWordY = entity.worldY + entity.solidArea.y;
        int entityBottomWordY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWordX / gamePanel.tileSize;
        int entityRightCol = entityRightWordX / gamePanel.tileSize;
        int entityTopRow = entityTopWordY / gamePanel.tileSize;
        int entityBottomRow = entityBottomWordY / gamePanel.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction){
            case "up":
                entityTopRow = (entityTopWordY - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNumber[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNumber[entityRightCol][entityTopRow];
                if(gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWordY - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNumber[entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.tileManager.mapTileNumber[entityRightCol][entityBottomRow];
                if(gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWordX - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNumber[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNumber[entityLeftCol][entityBottomRow];
                if(gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWordX + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNumber[entityRightCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNumber[entityRightCol][entityBottomRow];
                if(gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;

        }



    }


    /**
     * On passe en parametre l'entité (entity) que l'ont veut tester pour les collisions et l'état de collision du joueur.
     * On va ensuite pour chaque objet dans la liste gamePanel.objects tester si l'entité est en collision en fonction de la direction de celle-ci avec un objet.
     * Si l'entité est en collision avec un objet, on va mettre la variable 'collisionOn' de l'entité à true si la propri.
     * Si l'entité n'est pas en collision avec une tile, on va mettre la variable 'collisionOn' de l'entité à false.
     * Donc l'entité pourra alors récuperer les objets qui seront récuperable et les désactiver.
     * @param entity
     * @param player
     * @return
     */
    public int checkObject(Entity entity, boolean player){

        int index = 999;

        for (int i = 0; i < gamePanel.objects.length; i++) {
            if(gamePanel.objects[i] != null){
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                gamePanel.objects[i].solidArea.x = gamePanel.objects[i].worldX + gamePanel.objects[i].solidArea.x;
                gamePanel.objects[i].solidArea.y = gamePanel.objects[i].worldY + gamePanel.objects[i].solidArea.y;

                switch (entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(gamePanel.objects[i].solidArea)){
                            if(gamePanel.objects[i].collision){
                                entity.collisionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gamePanel.objects[i].solidArea)){
                            if(gamePanel.objects[i].collision){
                                entity.collisionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }

                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gamePanel.objects[i].solidArea)){
                            if(gamePanel.objects[i].collision){
                                entity.collisionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }

                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(gamePanel.objects[i].solidArea)){
                            if(gamePanel.objects[i].collision){
                                entity.collisionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }

                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gamePanel.objects[i].solidArea.x = gamePanel.objects[i].solidAreaDefaultX;
                gamePanel.objects[i].solidArea.y = gamePanel.objects[i].solidAreaDefaultY;
            }
        }


        return index;
    }

}
