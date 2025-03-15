package com.models;

import java.util.ArrayList;
import java.util.List;
import java.awt.Point;
import com.models.dungeonofdoom.Items.Item;
import com.models.dungeonofdoom.Items.Pack;
import com.models.dungeonofdoom.Items.Armor.Armor;
import com.models.dungeonofdoom.Items.Ring.Ring;

import com.models.dungeonofdoom.Items.Stick.Stick;
import com.models.dungeonofdoom.Items.Weapon.Weapon;
import com.models.dungeonofdoom.enums.RingEnum;
import com.models.dungeonofdoom.enums.StickEnum;
import com.models.dungeonofdoom.Items.Stick.Striking;
import java.util.Random;


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
    private Pack pack;
    private List<Item> equippedItems;
    private int maxStrength;
    private int minStrength;
    private boolean isCursed;

    //potion effects
    private int blindTimer;
    private int hasteTimer;
    private int faintTimer;
    private int revealTimer = 0;

    private Item leftRing;
    private Item rightRing;
    private Armor bodyArmor;
    private Weapon weapon;

    private Random random;

    
    // Constructor
    public Player(String name) {
        this.name = name;
        this.level = 1;
        this.maxHealth = 100;
        this.currentHealth = this.maxHealth;
        this.gold = 0;
        this.experience = 1;
        this.armor = 10;
        this.maxStrength = 31;
        this.minStrength = 3;
        this.strength = this.minStrength; // Default minimum strength
        //test attributes
        this.icon = '@'; //temporary should consider creating a ENUM to hold all character symbols that can be called.
        this.immobile = 0;
        this.confused = 0;
        this.equippedItems = new ArrayList<Item>();

        // potion
        
        this.blindTimer = 0;
        this.isCursed = false;
        this.hasteTimer = 0;
        this.faintTimer = 0;

        this.pack = new Pack();
        this.random = new Random();
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

    public void updateLvl(int i){
        this.level = i;
    }

    public void increaseLvl(){
        this.level ++;
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

    public boolean hasRingOfSustain(){
        return (leftRing != null && leftRing.getItemName().equals(RingEnum.SUSTAIN_STRENGTH.getName())) ||
           (rightRing != null && rightRing.getItemName().equals(RingEnum.SUSTAIN_STRENGTH.getName()));
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
        for (Item item : equippedItems) {
            // Handle bonus damage based on item type
            if (item instanceof Stick && ((Stick) item).getStickType() == StickEnum.STRIKING) {
                Striking strikingEffect = new Striking(random);
                baseDamage += strikingEffect.rollStrikingHit();
            } else if (item instanceof Weapon) {
                Weapon weapon = (Weapon) item;
                baseDamage += weapon.getDamageWielded();
            } 
            // Add more item types as needed
            bonusDamage += 0;   // don't what to do here yet, setting up some basics
        }
        
        // If no weapon equipped, use minimum damage
        if (baseDamage == 0) {
            baseDamage = 1;
        }

    
        return baseDamage + bonusDamage;
    }
    public Pack getPack() {
        return pack;
    }

    public void printPack() {
        pack.listInventory();
    }
    public void addItem(Item i){
        pack.addItem(i);
    }
    public int getHealth() {
        return currentHealth;
    }

    public Point getPosition(){
        Point position = new Point(x, y);
        return position;
    }


    public void heal(int amount) {
        if (this.currentHealth < this.maxHealth) {
            this.currentHealth = Math.min(this.maxHealth, this.currentHealth + amount);
        }
    }

    // public void eatFood() {
    //     if (pack.containsItem("Food")) {
    //         pack.dropItem(pack.getItem("Food"));
    //         hungerCounter = 1300;
    //         System.out.println("You eat some food. You feel refreshed.");//tochange later.
    //     } else {
    //         System.out.println("You have no food to eat.");//tochange later.
    //     }
    // }

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

    public void curseSelf(){
        this.isCursed = true;
        System.out.println("Monsters are scared of you now, but you aren't long for this world.");
        //TODO: add a timer that counts down to players death 
    }

    public void immobileDecrease(){
        if (isImmobile()) immobile --;
    }

    public void confusedDecrease(){
        if (isConfused()) confused --;
    }

    public boolean isDead(){
        return this.currentHealth < 1 || this.maxHealth < 1 || this.experience < 0;
    }

    //reduce armor by -1 since
    //changed for being more open for future calls if it happens.
    public void adjustArmor(int modification){
        this.armor += modification;
    }

    public void setArmor(int armor){
        this.armor = armor;
    }

    //making this so minimum is always one
    public void adjustStrength(int modifier){
        int oldStrength = this.strength;

        //changed to max minimum strength 3 max strength 31
        this.strength = Math.min(getMaxStrength(), Math.max(getMinStrength(), this.strength + modifier)); //TO DO I dont think i did this correct for when substracting strength?
        
        System.out.println("Strength changed: " + oldStrength + " → " + this.strength + " (Modifier: " + modifier + ")");
    
    }
    public void adjustMaxStrength(){
        //changed to max minimum strength 3 max strength 31
        this.maxStrength += 1;
    }

    public int getArmorClass(){
        return bodyArmor != null ? bodyArmor.getArmorClass() : armor;
    }

    public List<Item> getEquippedItems() {
        return equippedItems;
    }

    public int calculateStrengthWithItems(){
        return 0;
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

    public boolean isBlind(){
        return this.blindTimer > 0;
    }

    public void applyBlind(int blind){
        this.blindTimer += blind;
    }

    public void clearBlind(){
        this.blindTimer = 0;
    }

    public void decrementBlind(){
        if (isBlind()){
            this.blindTimer--;
        }
    }

    public boolean isHasted(){
        return this.hasteTimer > 0;
    }
    public void applyHaste(int turns){
        this.hasteTimer = turns;
    }

    public void decrementHaste(){
        if(isHasted()){
            this.hasteTimer--;
        }
    }

    public boolean isFainted(){
        return this.faintTimer > 0;
    }

    public void applyFaint(int turns){
        this.faintTimer = turns; 
    }

    public boolean isRevealed(){
        return revealTimer > 0;
    }

    public void reveal(int i){
        this.revealTimer += i;
    }

    public void decrementReveal(){
        if(isRevealed()){
            this.revealTimer --;
        }
    }

    public Armor getEquippedArmor(){
        return this.bodyArmor;
    }
    public String equipRing(Item ring) {
        System.out.println("equipRing() called with: " + ring.getItemName());
    
        if (!(ring instanceof Ring)) {
            System.out.println("Not a ring!"); 
            return "You can only equip rings!";
        }
    
        if (leftRing == null) {
            leftRing = ring;
            System.out.println("Ring equipped on left hand.");
        } else if (rightRing == null) {
            rightRing = ring;
            System.out.println("Ring equipped on right hand.");
        } else {
            System.out.println("Both ring slots full!");
            return "You are already wearing two rings.";
        }
    
        ring.equip();
        System.out.println("Final Equipped Status: " + ring.isEquipped());
        return "You equipped " + ring.getItemName();
    }

    public String equipWeapon(Item w){
        if (!(w instanceof Weapon)) {
            return "You can only equip weapons!";
        }
    
    
        weapon = (Weapon) w;
        w.equip();

        return w.message(this);
    }
    
    public String equipArmor(Item w){
        if (!(w instanceof Armor)) {
            return "You can only equip Armors!";
        }

        if(this.bodyArmor != null) return "Must reomve current armor first";
    
    
        bodyArmor = (Armor) w;
        w.equip();

        return w.message(this);
    }
}
