package com.models.dungeonofdoom.Items.Scroll;

import java.util.Random;

import com.models.Player;
import com.models.dungeonofdoom.Items.ItemEffect;
import com.models.dungeonofdoom.monster.Monster;

public class SleepScroll implements ItemEffect{
    private final Random rand;

    public SleepScroll(){
        this.rand = new Random();
    }
    public SleepScroll(Random rand){
        this.rand = rand;
    }

    @Override
    public void applyToPlayer(Player player) {
        int immobilizedTurns = rand.nextInt(5) + 1; // 1d5 turns
        player.setImmobile(immobilizedTurns);
    }

    @Override
    public void applyToMonster(Monster monster) {
        
    }

    @Override
    public String messageStringPlayer(Player player) {
        return "You fall asleep ";
    }

    @Override
    public String messageStringMonster(Monster monster) {
        return "";
    }

}
