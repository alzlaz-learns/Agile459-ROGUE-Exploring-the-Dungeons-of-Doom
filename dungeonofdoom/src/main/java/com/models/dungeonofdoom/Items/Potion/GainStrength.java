package com.models.dungeonofdoom.Items.Potion;

import com.models.Player;
import com.models.dungeonofdoom.monster.Monster;

public class GainStrength implements PotionEffect{

    @Override
    public void applyToPlayer(Player player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyToPlayer'");
    }

    @Override
    public void applyToMonster(Monster monster) {
        // never should be called so 
        throw new UnsupportedOperationException("Unimplemented method 'applyToMonster'");
    }

    @Override
    public String messageStringPlayer(Player player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'messageStringPlayer'");
    }

    @Override
    public String messageStringMonster(Monster monster) {
        // never should be called
        throw new UnsupportedOperationException("Unimplemented method 'messageStringMonster'");
    }

}
