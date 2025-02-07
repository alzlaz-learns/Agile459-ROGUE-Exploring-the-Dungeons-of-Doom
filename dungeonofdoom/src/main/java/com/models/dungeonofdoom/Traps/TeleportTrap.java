package com.models.dungeonofdoom.Traps;

import java.util.Random;

import com.models.Player;
import com.models.dungeonofdoom.enums.TrapTypeEnum;
public class TeleportTrap extends AbstractTrap{
    Random rand;
    private char[][] dungeon;
    public TeleportTrap(boolean hidden, char[][] dungeon, Random rand) {

        super(hidden, TrapTypeEnum.TELEPORT_TRAP);
        this.dungeon = dungeon;
        this.rand = rand;
    }

    
    public void applyEffect(Player player) {
        //effect is applied by game logic so this empty for nbow to be considered how to handle once a dungeons are properly generated.

        int newX, newY;
        int width = dungeon[0].length;
        int height = dungeon.length;
        
        // Ensure the player doesn't teleport onto a trap or stair
        do {
            newX = rand.nextInt(width);
            newY = rand.nextInt(height);
            System.out.println("Trying to teleport to: " + newX + "," + newY);
        } while (dungeon[newY][newX] == '!' || dungeon[newY][newX] == '>'); // Avoid teleporting onto traps or stairs

        System.out.println("Final teleport location: " + newX + "," + newY);
        player.moveTo(newX, newY);
    }


    @Override
    public String trigger(Player player) {
            this.reveal();
            applyEffect(player);
            return trapType.getMessage(); 
    }
    
}
