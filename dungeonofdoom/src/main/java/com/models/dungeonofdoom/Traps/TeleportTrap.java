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
        // Get valid room tiles instead of raw map data
        List<Point> validTiles = df.getValidRoomTiles();

        if (validTiles.isEmpty()) {
            System.out.println("No valid tiles to teleport to!");
            return;
        }

        Point newTile;
        do {
            newTile = validTiles.get(rand.nextInt(validTiles.size()));
        } while (df.getTrapAt(newTile.x, newTile.y) != null); 

        System.out.println("Teleporting to: " + newTile.x + "," + newTile.y);
        player.moveTo(newTile.x, newTile.y);
        df.revealRoomAt(newTile.x, newTile.y);
        df.revealCorridorAt(newTile.x, newTile.y);
    }


   

    @Override
    public String trigger(Player player) {
            this.reveal();
            applyEffect(player);
            return trapType.getMessage(); 
    }
    
}
