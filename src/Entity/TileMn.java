package Entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileMn {

    GamePanel gp;
    public Tile[] tiles;
    public int mapTileNum[][];

    public TileMn(GamePanel gp) {
        this.gp = gp;
        this.tiles = new Tile[10];
        // co khoan 10 loai tile
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/Map/Map2.txt");
    }

    public void getTileImage() {
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/grass.png"));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/wall.png"));
            tiles[1].collistion=true;

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/water.png"));
            tiles[2].collistion=true;

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/earth.png"));


            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/tree.png"));
            tiles[4].collistion=true;

            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/sand.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String mapPath) {
        try {
            InputStream is = getClass().getResourceAsStream(mapPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                String[] numberarr = line.split(" ");
                while (col < gp.maxWorldCol) {
                    int num = Integer.parseInt(numberarr[col]);
                    mapTileNum[row][col] = num;
                    col++;

                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldcol = 0;
        int worldrow = 0;


        while (worldcol < gp.maxWorldCol && worldrow < gp.maxWorldRow) {
            int num = mapTileNum[worldrow][worldcol];

            int worldx = worldcol * gp.tileSize;
            int worldy = worldrow * gp.tileSize;

            int screenX = worldx - gp.player1.Eworldx + gp.player1.EscreenX;
            int screenY = worldy - gp.player1.Eworldy + gp.player1.EscreenY;
//          int screenX=worldx- gp.player1.Eworldx;
//          int screenY= worldy -gp.player1.Eworldy;
            if (worldx + gp.tileSize > gp.player1.Eworldx - gp.player1.EscreenX &&
                    worldx - gp.tileSize < gp.player1.Eworldx + gp.player1.EscreenX &&
                    worldy + gp.tileSize > gp.player1.EscreenY - gp.player1.EscreenY &&
                    worldy - gp.tileSize < gp.player1.Eworldy + gp.player1.EscreenY) {

                g2.drawImage(tiles[num].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            worldcol++;

            if (worldcol == gp.maxWorldCol) {
                worldcol = 0;

                worldrow++;

            }


        }
    }
}
