package com.playground.alex;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class JFramePlayGround {
    public static void generateLayout(){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Rougue: Dungeon of Doom");

        // Message Area at the top
        MessageAreaPlayground messageArea = new MessageAreaPlayground();
        window.add(messageArea, BorderLayout.NORTH);

        //Game play are in the center
        GamePanelPlayground gamePanel = new GamePanelPlayground();
        window.add(gamePanel);

        StatAreaPlayground statArea = new StatAreaPlayground();
        window.add(statArea, BorderLayout.SOUTH);

        window.pack(); // added so we can see the game screen.

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        
    }

    public static void main(String[] args){
        JFramePlayGround.generateLayout();
    }
}
