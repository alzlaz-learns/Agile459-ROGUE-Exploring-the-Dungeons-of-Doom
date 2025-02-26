package com.models.dungeonofdoom.Items.Potion;

import java.util.Random;

import com.models.Player;
import com.models.dungeonofdoom.monster.Monster;

public class ExtraHealing implements PotionEffect{

    private final Random random;
    
    public ExtraHealing(Random random) {
        this.random = random;
    }

    public ExtraHealing() {
        this.random = new Random();
    }

    @Override
    public void applyToPlayer(Player player) {
        int x = player.getLevel();
        int heal = healStrength(x);

        int max = player.getMaxHealth();
        int res = player.getCurrentHealth() + heal;

        if(res > max){
            player.setMaxHealth(max + 2);
            player.setCurrentHealth(player.getMaxHealth());
        }else{
            player.heal(heal);
        }
    }

    @Override
    public void applyToMonster(Monster monster) {
        int x = monster.getLevel();
        int heal = healStrength(x);
    
        int max = monster.getMaxHpt();
        int res = monster.getHpt() + heal;

    
        if (res > max) {
            monster.setMaxHpt(max + 2);
            monster.setCurrentHpt(monster.getMaxHpt());
        } else {
            monster.heal(heal);
        }
    }

    @Override
    public String messageStringPlayer(Player player) {
        // TODO Auto-generated method stub
       return "You begin to feel much better ";
    }

    @Override
    public String messageStringMonster(Monster monster) {
        return "";
    }

    private int healStrength(int x){
        int totalHealing = 0;
        for(int i = 0; i < x; i++){
            totalHealing += random.nextInt(8) + 1;
        }

        return totalHealing;
    }

}
