package com.playground.alex;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;


import javax.swing.JFrame;
import com.models.Player;

public class JFramePlayGround {

    MessageAreaPlayground messageArea;
    JFrame window;
    GamePanelPlayground gamePanel;
    StatAreaPlayground statArea;

    //test attributes
    Player player;
    List<DungeonFloor> dungeonFloors;
    int currentFloorIndex = 0;

    

    public JFramePlayGround(){

        // Initialize player and dungeon
        player = new Player("Hero");
        player.moveTo(5, 5);
        
        // Simple generate new floors that essentially on open grid with different symbols to represent change.
        dungeonFloors = new ArrayList<>();
        for (int i = 1; i <= 26; i++) {
            dungeonFloors.add(new DungeonFloor(i, 80, 22)); // 80x22 grid
        }

        //Initialize UI
        window = new JFrame();
        gamePanel = new GamePanelPlayground(player, dungeonFloors.get(currentFloorIndex).getMap(), this);
        messageArea = new MessageAreaPlayground();
        statArea = new StatAreaPlayground();
        
    }
    
    public void generateLayout() {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Rogue: Dungeon of Doom");
    
        // Message Area at the top
        window.add(messageArea, BorderLayout.NORTH);

        // Game play area in the center
        window.add(gamePanel, BorderLayout.CENTER);
    
        // Stats Area at the bottom
        window.add(statArea, BorderLayout.SOUTH);
    
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    
        // Ensure focus is on the game panel for key events
        gamePanel.requestFocusInWindow();
    }

    /*
        currently fills board with periods as an example field.
        
        an idea I had when writing this stuff. For when entering new rooms and passages to help 
        reveal when a player does is to create an secondary hidden board that maps if a location
        has been discovered, so when it is it can be reflected on the main board? maybe thats a little
        goofy. probably just better to make a custom object that can hold a character and reflect a revealed
        status.

    */

    public void changeFloor(boolean goingDown) {
        if (goingDown && currentFloorIndex < 25) {
            currentFloorIndex++;
        } else if (!goingDown && currentFloorIndex > 0) {
            currentFloorIndex--;
        } else {
            //cant pass 0 or 25
            return; 
        }

        // Reset player position to a set spot 5,5
        // basic implementation
        //TODO: Eventualy have an implementation that places a player in a random position in a random room
        player.moveTo(5, 5);

        // Update the game panel with the new floor
        gamePanel.setDungeon(dungeonFloors.get(currentFloorIndex).getMap());
        gamePanel.updateDungeon();

        // Update the message area
        messageArea.updateMessage("You are now on floor " + (currentFloorIndex + 1));
    }

    private void updateDungeon() {
        gamePanel.updateDungeon();
    }

    //updates top messages bar
    public void updateMessage(String message) {
        messageArea.updateMessage(message);
    }

    //updates bottoms stats bar
    public void updateStats(String stats) {
        statArea.setText(stats);
    }




    public static void main(String[] args) {
        JFramePlayGround playGround = new JFramePlayGround();
        playGround.generateLayout();
        playGround.updateDungeon(); // Set initial player position
    
        playGround.gamePanel.requestFocusInWindow(); // Ensure keyboard focus for movement
    }
}
