package com.models.dungeonofdoom.Traps;

import java.awt.Point;
import java.util.List;
import java.util.Random;

import com.models.Player;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.enums.TrapTypeEnum;
public class TeleportTrap extends AbstractTrap{
    Random rand;
    private char[][] dungeon;
    private DungeonFloor df;
    public TeleportTrap(boolean hidden, char[][] dungeon, Random rand) {

        super(hidden, TrapTypeEnum.TELEPORT_TRAP);
        this.dungeon = dungeon;
        this.rand = rand;
    }

    public TeleportTrap(boolean hidden, DungeonFloor df, Random rand){
        super(hidden, TrapTypeEnum.TELEPORT_TRAP);
        this.df = df;
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

    public void applyEffect2(Player player) {
        // Get valid room tiles instead of raw map data
        List<Point> validTiles = df.getValidRoomTiles();

        if (validTiles.isEmpty()) {
            System.out.println("No valid tiles to teleport to!");
            return;
        }

        // Select a random valid room tile
        Point newTile = validTiles.get(rand.nextInt(validTiles.size()));

        System.out.println("Final teleport location: " + newTile.x + "," + newTile.y);
        player.moveTo(newTile.x, newTile.y);
    }


   

    @Override
    public String trigger(Player player) {
            this.reveal();
            applyEffect2(player);
            return trapType.getMessage(); 
    }
    
}
