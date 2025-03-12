package com.models.dungeonofdoom.Items.Stick;

import com.models.Player;
import com.models.dungeonofdoom.Items.Item;
import com.models.dungeonofdoom.Items.ItemEffect;
import com.models.dungeonofdoom.monster.Monster;
import com.models.dungeonofdoom.enums.StickEnum;

import java.util.Random;

public class Stick extends Item {
    private StickEnum stickType;
    private ItemEffect effect;
    private int charges;
    private Random random = new Random();

    public Stick() {
    }

    public Stick(StickEnum stickType, ItemEffect effect) {
        this.stickType = stickType;
        this.effect = effect;
        
        // Initialize charges if applicable
        if (stickType.getCharges().isPresent()) {
            int min = stickType.getCharges().get().getA();
            int max = stickType.getCharges().get().getB();
            this.charges = random.nextInt(max - min + 1) + min;
        } else {
            this.charges = 0; // No charges
        }
    }

    public StickEnum getStickType() {
        return stickType;
    }

    public int getCharges() {
        return charges;
    }
    
    public void decrementCharges() {
        if (charges > 0) {
            charges--;
        }
    }
    
    public boolean hasCharges() {
        return charges > 0;
    }
    
    public int rollDamage() {
        if (stickType != null && stickType.getDamage().isPresent()) {
            int min = stickType.getDamage().get().getA();
            int max = stickType.getDamage().get().getB();
            return random.nextInt(max - min + 1) + min;
        }
        return 0;
    }

    @Override
    public void message(Player p) {
        System.out.println("You wave the " + getItemName() + 
                ". It has " + charges + " charges remaining.");
    }

    @Override
    public void message(Monster m) {
        System.out.println("The " + m.getName() + 
                " waves the " + getItemName() + ".");
    }

    @Override
    public void effect(Player p) {
        if (effect != null && charges > 0) {
            effect.applyToPlayer(p);
            decrementCharges();
        }
    }

    @Override
    public void effect(Monster m) {
        if (effect != null && charges > 0) {
            effect.applyToMonster(m);
            decrementCharges();
        }
    }

    @Override
    public String getItemName() {
        return stickType != null ? 
                stickType.name().toLowerCase().replace('_', ' ') + " stick" : 
                "unknown stick";
    }

    @Override
    public char getSymbol() {
        return stickType != null ? stickType.getSymbol() : '?';
    }
}
