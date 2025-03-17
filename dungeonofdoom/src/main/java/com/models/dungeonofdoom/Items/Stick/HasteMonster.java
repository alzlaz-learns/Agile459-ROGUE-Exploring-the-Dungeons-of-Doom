package com.models.dungeonofdoom.Items.Stick;

import com.models.Player;
import com.models.dungeonofdoom.Items.ItemEffect;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.monster.Monster;

public class HasteMonster implements ItemEffect {

    public HasteMonster() {
    }

    @Override
    public void applyToPlayer(Player player, DungeonFloor df) {
        // Nothing to do here - this stick only affects monsters
    }

    @Override
    public void applyToMonster(Monster monster) {
        // Apply haste effect for 20-30 turns (similar to other effects in the game)
        int hasteDuration = 20 + (int)(Math.random() * 11); // 20 + random(0-10)
        monster.applyHaste(hasteDuration);
    }

    @Override
    public String messageStringPlayer(Player player) {
        return "You wave the haste monster stick!";
    }

    @Override
    public String messageStringMonster(Monster monster) {
        return "The " + monster.getName() + " begins moving much faster, ruh roh!";
    }
}