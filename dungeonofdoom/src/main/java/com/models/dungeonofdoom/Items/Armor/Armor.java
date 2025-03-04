package com.models.dungeonofdoom.Items.Armor;

import com.models.Player;
import com.models.dungeonofdoom.monster.Monster;
import com.models.dungeonofdoom.enums.ArmorEnum;
import com.models.dungeonofdoom.Items.Item;

public class Armor extends Item {

    private ArmorEnum armorType;
    private boolean isEnchanted;
    private boolean isCursed;
    private int armorClass;

    public Armor(ArmorEnum armorType) {
        this.armorType = armorType;
        this.isEnchanted = false;
        this.isCursed = false;
        this.armorClass = armorType.getArmorClass();
    }

    public ArmorEnum getArmorType() {
        return armorType;
    }

    public String getName() {
        return armorType.getName();
    }

    public int getArmorClass() {
        return armorClass;
    }

    public void enchantArmor(){
        this.isEnchanted = true;
        System.out.println("The armor glows with a strange energy.");
        armorClass = armorType.getArmorClass() - 1;
    }

    public boolean isEnchanted(){
        return isEnchanted;
    }

    public boolean isCursed(){
        return isCursed;
    }

    public void curseArmor(){
        this.isCursed = true;
        System.out.println("The armor is now cursed. You feel rotten.");
        armorClass = armorType.getArmorClass() + 1;
    }

    @Override
    public void message(Player p) {
        System.out.println("You put on the " + armorType.getName() + 
        "and your armor class is now " +
         armorType.getArmorClass() + ".");
    }

    @Override
    public void message(`Monster m) {
        System.out.println("The " + m.getName() +
         " puts on the " + armorType.getName() + 
         " and its armor class is now " +
         armorType.getArmorClass() + ".");
    }

    @Override
    public void effect(Player p) {
        // Set the player's armor class based on this armor
        p.setArmor(this.getArmorClass());
    }

    @Override
    public void effect(Monster m) {
        // If monsters have an armor property similar to players
        // you would set it here
        m.setAmr(this.getArmorClass());
    }

}
