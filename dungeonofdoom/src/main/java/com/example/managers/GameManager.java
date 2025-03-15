package com.example.managers;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.ui.JFrameUI;
import com.models.Player;
import com.models.dungeonofdoom.Items.Item;
import com.models.dungeonofdoom.Traps.AbstractTrap;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.enums.ItemOptions;
import com.models.dungeonofdoom.monster.Monster;


public class GameManager {

    private Player player;
    private List<DungeonFloor> dungeonFloors;
    private int currentFloor;
    private JFrameUI frame;
    
    private MonsterManager monsterManager;
    
    private boolean processing = false;
    private ItemOptions currentProcessingOption = null;

    public GameManager(JFrameUI frame) {
        this.frame = frame;
        this.player = new Player("hero");
        dungeonFloors = new ArrayList<>();

        this.monsterManager = new MonsterManager(new Random(), frame);
        
        // Create all dungeon floors
        for(int i = 1; i < 26; i++) {
            dungeonFloors.add(new DungeonFloor(i, 80, 22, monsterManager));
        }
        this.currentFloor = 0;
        // Place player on the first floor
        dungeonFloors.get(currentFloor).placePlayer(player);
        dungeonFloors.get(currentFloor).discoverMonsterInRoom(player.getX(), player.getY());
        dungeonFloors.get(currentFloor).discoverItemsInRoom(player.getX(), player.getY());
    }   

    //logic to handle player movement based off of JFramePlayGround
    //separated trap stuff from it
    //moved it all to Game Manager
    public void handleMovement(KeyEvent e) {
        // Check if player is dead first
        if (player.isDead()) {
            frame.updateMessage("Game Over! You are dead.");
            return;
        }

        if (player.isImmobile()) {
            frame.updateMessage("You are trapped and cannot move for " + player.getImmobile() + " more turns!");
            player.immobileDecrease();
            return; 
        }

        if (processing){
            processInput(e.getKeyChar());
            return;
        }
        
        
        DungeonFloor currentDungeonFloor = dungeonFloors.get(currentFloor);
        char[][] dungeon = currentDungeonFloor.getMap();

        if(player.isRevealed()){
            player.decrementReveal();
            // currentDungeonFloor.revealMonstersOnMap();
        }

        if(player.isBlind()){
            player.decrementBlind();

        }


        int newX = player.getX();
        int newY = player.getY();

        int keyCode = e.getKeyCode();

        int moveMultiplier = player.isHasted() ? 2 : 1;
        player.decrementHaste();

        if (player.isConfused()) {
            frame.updateMessage("Wait, what's going on? Huh? What? Who?");
            
            Random rand = new Random();
            int randomDirection = rand.nextInt(4); 
            
            switch (randomDirection) {
                case 0: if (newY > 0) newY -= moveMultiplier; break; 
                case 1: if (newY < dungeon.length - 1) newY += moveMultiplier; break; 
                case 2: if (newX > 0) newX -= moveMultiplier; break; 
                case 3: if (newX < dungeon[0].length - 1) newX += moveMultiplier; break;
            }
    
            player.confusedDecrease(); // Reduce confusion counter
        } else{
            switch (keyCode) {
                case KeyEvent.VK_D:
                    handleInput(ItemOptions.ALL);
                    return;
                case KeyEvent.VK_T:
                    if(e.isShiftDown()) {
                        String res = player.unEquipArmor();
                        frame.updateMessage(res);
                    }
                    return; 
                case KeyEvent.VK_W:
                    if(e.isShiftDown()){
                        handleInput(ItemOptions.WEARABLE);
                    } else{
                        handleInput(ItemOptions.WIELDABLE);
                    }
                    
                    return;
                case KeyEvent.VK_I:
                    frame.showInventoryScreen(player, ItemOptions.ALL);
                    return;
                case KeyEvent.VK_Q:
                    handleInput(ItemOptions.QUAFFABLE);
                    return;
                case KeyEvent.VK_P:
                    handleInput(ItemOptions.PUTTABLE);
                    return;
                case KeyEvent.VK_K: 
                case KeyEvent.VK_UP: 
                    if (newY > 0) newY -= moveMultiplier;
                    break;
                case KeyEvent.VK_J:           
                case KeyEvent.VK_DOWN: 
                    if (newY < dungeon.length - 1) newY += moveMultiplier;
                    break;
                case KeyEvent.VK_H: 
                case KeyEvent.VK_LEFT: 
                    if (newX > 0) newX -= moveMultiplier;
                    break;
                case KeyEvent.VK_L: 
                case KeyEvent.VK_RIGHT:
                    if (newX < dungeon[0].length - 1) newX += moveMultiplier;
                    break;
                case KeyEvent.VK_PAGE_UP: 
                    if (newY > 0 && newX < dungeon[0].length - 1) {
                        newX+= moveMultiplier;
                        newY-= moveMultiplier;
                    }
                    break;
                case KeyEvent.VK_PAGE_DOWN: 
                    if (newY < dungeon.length - 1 && newX < dungeon[0].length - 1) {
                        newX+= moveMultiplier;
                        newY+= moveMultiplier;
                    }
                    break;
                case KeyEvent.VK_HOME: 
                    if (newY > 0 && newX > 0) {
                        newX-= moveMultiplier;
                        newY-= moveMultiplier;
                    }
                    break;
                case KeyEvent.VK_END: 
                    if (newY < dungeon.length - 1 && newX > 0) {
                        newX-= moveMultiplier;
                        newY+= moveMultiplier;
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
                case KeyEvent.VK_1:

                default:
                    return;
                    
            }
        }
        
        //check if player enters the room to activate
        checkRoomEntry(newX, newY);
        
        if (currentDungeonFloor.isInsideRoom(newX, newY)) {
            currentDungeonFloor.revealRoomAt(newX, newY);
            currentDungeonFloor.discoverMonsterInRoom(newX, newY);
            currentDungeonFloor.discoverItemsInRoom(newX, newY);
        } else {
            currentDungeonFloor.revealCorridorAt(newX, newY);
        }

        
        // System.out.println("X: " + player.getX()+ " Y: " +player.getY());
        handleMovement(newX, newY);

        frame.updateGameScreen();
    }
    
    public void processInput(char input){
        //escapes the input loop
        if(input == KeyEvent.VK_ESCAPE){
            processing = false;
            currentProcessingOption = null; 
            return;
        }
        if(input == '*'){
            frame.showInventoryScreen(player, currentProcessingOption);
        }

        List<Item> availableItems = player.getPack().getItemsByType(currentProcessingOption);
        input = Character.toLowerCase(input);
        int index = input - 'a';

        if (index >= 0 && index < availableItems.size()) {
            // Item selectedItem = availableItems.get(index);
            if (currentProcessingOption == ItemOptions.PUTTABLE) {
                String equipMessage = player.getPack().equipItem(index, player, dungeonFloors.get(currentFloor));
                frame.updateMessage(equipMessage);
            } 
            
            else if (currentProcessingOption == ItemOptions.QUAFFABLE){
                player.getPack().useItem(index, player, dungeonFloors.get(currentFloor)); 
            }

            else if (currentProcessingOption == ItemOptions.WIELDABLE){
                String wieldMessage = player.getPack().equipItem(index, player, dungeonFloors.get(currentFloor));
                frame.updateMessage(wieldMessage);
            } else if (currentProcessingOption == ItemOptions.WEARABLE){
                String wearMessage = player.getPack().equipItem(index, player, dungeonFloors.get(currentFloor));
                frame.updateMessage(wearMessage);
            }else if (currentProcessingOption == ItemOptions.ALL){
                player.getPack().dropObject(index, player, dungeonFloors.get(currentFloor));
                // frame.updateMessage();
            }
            
            processing = false; 
        }
    }

    
    private void handleInput(ItemOptions option){
        currentProcessingOption = option;
        String result =  String.format("Which object do you want to %s? (* for list)", option.getName());
        frame.updateMessage(result);
        processing = true;
    }

    private void handleMovement(int newX, int newY) {
        DungeonFloor currentDungeonFloor = dungeonFloors.get(currentFloor);

        
        //check if we can move there and if we can clean up stuff.
        if(!currentDungeonFloor.isWalkable(newX, newY)){
            frame.updateMessage("You cant go there!");
            return;
        }

        //todo: think of a way for player to handle running into a monster.
        if(currentDungeonFloor.monsterOccupies(newX, newY)){
            Monster monster = currentDungeonFloor.getMonsterAt(newX, newY);
            CombatManager.combatOrdering(player, monster, currentDungeonFloor, frame);
            frame.updateGameScreen();
            // Reset healing counter after combat
            TurnManager.resetNonCombatCounter();
        } else {

            // Move player only if the tile is walkable
            player.moveTo(newX, newY);
            checkTrap(newX, newY);
            checkItem(newX, newY);
            // Process healing for non-combat turn
            TurnManager.processNonCombatTurn(player, frame);
        }


        monsterManager.monsterAction(currentDungeonFloor, player);
        frame.updateStats(player.toString());
    }

    private void checkRoomEntry(int x, int y) {
        DungeonFloor currentDungeonFloor = dungeonFloors.get(currentFloor);
        
        if (currentDungeonFloor.isInsideRoom(x, y)) {
            monsterManager.activateRoomMonsters(currentDungeonFloor, x, y);
        }
    }

    private void checkItem(int x, int y){
        Item item = dungeonFloors.get(currentFloor).getItemAt(x, y);
        if(item != null){
            dungeonFloors.get(currentFloor).removeItem(item);
            player.addItem(item);
            // player.printPack();
            // System.out.println(player.getPack());
        }
        
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
                    trap.trigger(player);
                    //take player to lower floor
                    changeFloor(true); 
                }
                case HOLD -> {
                    frame.updateMessage(trapMessage);
                    trap.trigger(player);
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
            
            frame.updateGameScreen();
            return;
        }
    }

    

    public void changeFloor(boolean goingDown) {
        if (goingDown && currentFloor < dungeonFloors.size() - 1) {
            currentFloor++;
            player.increaseLvl();
            System.out.println(player.getLevel());
            frame.updateStats(player.toString());
        } else if (!goingDown && currentFloor > 0) {
            currentFloor--;
            player.increaseLvl();
            frame.updateStats(player.toString());
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
