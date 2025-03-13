package com.models.dungeonofdoom.Items.Stick;

import java.util.Random;

import com.models.Player;
import com.models.dungeonofdoom.Items.ItemEffect;
import com.models.dungeonofdoom.enums.StickEnum;
import com.models.dungeonofdoom.monster.Monster;

import com.models.dungeonofdoom.Helper.Pair;

public class Striking implements ItemEffect {

    private Random randomHitBoost;
    private Pair<Integer, Integer> hitBoostRange = new Pair<>(3, 8);
    
    public Striking(Random randomHitBoost) {
        this.randomHitBoost = randomHitBoost;
    }
    

    @Override
    public void applyToPlayer(Player player) {
        // do nothing 
    }

    @Override
    public void applyToMonster(Monster monster) {
        // do nothing 
    }

    public int rollStrikingHit(){
        if (randomHitBoost.nextDouble() <= 0.05) { // 5% chance
            return randomHitBoost.nextInt(hitBoostRange.getB() - hitBoostRange.getA() + 1) + hitBoostRange.getA();
        }
        Pair<Integer, Integer> damage = StickEnum.STRIKING.getDamage().get();
        return damage.getA() + randomHitBoost.nextInt(damage.getB() - damage.getA() + 1);
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
