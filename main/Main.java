package main;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        JFrame window= new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2d game");

        GamePanel gamep= new GamePanel();

        window.add(gamep);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamep.startGameThread();//will loop still end of game



    }
}
