package com.models.dungeonofdoom.monster;

import java.util.Random;

import com.models.dungeonofdoom.Helper.Pair;
import com.models.dungeonofdoom.enums.MonsterEnum;

public class Monster {
    private MonsterEnum type;
    private int hpt;
    private int amr;

    public Monster(MonsterEnum type){
        this.type = type;
        this.hpt = roll(type.getHpt());
        this.amr = type.getAmr();
    }

    private int roll(Pair<Integer, Integer> pair){
        int left = pair.getA();
        int right = pair.getB();
        Random random = new Random();

        int total = 0;
        for(int i = 0; i < left; i++){
            total += random.nextInt(right) + 1;
        }

        return total;
    }

    private int calculateDmg(){
        int dmg = 0;
        for(Pair<Integer, Integer> attack: type.getDmg()){
            dmg += roll(attack);
        }
        return dmg;
    }

    public void takeDmg(int dmg){
        this.hpt -= dmg;
        //TODO: implement dying
    }

    public boolean isDead(){
        return this.hpt < 1;
    }

    public String getName() {
        return type.getName();
    }

    public char getSymbol() {
        return type.getSymbol();
    }

    public int getHealth() {
        return this.hpt;
    }

    public int getArmor() {
        return this.amr;
    }

    public int getExperience() {
        return type.getExp();
    }

    public boolean validFloor(int currentFloor){
        //TODO: Consider about the percentage of monster to spawn.
        Pair<Integer, Integer> floorPair = type.getFloorFound();
        int minFloor = floorPair.getA();
        int maxFloor = floorPair.getB();
    
        /*
         * pairs for floors that state 0,0 should represent that they dont have floors 
         * to spawn on so likely intended to show it can spawn on any floor or on event
         */
        if (minFloor == 0 && maxFloor == 0) {
            return true;
        }
        
        /*
         * pairs that have the first number being greater then 0 and the 
         * second equal 0 indicates that they only spawn on that floor
         */
        if (minFloor > 0 && maxFloor == 0) {
            return currentFloor == minFloor;
        }
    
        /*
         * pairs that have a number that range from 1- 26 such as Hobglobins 1,7 
         * represents those are the floors they can spawn on.
         */
        return currentFloor >= minFloor && currentFloor <= maxFloor;
    
    }

}
