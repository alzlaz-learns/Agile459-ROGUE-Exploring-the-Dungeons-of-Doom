package com.playground.alex;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.models.Player;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Color;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanelPlayground extends JPanel{
    //screen settings
    final int tileSize = 16;

    private static final int SCREEN_WIDTH = 80;
    private static final int SCREEN_HEIGHT = 24;

    final int screenWidthScaled = tileSize * SCREEN_WIDTH; //16 * 80 = 1,280
    final int screenHeightScaled = tileSize * SCREEN_HEIGHT; //16 * 24 = 384

    //test attributes
    private Player player; // The player object
    private char[][] dungeon; // The dungeon map
    private JLabel[][] gridLabels;
    
    //reference to main jframePlayGround
    private JFramePlayGround frame;

    public GamePanelPlayground(Player player, char[][] dungeon, JFramePlayGround frame) {
        this.player = player;
        this.dungeon = dungeon;
        this.frame = frame;

        this.setPreferredSize(new Dimension(screenWidthScaled, screenHeightScaled));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

        this.setLayout(new GridLayout(dungeon.length, dungeon[0].length));

        gridLabels = new JLabel[dungeon.length][dungeon[0].length];

        for (int i = 0; i < dungeon.length; i++) {
            for (int j = 0; j < dungeon[i].length; j++) {
                JLabel cell = new JLabel(String.valueOf(dungeon[i][j]), SwingConstants.CENTER);
                cell.setForeground(Color.WHITE);
                cell.setBackground(Color.BLACK);
                cell.setOpaque(true);
                cell.setFont(new Font("Monospaced", Font.PLAIN, 12));
                this.add(cell);
                gridLabels[i][j] = cell;
            }
        }
        //key listener to to read user input
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // Debug
                // System.out.println("Key pressed: " + KeyEvent.getKeyText(e.getKeyCode())); 
                // Pass the entire KeyEvent
                handleMovement(e); 
            }
        });
    }


    private void handleMovement(KeyEvent e) {

        if (player.isImmobile()) {
            frame.updateMessage("You are trapped and cannot move for " + player.getImmobile() + " more turns!");
            player.immobileDecrease();
            // Stop movement if immobilized
            return; 
        }

        //the logic is simple for just the borders of the screen no collision with walls mostly because those dont exist yet.
        int newX = player.getX();
        int newY = player.getY();

        int keyCode = e.getKeyCode();
        
        switch (keyCode) {
            // Move up (waterfalls in)
            case KeyEvent.VK_W: 
            case KeyEvent.VK_UP: 
                if (newY > 0) newY--;
                break;
            // Move down (waterfalls in)
            case KeyEvent.VK_S:           
            case KeyEvent.VK_DOWN: 
                if (newY < dungeon.length - 1) newY++;
                break;
            // Move left (waterfalls in)
            case KeyEvent.VK_A: 
            case KeyEvent.VK_LEFT: 
                if (newX > 0) newX--;
                break;
            // Move right (waterfalls in)
            case KeyEvent.VK_D: 
            case KeyEvent.VK_RIGHT:
                if (newX < dungeon[0].length - 1) newX++;
                    break;
            // Moves up-right
            case KeyEvent.VK_PAGE_UP: 
                if (newY > 0 && newX < dungeon[0].length - 1) {
                    newX++; 
                    newY--;
                }
                break;
            // Move down-right
            case KeyEvent.VK_PAGE_DOWN: 
                if (newY < dungeon.length - 1 && newX < dungeon[0].length - 1) {
                    newX++; 
                    newY++;
                }
                break;
            // Move up-left
            case KeyEvent.VK_HOME: 
                if (newY > 0 && newX > 0) {
                    newX--; 
                    newY--;
                }
                break;
            // Move down-left   
            case KeyEvent.VK_END: 
                if (newY < dungeon.length - 1 && newX > 0) {
                    newX--; 
                    newY++;
                }
                break;
            // Go to the next floor (>)
            case KeyEvent.VK_PERIOD:
                // Detect if Shift is held down
                if (e.isShiftDown()) { 
                    DungeonFloor currentFloor = frame.dungeonFloors.get(frame.currentFloorIndex);

                    // Debugging: Print player and stairs positions
                    // System.out.println("Player at: (" + player.getX() + ", " + player.getY() + ")");
                    // System.out.println("Stairs at: (" + currentFloor.getStairX() + ", " + currentFloor.getStairY() + ")");

                    if (player.getX() == currentFloor.getStairX() && player.getY() == currentFloor.getStairY()) {
                        frame.messageArea.updateMessage("Descending to floor " + (frame.currentFloorIndex));
                        frame.changeFloor(true);
                    } else {
                        frame.messageArea.updateMessage("You need to be on the stairs (>) to go down!");
                    }
                    return;
                }
                break;
                case KeyEvent.VK_COMMA:
                // Detect if Shift is held down
                if (e.isShiftDown()) { 
                    DungeonFloor currentFloor = frame.dungeonFloors.get(frame.currentFloorIndex);

                    // Debugging: Print player and stairs positions
                    // System.out.println("Player at: (" + player.getX() + ", " + player.getY() + ")");
                    // System.out.println("Stairs at: (" + currentFloor.getStairX() + ", " + currentFloor.getStairY() + ")");

                    if (player.getX() == currentFloor.getStairX() && player.getY() == currentFloor.getStairY()) {
                        frame.messageArea.updateMessage("Ascending to floor " + (frame.currentFloorIndex + 2));
                        frame.changeFloor(false);
                    } else {
                        frame.messageArea.updateMessage("You need to be on the stairs (>) to go up!");
                    }
                    return;
                }
                break;
                

            
            default:
                return;
        }

        TrapInterfacePlayground trap = frame.dungeonFloors.get(frame.currentFloorIndex).getTrapAt(newX, newY);
        if (trap != null) {
            String trapMessage = trap.trigger(player);
            System.out.println(trap.getEffect());// <-- here
            
            switch (trap.getEffect()) {
                case FALL -> {
                    frame.updateMessage(trapMessage);
                    // Move down a floor
                    frame.changeFloor(true); 
                }
                case HOLD -> {
                    frame.updateMessage(trapMessage);
                }
                //TODO: add more traps and cases.
            }
            updateDungeon();
            return; 
        }

        player.moveTo(newX, newY);
        frame.updateStats(player.toString());
        updateDungeon();
    }

    public void setDungeon(char[][] newDungeon) {
        this.dungeon = newDungeon;
    }

    //used to draw the dungeon on screen
    public void updateDungeon() {
        
        DungeonFloor currentFloor = frame.dungeonFloors.get(frame.currentFloorIndex);
        char[][] originalMap = currentFloor.getOriginalMap();

        for (int i = 0; i < dungeon.length; i++) {
            for (int j = 0; j < dungeon[i].length; j++) {
                dungeon[i][j] = originalMap[i][j]; // Reset map
            }
        }

        // Place the stairs back on the map temp example
        dungeon[currentFloor.getStairY()][currentFloor.getStairX()] = '>';


        // Place the traps (Only show if revealed)
        for (TrapInterfacePlayground trap : currentFloor.traps) {
            if (!trap.isHidden()) {
                dungeon[trap.getY()][trap.getX()] = '!'; // Change this if needed
            }
        }


         // Place the player on the map
        dungeon[player.getY()][player.getX()] = player.getIcon();

        // Update UI to reflect the dungeon map
        for (int i = 0; i < dungeon.length; i++) {
            for (int j = 0; j < dungeon[i].length; j++) {
                JLabel cell = gridLabels[i][j];
                char cellContent = dungeon[i][j];

                //changes the stair symbol to a green box
                if (cellContent == '>') {
                    cell.setBackground(Color.GREEN);
                    cell.setText(""); 
                } else if (cellContent == player.getIcon()) {
                    cell.setBackground(Color.BLACK);
                    cell.setForeground(Color.ORANGE);
                    cell.setText(String.valueOf(cellContent));
                } else {
                    cell.setBackground(Color.BLACK);
                    cell.setForeground(Color.WHITE);
                    cell.setText(String.valueOf(cellContent));
                }
            }
        }

        this.revalidate();
        this.repaint();
    }
    
}
