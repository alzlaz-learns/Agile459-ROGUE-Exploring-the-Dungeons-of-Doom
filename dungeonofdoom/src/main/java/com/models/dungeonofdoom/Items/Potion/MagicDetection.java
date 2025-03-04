package com.models.dungeonofdoom.Items.Potion;

import com.models.Player;
import com.models.dungeonofdoom.Items.Item;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.monster.Monster;

public class MagicDetection implements PotionEffect{

    @Override
    public void applyToPlayer(Player player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyToPlayer'");
    }

    @Override
    public void applyToMonster(Monster monster) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyToMonster'");
    }

    @Override
    public String messageStringPlayer(Player player) {
        return "You sense the presence of magic!";
    }

    @Override
    public String messageStringMonster(Monster monster) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'messageStringMonster'");
    }

    @Override
    public void applyToPlayer(Player player, DungeonFloor dungeonFloor){
        for(Item i: dungeonFloor.getItems()){
            dungeonFloor.revealItemLocation(i);
        }
    }
}
