package com.models.dungeonofdoom.Items.Potion;

import com.models.Player;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.monster.Monster;

public interface PotionEffect {
    void applyToPlayer(Player player);
    void applyToMonster(Monster monster);
    String messageStringPlayer(Player player);
    String messageStringMonster(Monster monster);

    default void applyToPlayer(Player player, DungeonFloor floor) {
        applyToPlayer(player); 
    }
}
