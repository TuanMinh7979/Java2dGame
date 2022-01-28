package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHdle implements KeyListener {
    public boolean upP=false, downP=false, leftP=false, rightP=false;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_A) {
            leftP = true;

        }
        if (code == KeyEvent.VK_D) {
            rightP = true;

        }
        if (code == KeyEvent.VK_W) {
            upP = true;

        }
        if (code == KeyEvent.VK_S) {

            downP = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_A) {
            leftP = false;

        }
        if (code == KeyEvent.VK_D) {
            rightP = false;

        }
        if (code == KeyEvent.VK_W) {
            upP = false;

        }
        if (code == KeyEvent.VK_S) {

            downP =false;
        }
    }
}
