package com.playground.alex;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.models.Player;
import com.models.dungeonofdoom.Traps.AbstractTrap;

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

        // Try to get DejaVu Sans Mono first, fall back to other Unicode-compatible fonts
        Font dungeonFont = null;
        String[] fontNames = {
            "DejaVu Sans Mono",
            "Noto Mono",
            "Lucida Console",
            "Consolas",
            "Monospaced"  // Final fallback
        };
        
        for (String fontName : fontNames) {
            try {
                dungeonFont = new Font(fontName, Font.PLAIN, 16);
                if (dungeonFont.canDisplay('║') && dungeonFont.canDisplay('═')) {
                    break;
                }
            } catch (Exception e) {
                continue;
            }
        }
        
        for (int i = 0; i < dungeon.length; i++) {
            for (int j = 0; j < dungeon[i].length; j++) {
                JLabel cell = new JLabel(String.valueOf(dungeon[i][j]), SwingConstants.CENTER);
                cell.setForeground(Color.WHITE);
                cell.setBackground(Color.BLACK);
                cell.setOpaque(true);
                cell.setFont(dungeonFont);
                //System.out.println(cell.getFont());
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

    //logic to handle player movement
    private void handleMovement(KeyEvent e) {

        if (player.isImmobile()) {
            frame.updateMessage("You are trapped and cannot move for " + player.getImmobile() + " more turns!");
            player.immobileDecrease();
            return; // Stop movement if immobilized
        }

        DungeonFloor currentFloor = frame.dungeonFloors.get(frame.currentFloorIndex);
        char[][] dungeon = currentFloor.getMap(); // Access the current dungeon map

        int newX = player.getX();
        int newY = player.getY();

        int keyCode = e.getKeyCode();

        // Determine the new coordinates based on the key pressed
        switch (keyCode) {
            case KeyEvent.VK_W: 
            case KeyEvent.VK_UP: 
                if (newY > 0) newY--;
                break;

            case KeyEvent.VK_S:           
            case KeyEvent.VK_DOWN: 
                if (newY < dungeon.length - 1) newY++;
                break;

            case KeyEvent.VK_A: 
            case KeyEvent.VK_LEFT: 
                if (newX > 0) newX--;
                break;

            case KeyEvent.VK_D: 
            case KeyEvent.VK_RIGHT:
                if (newX < dungeon[0].length - 1) newX++;
                break;

            case KeyEvent.VK_PAGE_UP: 
                if (newY > 0 && newX < dungeon[0].length - 1) {
                    newX++; 
                    newY--;
                }
                break;

            case KeyEvent.VK_PAGE_DOWN: 
                if (newY < dungeon.length - 1 && newX < dungeon[0].length - 1) {
                    newX++; 
                    newY++;
                }
                break;

            case KeyEvent.VK_HOME: 
                if (newY > 0 && newX > 0) {
                    newX--; 
                    newY--;
                }
                break;

            case KeyEvent.VK_END: 
                if (newY < dungeon.length - 1 && newX > 0) {
                    newX--; 
                    newY++;
                }
                break;

            case KeyEvent.VK_PERIOD:
                if (e.isShiftDown()) { 
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
                if (e.isShiftDown()) { 
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

        // Ensure player cannot walk into walls
        if (dungeon[newY][newX] == '║' || dungeon[newY][newX] == '═' || 
            dungeon[newY][newX] == '╔' || dungeon[newY][newX] == '╗' ||
            dungeon[newY][newX] == '╚' || dungeon[newY][newX] == '╝') {
            System.out.println("Wall detected at: (" + newX + ", " + newY + ")");
            frame.updateMessage("You cannot walk into walls!");
            return; // Stop movement if it's a wall
        }

        // Debugging: Print before and after movement
        System.out.println("Before move: Player at (" + player.getX() + ", " + player.getY() + ") - Tile: " + dungeon[player.getY()][player.getX()]);
        
        // Update player's position
        dungeon[player.getY()][player.getX()] = '.'; // Clear old position
        player.moveTo(newX, newY); // Move player
        dungeon[newY][newX] = '@'; // Mark new position

        System.out.println("After move: Player at (" + newX + ", " + newY + ") - Tile: " + dungeon[newY][newX]);

        // Refresh game UI
        frame.updateStats(player.toString()); 
        frame.updateDungeon();
    }


    public void setDungeon(char[][] newDungeon) {
        this.dungeon = newDungeon;
    }


    // Used to draw the dungeon on screen
    public void updateDungeon() {
        DungeonFloor currentFloor = frame.dungeonFloors.get(frame.currentFloorIndex);
        char[][] originalMap = currentFloor.getOriginalMap();

        // Reset the map every key event so there aren't any @ sign trails
        for (int i = 0; i < dungeon.length; i++) {
            for (int j = 0; j < dungeon[i].length; j++) {
                dungeon[i][j] = originalMap[i][j]; // Reset map to original state
            }
        }

        // Place the stairs correctly
        dungeon[currentFloor.getStairY()][currentFloor.getStairX()] = '>';

        // Ensure traps remain visible if not hidden
        for (AbstractTrap trap : currentFloor.traps) {
            if (!trap.isHidden()) {
                dungeon[trap.getY()][trap.getX()] = '!'; // Keep traps in place
            }
        }

        // **Ensure corridors remain visible**
        for (int i = 0; i < dungeon.length; i++) {
            for (int j = 0; j < dungeon[i].length; j++) {
                if (originalMap[i][j] == '░') { 
                    dungeon[i][j] = '░'; // Keep corridors
                }
            }
        }

        // Draw the player on the map
        dungeon[player.getY()][player.getX()] = player.getIcon();

        // **Update UI to reflect the dungeon map**
        for (int i = 0; i < dungeon.length; i++) {
            for (int j = 0; j < dungeon[i].length; j++) {
                JLabel cell = gridLabels[i][j];
                char cellContent = dungeon[i][j];

                if (cellContent == '>') { // Stairs down
                    cell.setBackground(Color.GREEN);
                    cell.setText(""); 
                } else if (cellContent == '<') { // Stairs up
                    cell.setBackground(Color.BLUE);
                    cell.setText(""); 
                } else if (cellContent == '!') { // Trap
                    cell.setBackground(Color.RED);
                    cell.setText(""); 
                } else if (cellContent == '░') { // Corridor
                    cell.setBackground(new Color(20, 60, 20)); // Dark green
                    cell.setForeground(new Color(150, 255, 150)); // Light green
                    cell.setText("░");
                    System.out.println("Corridor at: (" + i + ", " + j + ")");
                } else if (cellContent == player.getIcon()) { // Player
                    cell.setBackground(Color.BLACK);
                    cell.setForeground(Color.ORANGE);
                    cell.setText(String.valueOf(cellContent));
                } else { // Default rendering for walls, floors, etc.
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
