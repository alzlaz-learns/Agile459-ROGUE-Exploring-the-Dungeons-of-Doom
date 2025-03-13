package com.models.dungeonofdoom.Items.Scroll;

import com.models.Player;
import com.models.dungeonofdoom.Items.ItemEffect;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.monster.Monster;

public class AggravateMonster implements ItemEffect {

    @Override
    public void applyToPlayer(Player player, DungeonFloor floor) {
        System.out.println("You hear a high-pitched humming noise...");

        for (Monster monster : floor.getMonsters()) {
            monster.setAggressive(true); // Set all monsters to chase the player
            monster.activate();
        }
    }

    @Override
    public void applyToMonster(Monster monster) {
        throw new UnsupportedOperationException("Monsters cannot use this scroll.");
    }

    @Override
    public String messageStringPlayer(Player player) {
        return "You hear a high-pitched humming noise...";
    }

    @Override
    public String messageStringMonster(Monster monster) {
        return "";
    }
}
