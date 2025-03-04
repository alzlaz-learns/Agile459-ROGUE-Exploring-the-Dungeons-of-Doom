package com.models.dungeonofdoom.Items.Potion;

import com.models.Player;
import com.models.dungeonofdoom.monster.Monster;

public class NoEffect implements PotionEffect{

    @Override
    public void applyToPlayer(Player player) {}

    @Override
    public void applyToMonster(Monster monster) {}

    @Override
    public String messageStringPlayer(Player player) {
        return "";
    }

    @Override
    public String messageStringMonster(Monster monster) {
        return "";
    }

}
