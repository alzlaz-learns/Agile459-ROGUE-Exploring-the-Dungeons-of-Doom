package com.models.dungeonofdoom.Items.Stick;

import java.util.Random;

import com.models.Player;
import com.models.dungeonofdoom.Items.ItemEffect;
import com.models.dungeonofdoom.monster.Monster;

import com.models.dungeonofdoom.Helper.Pair;

public class SlowMonster implements ItemEffect {

    private Pair<Integer, Integer> slowRoll = new Pair<>(1, 4);
    private Random random = new Random();

    public SlowMonster(Random random){
        this.random = random;
    }

    @Override
    public void applyToPlayer(Player player) {
        // do nothing 
    }

    @Override
    public void applyToMonster(Monster monster) {
        int turns = random.nextInt(slowRoll.getB() - slowRoll.getA() + 1) + slowRoll.getA();
        monster.applySlow(turns);
    }

    @Override
    public String messageStringPlayer(Player player) {
        return "";
    }

    @Override
    public String messageStringMonster(Monster monster) {
        return "";
    }

}
