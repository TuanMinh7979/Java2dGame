package Entity;

import main.GamePanel;
import main.KeyHdle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHdle keyHdle;

    public final int EscreenX;//fix
    public final int EscreenY;//fix

    public Player(GamePanel inpgp, KeyHdle inpkeyhdle) {
        this.gp = inpgp;
        this.keyHdle = inpkeyhdle;
        EscreenX=gp.screenWidth/2 - gp.tileSize/2;
        //toa do Escreen X la fix cung trong khung
        //nhan vat di chuyen thi la khung camera di chuyen
        EscreenY=gp.screenHeight/2- gp.tileSize/2;

        solidArea= new Rectangle();
        solidArea.x=11;
        solidArea.y=22;
        solidArea.width=26;
        solidArea.height=26;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        Eworldx = gp.tileSize*23;
        Eworldy = gp.tileSize*21;
        speed = 4;
        direction= "down";
    }
    public void getPlayerImage(){
        try{
         down1= ImageIO.read(getClass().getResourceAsStream("/Sprite/boy_down_1.png"));
         down2= ImageIO.read(getClass().getResourceAsStream("/Sprite/boy_down_2.png"));
         up1= ImageIO.read(getClass().getResourceAsStream("/Sprite/boy_up_1.png"));
         up2= ImageIO.read(getClass().getResourceAsStream("/Sprite/boy_up_2.png"));
         left1= ImageIO.read(getClass().getResourceAsStream("/Sprite/boy_left_1.png"));
         left2= ImageIO.read(getClass().getResourceAsStream("/Sprite/boy_left_2.png"));
         right1= ImageIO.read(getClass().getResourceAsStream("/Sprite/boy_right_1.png"));
         right2= ImageIO.read(getClass().getResourceAsStream("/Sprite/boy_right_2.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update() {
        if(keyHdle.upP==true|| keyHdle.downP==true||keyHdle.leftP==true||keyHdle.rightP==true) {

            if (keyHdle.upP == true) {
                direction = "up";
//            System.out.println("key W");
            } else if (keyHdle.downP == true) {
                direction = "down";


            } else if (keyHdle.leftP == true) {
                direction = "left";

            } else if (keyHdle.rightP == true) {
                direction = "right";

            }

            colistionOn=false;
            gp.colcheck.checkTile(this);

            if(!colistionOn){
                switch (direction) {
                    case "up" -> Eworldy -= speed;
                    case "down" -> Eworldy += speed;
                    case "left" -> Eworldx -= speed;
                    case "right" -> Eworldx += speed;
                }
            }

            spriteCounter++;
            if (spriteCounter >= 15) {
                if (spriteNumber == 1) {
                    spriteNumber = 2;
                } else if (spriteNumber == 2) {
                    spriteNumber = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
//        g2.setColor(Color.CYAN);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage image=null;
        switch(direction){
            case "down":
                if(spriteNumber==1){
                    image=down1;
                }else{
                    image=down2;
                }

            break;
            case "up":
                if(spriteNumber==1){
                    image=up1;
                }else{
                    image=up2;
                }
            break;
            case "left":
                if(spriteNumber==1){
                    image=left1;
                }else{
                    image=left2;
                }
            break;
            case "right":
                if(spriteNumber==1){
                    image=right1;
                }else{
                    image=right2;
                }
            break;

        }
        g2.drawImage(image, EscreenX, EscreenY, gp.tileSize, gp.tileSize, null);

    }

}
