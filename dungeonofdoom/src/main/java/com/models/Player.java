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
            Level: %d
            Hits: %s
            Str: %d
            Gold: %d
            Armor: %d
            Exp: %s""",
            level,
            getHealthStatus(),
            strength,
            gold,
            armor,
            getExperienceStatus());
    }
}
