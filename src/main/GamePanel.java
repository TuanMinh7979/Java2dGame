package main;

import Entity.Player;
import Entity.TileMn;

import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel implements Runnable {
    final int oriTileSize = 16;
    final int scale = 3;

    public final int tileSize = oriTileSize * scale;
    public final int maxXCol = 16;
    public final int maxYRow = 12;
    public final int screenWidth = tileSize * maxXCol;//768 pixel
    public final int screenHeight = tileSize * maxYRow;//576 pixel

    public final int maxWorldCol=50;
    public final int maxWorldRow=50;

    public final int worldWidth=tileSize*maxWorldCol;
    public final int worldHeight=tileSize*maxWorldRow;


    //Inject
    int FPS = 60;
    TileMn tileMn= new TileMn(this);
    KeyHdle keyhdle = new KeyHdle();
    Thread gameThread;

    public CollistionChecker colcheck= new CollistionChecker(this);
    public Player player1= new Player(this, this.keyhdle);
    //Inject



    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)
        );
        this.setBackground(Color.black  );
        this.setDoubleBuffered(true);

        this.addKeyListener(keyhdle);
        this.setFocusable(true);//recive key input
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        //khoang thoi gian update again is 1 / 60s
        double delta = 0;
        long lastTime = System.nanoTime();
        long curtime;
//        long timer=0;
//        int drawcount=0;
        while (gameThread != null) {


            curtime = System.nanoTime();
            delta += (curtime - lastTime) / drawInterval;

           // so lan update la thoi gian hien tai tru thoi gian truoc do / thoi gian 1 lan update

//            timer+=(curtime-lastTime);
            lastTime = curtime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;

//                drawcount++;

            }
//            if(timer>=1000000000){
//                System.out.println("FPS:"+drawcount);
//                drawcount=0;
//                timer=0;
//            }
        }

    }

    public void update() {
       player1.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
//        g2.setColor(Color.CYAN);
//        g2.fillRect(playerX, playerY, tileSize, tileSize);
        tileMn.draw(g2);
        //map ve lai
        player1.draw(g2);
        //toa do nhan vat da fix cung tai giua man hinh
        //chi có tọa độ camera thay đổi

        g2.dispose();

    }
}
