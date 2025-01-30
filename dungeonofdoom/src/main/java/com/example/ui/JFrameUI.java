package com.example.ui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;

import javax.swing.JFrame;


import java.awt.event.MouseEvent;

import com.example.managers.GameManager;

public class JFrameUI {

    JFrame window;
    private AbstractTextBars messageArea;
    private AbstractTextBars statArea;
    private GamePanel gamePanel;
    private GameManager gameManager;

    public JFrameUI(){
        this.window = new JFrame();
        this.messageArea = new MessageArea(); 
        this.gameManager = new GameManager(this);
        this.gamePanel = new GamePanel(gameManager);
        
        /*
            ive come across an a truely goofy inconsistency that i dont understand why
            the focus isnt alway caught by the Jframe window so i added this so we can just click on it
            I also think i might have resolved it the other way? but setting everyother focusable false
            but who knows.
            
            It just randomly started with no major changes right before i was done
            with things and doing final runs
        */
        gamePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    gamePanel.requestFocusInWindow();
                }
            });
        this.statArea = new StatArea();
        
        generateLayout();

        
    }

    public void generateLayout() {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Rogue: Dungeon of Doom");
    
        //Message Area at the top
        window.add(messageArea, BorderLayout.NORTH);

        
    
        // Stats Area at the bottom
        window.add(statArea, BorderLayout.SOUTH);


        // // Game play area in the center
        window.add(gamePanel, BorderLayout.CENTER);
    
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        messageArea.setFocusable(false);
        statArea.setFocusable(false);

        // Ensure focus is on the game panel for key events
        gamePanel.requestFocusInWindow();

    }

    //updates top messages bar
    public void updateMessage(String message) {
        messageArea.updateText(message);
    }

    //updates bottoms stats bar
    public void updateStats(String stats) {
        statArea.updateText(stats);
    }

    public void updateGameScreen(){
        gamePanel.updateDungeonView();
    }
}
