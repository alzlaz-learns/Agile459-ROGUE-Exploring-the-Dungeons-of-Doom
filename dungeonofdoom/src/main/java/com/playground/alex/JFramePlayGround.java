package com.playground.alex;

import java.awt.BorderLayout;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class JFramePlayGround {

    MessageAreaPlayground messageArea;
    JFrame window;
    GamePanelPlayground gamePanel;
    StatAreaPlayground statArea;

    public JFramePlayGround(){
        window = new JFrame();
        gamePanel = new GamePanelPlayground();
        messageArea = new MessageAreaPlayground();
        statArea = new StatAreaPlayground();
        
    }
    
    public void generateLayout(){

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Rougue: Dungeon of Doom");

        // Message Area at the top
        window.add(messageArea, BorderLayout.NORTH);

        //Game play are in the center
        window.add(gamePanel);

        window.add(statArea, BorderLayout.SOUTH);

        window.pack(); // added so we can see the game screen.

        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }

    public void updateMessage(String message) {
        messageArea.updateMessage(message);
    }

    public void updateStats(String stats) {
        statArea.setText(stats);
    }

    public void drawDungeon(char[][] dungeon) {
        gamePanel.drawDungeon(dungeon);
    }
    

    public static void main(String[] args){

        JFramePlayGround playGround = new JFramePlayGround();
        playGround.generateLayout();

        // Example Output Layout
        
        
        

        

        Scanner sc = new Scanner(System.in);
        while(true){
            String input = sc.nextLine();
            
            if (input.equalsIgnoreCase("bye")) {
                break;
            }
            playGround.updateMessage(input);
            char[][] exampleDungeon = new char[22][80];
            for (int i = 0; i < exampleDungeon.length; i++) {
                for (int j = 0; j < exampleDungeon[i].length; j++) {
                    exampleDungeon[i][j] = input.charAt(0);
                }
            }
            playGround.drawDungeon(exampleDungeon);
        }

        sc.close();
    }
}
