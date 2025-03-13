package com.models.dungeonofdoom.Items.Potion;

import com.models.Player;
import com.models.dungeonofdoom.Items.ItemEffect;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.monster.Monster;

public class OtherPotion implements ItemEffect{

    @Override
    public void applyToPlayer(Player player, DungeonFloor d) {
    }

    @Override
    public void applyToMonster(Monster monster) {
        
    }

    @Override
    public String messageStringPlayer(Player player) {
        return "What an odd tasting potion! ";
    }

    @Override
    public String messageStringMonster(Monster monster) {
        return "";
    }

}
