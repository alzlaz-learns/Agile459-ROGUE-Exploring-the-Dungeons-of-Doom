package com.models.dungeonofdoom.Items.Stick;

import com.models.Player;
import com.models.dungeonofdoom.Items.ItemEffect;
import com.models.dungeonofdoom.monster.Monster;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.dungeonfloor.Room;

public class DrainLife implements ItemEffect {

    private DungeonFloor dungeonFloor;
    private int drainAmount = 0;

    public DrainLife(DungeonFloor dungeonFloor) {
        this.dungeonFloor = dungeonFloor;
    }

    @Override
    public void applyToPlayer(Player player, DungeonFloor df) {
        player.setCurrentHealth(player.getCurrentHealth() / 2);
        drainAmount = player.getCurrentHealth();
        
        // Get the room the player is in
        Room playerRoom = dungeonFloor.getRoomAt(player.getX(), player.getY());
        
        if (playerRoom != null) {
            // Apply drain effect to all monsters in the same room
            for (Monster monster : dungeonFloor.getMonsters()) {
                if (playerRoom.contains(monster.getX(), monster.getY())) {
                    // Drain health from the monster
                    monster.setCurrentHpt(monster.getHpt() - drainAmount);
                }
            }
        }
    }

    @Override
    public void applyToMonster(Monster monster) {
        // just gonna use the above method for now 
    }

    @Override
    public String messageStringPlayer(Player player) {
        return "";
    }

    @Override
    public String messageStringMonster(Monster monster) {
        return "";
    }
}
