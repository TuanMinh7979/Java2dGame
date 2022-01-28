package main;

import Entity.Entity;

public class CollistionChecker {
    GamePanel gp;
    public CollistionChecker(GamePanel gp){
        this.gp=gp;

    }
    public void checkTile( Entity entity){
        int entityLeftWorldX=entity.Eworldx +entity.solidArea.x;
        int entityRightWorldX=entity.Eworldx+ entity.solidArea.x+ entity.solidArea.width;
        int entityTopWorldY= entity.Eworldy+entity.solidArea.y;
        int entityBottomWorldY=entity.Eworldy+entity.solidArea.y+entity.solidArea.height;

        //col and row
        int entityLeftCol=entityLeftWorldX/gp.tileSize;
        int entityRightCol=entityRightWorldX/gp.tileSize;

        int entityTopRow=entityTopWorldY/gp.tileSize;
        int entityBottomRow=entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;
        switch(entity.direction){
            case "up":
                entityTopRow=(entityTopWorldY-entity.speed)/gp.tileSize;
                tileNum1=gp.tileMn.mapTileNum[entityTopRow][entityLeftCol];
                tileNum2=gp.tileMn.mapTileNum[entityTopRow][entityRightCol];
                if(gp.tileMn.tiles[tileNum1].collistion || gp.tileMn.tiles[tileNum2].collistion){
                    entity.colistionOn=true;
                }

                break;
            case "down":
                entityBottomRow=(entityBottomWorldY+entity.speed)/gp.tileSize;
                tileNum1=gp.tileMn.mapTileNum[entityBottomRow][entityLeftCol];
                tileNum2=gp.tileMn.mapTileNum[entityBottomRow][entityRightCol];
                if(gp.tileMn.tiles[tileNum1].collistion || gp.tileMn.tiles[tileNum2].collistion){
                    entity.colistionOn=true;
                }

                break;
            case "left":
                entityLeftCol=(entityLeftWorldX-entity.speed)/gp.tileSize;
                tileNum1=gp.tileMn.mapTileNum[entityTopRow][entityLeftCol];
                tileNum2=gp.tileMn.mapTileNum[entityBottomRow][entityLeftCol];
                if(gp.tileMn.tiles[tileNum1].collistion || gp.tileMn.tiles[tileNum2].collistion){
                    entity.colistionOn=true;
                }
                break;
            case "right":
                entityRightCol=(entityRightWorldX-entity.speed)/gp.tileSize;
                tileNum1=gp.tileMn.mapTileNum[entityTopRow][entityRightCol];
                tileNum2=gp.tileMn.mapTileNum[entityBottomRow][entityRightCol];
                if(gp.tileMn.tiles[tileNum1].collistion || gp.tileMn.tiles[tileNum2].collistion){
                    entity.colistionOn=true;
                }
                break;


        }

    }
}
