package com.models.dungeonofdoom.Items.Potion;

import com.models.Player;
import com.models.dungeonofdoom.monster.Monster;

public class ThirstQuenching implements PotionEffect{

    @Override
    public void applyToPlayer(Player player) {
        
    }

    @Override
    public void applyToMonster(Monster monster) {
        
    }

    @Override
    public String messageStringPlayer(Player player) {
        return "This potion tastes extremely dull ";
    }

    @Override
    public String messageStringMonster(Monster monster) {
        return "";
    }

}
