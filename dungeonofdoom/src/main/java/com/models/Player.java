package com.models;

import java.util.ArrayList;
import java.util.List;


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
    private int confused;
    private PlayerPackPlayground pack;
    private List<Integer> equippedItems; //placeHolder <Integer>
    // Constructor
    public Player(String name) {
        this.name = name;
        this.level = 1;
        this.maxHealth = 100;
        this.currentHealth = this.maxHealth;
        this.gold = 0;
        this.experience = 0;
        this.armor = 5;
        this.strength = 3; // Default minimum strength
        //test attributes
        this.icon = '@'; //temporary should consider creating a ENUM to hold all character symbols that can be called.
        this.immobile = 0;
        this.confused = 0;
        this.equippedItems = new ArrayList<Integer>();
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
    
    //updated to handle monsters.
    //updated this to be inline with naming of monster attacking (calculateDamage)
    //updated to add place holder value for items so we can set up Item handling in the future.
    public int calculateDmg() {
        // Base damage will come from equipped weapon (currently placeholder)
        int baseDamage = 0;
        int bonusDamage = 0;
        
        // For now, we're using placeholder integers for items
        // This should be updated when proper item system is implemented
        for (Integer item : equippedItems) {
            baseDamage += item; // Base damage from weapon
            bonusDamage += 0;   // Bonus damage from magical enhancements
        }
        
        // If no weapon equipped, use minimum damage
        if (baseDamage == 0) {
            baseDamage = 1;
        }
        
        return baseDamage + bonusDamage;
    }

    public PlayerPackPlayground getPack() {
        return pack;
    }

    public int getHealth() {
        return currentHealth;
    }


    public void heal(int amount) {
        if (this.currentHealth < this.maxHealth) {
            this.currentHealth = Math.min(this.maxHealth, this.currentHealth + amount);
        }
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

    public boolean isConfused(){
        return confused > 0;
    }

    public void immobileDecrease(){
        if (isImmobile()) immobile --;
    }

    public void confusedDecrease(){
        if (isConfused()) confused --;
    }

    public boolean isDead(){
        return this.currentHealth < 1 || this.maxHealth < 1;
    }

    //reduce armor by -1 since
    //changed for being more open for future calls if it happens.
    public void adjustArmor(int modification){
        this.armor += modification;
    }

    //making this so minimum is always one
    public void adjustStrength(int modifier){
        this.strength = Math.max(1, this.strength + modifier);
    }

    public void adjustMaxHealth(int modifier){
        this.maxHealth += modifier;
    }
    public void adjustGold(int modifier){
        //gold cant become negative.
        this.gold = Math.max(0, this.gold + modifier);
    }

    public void adjustExperience(int modifier){
        this.experience += modifier;
    }
}
