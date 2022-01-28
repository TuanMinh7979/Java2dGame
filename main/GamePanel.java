package main;

import Entity.Player;

import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel implements Runnable {
    final int oriTileSize = 16;
    final int scale = 3;
    final int tileSize = oriTileSize * scale;
    final int maxYCol = 12;
    final int maxXCol = 16;

    final int screenWidth = tileSize * maxXCol;//768 pixel
    final int screenHeight = tileSize * maxYCol;//576 pixel

    //Inject
    int FPS = 60;
    KeyHdle keyhdle = new KeyHdle();
    Thread gameThread;
    Player player1= new Player(this, this.keyhdle);
    //Inject

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)
        );
        this.setBackground(Color.yellow);
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
        long timer=0;
        int drawcount=0;
        while (gameThread != null) {


            curtime = System.nanoTime();
            delta += (curtime - lastTime) / drawInterval;

           // so lan update la thoi gian hien tai tru thoi gian truoc do / thoi gian 1 lan update

            timer+=(curtime-lastTime);
            lastTime = curtime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;

                drawcount++;

            }
            if(timer>=1000000000){
                System.out.println("FPS:"+drawcount);
                drawcount=0;
                timer=0;
            }


        }

    }

    public void update() {

        if (keyhdle.upP == true) {
            playerY -= playerSpeed;
//            System.out.println("key W");
        } else if (keyhdle.downP == true) {
            playerY += playerSpeed;

        } else if (keyhdle.leftP == true) {
            playerX -= playerSpeed;
        } else if (keyhdle.rightP == true) {
            playerX += playerSpeed;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.CYAN);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();

    }
}
