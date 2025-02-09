package com.models;

import com.player.uday.MonsterPlayground;
import com.player.uday.PlayerPackPlayground;

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
    private int hungerCounter;

    //test attributes
    private char icon;
    private int x; // X position
    private int y; // Y position
    private int immobile;
    private PlayerPackPlayground pack;
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

    public void takeDamage(int damage) {
        this.currentHealth -= damage;
        if (this.currentHealth <= 0) {
            die();
        }
    }

    public void die() {
        System.out.println(name + " has died."); // to be changed to output on screen
        int goldLost = (int) (gold * 0.9);
        gold -= goldLost;
        System.out.println(goldLost + " gold added to the scoreboard."); // to be changed to output on screen
    }

    
    // to be changed to output on screen when monster
    public void attack(MonsterPlayground monster) {
        int damage = 10;
        monster.takeDamage(damage);
        // to be changed to output on screen
        System.out.println("You attack the " + monster.getName() + " for " + damage + " damage.");
    }

    public PlayerPackPlayground getPack() {
        return pack;
    }

    public int getHealth() {
        return currentHealth;
    }


    public void heal(int amount) {
        this.currentHealth = Math.min(this.maxHealth, this.currentHealth + amount);
    }

    public void eatFood() {
        if (pack.containsItem("Food")) {
            pack.dropItem(pack.getItem("Food"));
            hungerCounter = 1300;
            System.out.println("You eat some food. You feel refreshed.");//tochange later.
        } else {
            System.out.println("You have no food to eat.");//tochange later.
        }
    }

    public void updateHunger() {
        hungerCounter--;
        if (hungerCounter == 300) {
            System.out.println("You are starting to get hungry."); // to be changed to output on screen
        } else if (hungerCounter == 150) {
            System.out.println("You are starting to feel weak.");// to be changed to output on screen
        } else if (hungerCounter == 0) {
            System.out.println("You feel very weak. You faint.");// to be changed to output on screen
        } else if (hungerCounter == -850) {
            System.out.println("You have starved to death.");// to be changed to output on screen
            die();
        }
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
