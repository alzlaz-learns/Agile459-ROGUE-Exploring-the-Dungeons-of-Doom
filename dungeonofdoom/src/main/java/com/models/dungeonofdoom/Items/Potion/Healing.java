package com.models.dungeonofdoom.Items.Potion;



import com.models.Player;
import com.models.dungeonofdoom.Items.ItemEffect;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.monster.Monster;

public class Healing implements ItemEffect{

    @Override
    public void applyToPlayer(Player player, DungeonFloor d) {
        // TODO Auto-generated method stub
        for(int i = 0; i < player.getLevel(); i ++) player.heal(4);
    }

    @Override
    public void applyToMonster(Monster monster) {
        // TODO Auto-generated method stub
        for(int i = 0; i < monster.getLevel(); i ++) monster.heal(4);
    }

    @Override
    public String messageStringPlayer(Player player) {
        // TODO Auto-generated method stub
        return "You begin to feel better";
    }

    @Override
    public String messageStringMonster(Monster monster) {
        // TODO Auto-generated method stub
        return "";
    }

}
