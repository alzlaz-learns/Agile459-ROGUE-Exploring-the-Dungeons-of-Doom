package com.example.managers;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.ui.JFrameUI;
import com.models.Player;
import com.models.dungeonofdoom.Traps.AbstractTrap;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.monster.Monster;


public class GameManager {

    private Player player;
    private List<DungeonFloor> dungeonFloors;
    private int currentFloor;
    private JFrameUI frame;

    private MonsterManager monsterManager;
    

    public GameManager(JFrameUI frame) {
        this.frame = frame;
        this.player = new Player("hero");
        dungeonFloors = new ArrayList<>();

        this.monsterManager = new MonsterManager(new Random());
        
        // Create all dungeon floors
        for(int i = 1; i < 26; i++) {
            dungeonFloors.add(new DungeonFloor(i, 80, 22, monsterManager));
        }
        this.currentFloor = 0;
        // Place player on the first floor
        dungeonFloors.get(currentFloor).placePlayer(player);
    }

    //logic to handle player movement based off of JFramePlayGround
    //separated trap stuff from it
    //moved it all to Game Manager
    public void handleMovement(KeyEvent e) {
        if (player.isImmobile()) {
            frame.updateMessage("You are trapped and cannot move for " + player.getImmobile() + " more turns!");
            player.immobileDecrease();
            return; 
        }

        DungeonFloor currentDungeonFloor = dungeonFloors.get(currentFloor);
        char[][] dungeon = currentDungeonFloor.getMap();

        int newX = player.getX();
        int newY = player.getY();

        int keyCode = e.getKeyCode();
        
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
                if (e.isShiftDown()) { 
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

        // replaced all the redundant code with a method from floor to clean up stuff.
        if(!currentDungeonFloor.isWalkable(newX, newY)){
            frame.updateMessage("You cant go there!");
            return;
        }

        //todo: think of a way for player to handle running into a monster.
        if(currentDungeonFloor.monsterOccupies(newX, newY)){
            
            Monster monster = currentDungeonFloor.getMonsterAt(newX, newY);
           
            CombatManager.playerAttack(player, monster, currentDungeonFloor);
            
            frame.updateGameScreen();
            
            
            
        } else {
            // Move player only if the tile is walkable
            player.moveTo(newX, newY);
            checkTrap(newX, newY);
        }
        

        //check todo in Monstermanager.monsterAction()
        monsterManager.monsterAction(currentDungeonFloor, player);
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
                    frame.updateMessage(trapMessage);
                    trap.trigger(player);
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
            // Can't pass 0 or 25
            frame.updateMessage(goingDown ? "You have reached the bottom floor!" : "You cannot go any higher!");
            return;
        }

        // Get the new floor
        DungeonFloor newFloor = dungeonFloors.get(currentFloor);
        
        // Place the player in a random valid position on the new floor
        newFloor.placePlayer(player);

        // Update the game panel with the new floor
        frame.updateMessage("You are now on floor " + (currentFloor + 1));

        // Update the message area and game screen
        frame.updateGameScreen();
    }

    public Player getPlayer() {
        return player;
    }

    

    public DungeonFloor getCurrentFloor() {
        return dungeonFloors.get(currentFloor);
    }
}
