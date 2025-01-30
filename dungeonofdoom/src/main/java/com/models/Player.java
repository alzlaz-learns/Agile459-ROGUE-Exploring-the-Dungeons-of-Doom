package com.models;

import lombok.Data;

@Data
public class Player {
    private final String name;
    private int level;
    private int currentHealth;
    private int maxHealth;
    private int gold;
    private int experience;
    private int armor;
    private int strength;
    //test attributes
    private char icon;
    private int x; // X position
    private int y; // Y position
    private int immobile;
    // Constructor
    public Player(String name) {
        this.name = name;
        this.level = 1;
        this.currentHealth = 100;
        this.maxHealth = 100;
        this.gold = 0;
        this.experience = 0;
        this.armor = 0;
        this.strength = 3; // Default minimum strength
        //test attributes
        this.icon = '@'; //temporary should consider creating a ENUM to hold all character symbols that can be called.
        this.immobile = 0;
    }
    
    // Core Utility Methods
    public boolean isAlive() {
        return currentHealth > 0;
    }

    public String getHealthStatus() {
        return currentHealth + " (" + maxHealth + ")";
    }

    public String getExperienceStatus() {
        return level + "/" + experience;
    }

    @Override
    public String toString() {
        return String.format("""
            Level: %d\tHits: %s\tStr: %d\tGold: %d\tArmor: %d\tExp: %s""",
            level,               
            getHealthStatus(),   
            strength,            
            gold,                
            armor,               
            getExperienceStatus() 
        );
    }


    //test method
    public void moveTo(int x, int y) {
        if (!isImmobile()) {
            this.x = x;
            this.y = y;
        }
    }

    //Lombok should generate a setter and getter for immobile
    public boolean isImmobile(){
        return immobile > 0;
    }

    public void immobileDecrease(){
        if (isImmobile()) immobile --;
    }
}
