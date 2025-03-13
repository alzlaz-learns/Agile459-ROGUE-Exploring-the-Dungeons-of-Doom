package com.models.dungeonofdoom.Items.Stick;

import java.awt.Point;

import com.models.Player;
import com.models.dungeonofdoom.Items.ItemEffect;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.monster.Monster;

public class TeleportTo implements ItemEffect {

    private DungeonFloor dungeonFloor;  
    private Player player;

    public TeleportTo(DungeonFloor dungeonFloor, Player player) {
        this.dungeonFloor = dungeonFloor;
        this.player = player;
    }

    @Override
    public void applyToPlayer(Player player) {
        // do nothing 
    }

    @Override
    public void applyToMonster(Monster monster) {
        int xCord = player.getX();
        int yCord = player.getY();

        Point playerLocation = new Point(xCord, yCord);
        dungeonFloor.teleportMonsterTo(monster, playerLocation);
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
