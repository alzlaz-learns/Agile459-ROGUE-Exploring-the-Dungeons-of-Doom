package com.example.managers;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import com.example.ui.GamePanel;
import com.example.ui.JFrameUI;
import com.models.Player;
import com.models.dungeonofdoom.Traps.AbstractTrap;
import com.playground.alex.DungeonFloor;
import com.playground.alex.GamePanelPlayground;

public class GameManager {

    private Player player;
    private List<DungeonFloor> dungeonFloors;
    private int currentFloor;
    private JFrameUI frame;
    private GamePanelPlayground gamePanel;

    public GameManager(JFrameUI frame) {

        this.frame = frame;
        //eventually need to consider how to take a user input to make a custom player naem.
        this.player = new Player("hero");
        dungeonFloors = new ArrayList<>();
        for(int i = 1; i < 26; i++){
            //hard coded need to consider in the future  how not to do that
            dungeonFloors.add(new DungeonFloor(i, 80, 22));
        }
        this.currentFloor = 0;
    }

    //logic to handle player movement based off of JFramePlayGround
    //separated trap stuff from it
    //moved it all to Game Manager
    public void handleMovement(KeyEvent e) {

        if (player.isImmobile()) {
            frame.updateMessage("You are trapped and cannot move for " + player.getImmobile() + " more turns!");
            player.immobileDecrease();
            // Stop movement if immobilized
            return; 
        }

        DungeonFloor currentDungeonFloor = dungeonFloors.get(currentFloor);
        char[][] dungeon = currentDungeonFloor.getMap();

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
                    
                    if (player.getX() == currentDungeonFloor.getStairX() && player.getY() == currentDungeonFloor.getStairY()) {
                        frame.updateMessage("Descending to floor " + (currentFloor));
                        
                        changeFloor(true);
                    } else {
                        frame.updateMessage("You need to be on the stairs (>) to go down!");
                    }
                    return;
                }
                break;
                case KeyEvent.VK_COMMA:
                // Detect if Shift is held down
                if (e.isShiftDown()) { 
                    // have not implemented this yet mostly just copy and pasted 
                    if (player.getX() == currentDungeonFloor.getStairX() && player.getY() == currentDungeonFloor.getStairY()) {
                        frame.updateMessage("Ascending to floor " + (currentFloor));
                        changeFloor(false);
                    } else {
                        frame.updateMessage("You need to be on the stairs (>) to go up!");
                    }
                    return;
                }
                break;

            default:
                return;
        }

        
        //swapped so traps can actually be stepped on when triggering
        player.moveTo(newX, newY);
        checkTrap(newX, newY);
        frame.updateStats(player.toString());
        
    }

    //logic to handle trapLogic based off of JFramePlayGround
    //separated trap stuff from it
    //moved it all to Game Manager
    private void checkTrap(int x, int y){
        AbstractTrap trap = dungeonFloors.get(currentFloor).getTrapAt(x, y);
        if (trap != null) {
            String trapMessage = trap.trigger(player);
            
            switch (trap.getEffect()) {
                case FALL -> {
                    frame.updateMessage(trapMessage);
                    //take player to lower floor
                    changeFloor(true); 
                }
                case HOLD -> {
                    frame.updateMessage(trapMessage);
                } 
                case TELEPORT -> {
                    frame.updateMessage(trapMessage);
                    trap.trigger(player);
                }
                case ARROW -> {
                    frame.updateMessage(trapMessage);
                    trap.trigger(player);
                }
                case DART -> {

                }
            }
            
            return;
        }
    }


    public void changeFloor(boolean goingDown) {
        if (goingDown && currentFloor < dungeonFloors.size() - 1) {
            currentFloor++;
        } else if (!goingDown && currentFloor > 0) {
            currentFloor--;
        } else {
            //cant pass 0 or 25
            return;
        }

        // Reset player position to a set spot 5,5
        // basic implementation
        //TODO: Eventualy have an implementation that places a player in a random position in a random room
        player.moveTo(5, 5);

        // Update the game panel with the new floor
        frame.updateMessage("You are now on floor " + (currentFloor + 1));

        // Update the message area
        frame.updateGameScreen();
    }

    public Player getPlayer() {
        return player;
    }

    public DungeonFloor getCurrentFloor() {
        return dungeonFloors.get(currentFloor);
    }
}
