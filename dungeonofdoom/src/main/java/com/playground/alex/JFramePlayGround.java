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

        DungeonFloor firstFloor = dungeonFloors.get(0);
        firstFloor.placePlayer(player);

        // Initialize UI
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
        if (goingDown) { 
            if (currentFloorIndex < 25) { 
                currentFloorIndex++;
            } else {
                messageArea.updateMessage("You have reached the bottom floor!");
                return;
            }
        } else { 
            if (currentFloorIndex > 0) { 
                currentFloorIndex--;
            } else {
                messageArea.updateMessage("You cannot go any higher!");
                return;
            }
        }

        // Reference the new floor
        DungeonFloor newFloor = dungeonFloors.get(currentFloorIndex);

        // Set the player's spawn position to match the previous stair location
        int spawnX = dungeonFloors.get(goingDown ? currentFloorIndex - 1 : currentFloorIndex + 1).getStairX();
        int spawnY = dungeonFloors.get(goingDown ? currentFloorIndex - 1 : currentFloorIndex + 1).getStairY();

        // Place the player exactly where the stairs were
        player.moveTo(spawnX, spawnY);

        // Ensure the correct stairs exist
        if (goingDown) {
            newFloor.setStairs(spawnX, spawnY, true); // Downward stairs on new floor
            dungeonFloors.get(currentFloorIndex - 1).setStairs(spawnX, spawnY, false); // Upward stairs on previous floor
        } else {
            newFloor.setStairs(spawnX, spawnY, false); // Upward stairs on new floor
            dungeonFloors.get(currentFloorIndex + 1).setStairs(spawnX, spawnY, true); // Downward stairs on previous floor
        }

        // Update the player's tracked floor
        player.setCurrentFloor(currentFloorIndex + 1);

        // Refresh the game panel
        gamePanel.setDungeon(newFloor.getMap());
        gamePanel.updateDungeon();

        // Debugging
        System.out.println("Player moved to floor: " + player.getCurrentFloor() + " at (" + spawnX + ", " + spawnY + ")");
        
        // Update the message area
        messageArea.updateMessage("You are now on floor " + player.getCurrentFloor());
    }



    public void updateDungeon() {
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
