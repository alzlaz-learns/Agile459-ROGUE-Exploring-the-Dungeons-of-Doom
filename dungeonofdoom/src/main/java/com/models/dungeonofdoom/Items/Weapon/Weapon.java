package com.models.dungeonofdoom.Items.Weapon;

import com.models.Player;
import com.models.dungeonofdoom.enums.WeaponEnum;
import com.models.dungeonofdoom.Items.Item;
import com.models.dungeonofdoom.monster.Monster;
import com.models.dungeonofdoom.Helper.Pair;
import java.util.Random;
public class Weapon extends Item {

    private WeaponEnum weaponType;
    private boolean isEnchanted;
    private boolean isCursed;
    private Random random;

    public Weapon(WeaponEnum weaponType, Random random) {
        this.weaponType = weaponType;
        this.isEnchanted = false;
        this.isCursed = false;
        this.random = random;
    }

    public WeaponEnum getWeaponType() {
        return weaponType;
    }

    public String getName() {
        return weaponType.getName();
    }

    public int getDamageWielded() {
        Pair<Integer, Integer> damage = weaponType.getDamageWielded();
        if (damage == null) return 0;
        return random.nextInt(damage.getB() - damage.getA() + 1) + damage.getA();
    }

    public int getDamageThrown() {
        Pair<Integer, Integer> damage = weaponType.getDamageThrown();
        if (damage == null) return 0;
        return random.nextInt(damage.getB() - damage.getA() + 1) + damage.getA();
    }

    public void enchantWeapon() {
        this.isEnchanted = true;
    }

    public boolean isEnchanted() {
        return isEnchanted;
    }

    public boolean isCursed() {
        return isCursed;
    }

    public void removeCurse() {
        this.isCursed = false;
    }

    public void curseWeapon() {
        this.isCursed = true;
        // Modify damage or other properties if needed
    }

    @Override
    public void message(Player p) {
        System.out.println("You wield the " + weaponType.getName() + ".");
    }

    @Override
    public void message(Monster m) {
        System.out.println("The " + m.getName() + " wields the " + weaponType.getName() + ".");
    }

    @Override
    public void effect(Player p) {
        // no effect 
    }

    @Override
    public void effect(Monster m) {
        // no effect 
    }

    @Override
    public String getItemName() {
        return this.weaponType.getName();
    }

    @Override
    public char getSymbol() {
        return this.weaponType.getSymbol();
    }
}
