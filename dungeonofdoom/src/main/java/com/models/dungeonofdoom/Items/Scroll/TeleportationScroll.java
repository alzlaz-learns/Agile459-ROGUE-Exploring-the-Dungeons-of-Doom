package com.models.dungeonofdoom.Items.Scroll;

import java.awt.Point;
import java.util.List;
import java.util.Random;

import com.models.Player;
import com.models.dungeonofdoom.Items.ItemEffect;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.monster.Monster;

public class TeleportationScroll implements ItemEffect{
    private final Random rand;

    public TeleportationScroll(){
        this.rand = new Random();
    }

    public TeleportationScroll(Random rand){
        this.rand = rand;
    }
    

    @Override
    public void applyToPlayer(Player player, DungeonFloor df) {
        List<Point> validTiles = df.getValidRoomTiles();

        if (validTiles.isEmpty()) {
            System.out.println("No valid tiles to teleport to!");
            return;
        }

        
        Point newTile = validTiles.get(rand.nextInt(validTiles.size()));


        System.out.println("[BEFORE TELEPORT] Player at: " + player.getX() + "," + player.getY());

        // Move the player
        player.moveTo(newTile.x, newTile.y);

        System.out.println("[AFTER TELEPORT] Player at: " + player.getX() + "," + player.getY());

        df.revealRoomAt(newTile.x, newTile.y);
        df.revealCorridorAt(newTile.x, newTile.y);
    }




    @Override
    public void applyToMonster(Monster monster) {
        
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
