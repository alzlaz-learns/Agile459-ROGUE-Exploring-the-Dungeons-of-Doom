package com.models.dungeonofdoom.monster;

import java.util.Random;

import com.models.Player;
import com.models.dungeonofdoom.Helper.Pair;
import com.models.dungeonofdoom.enums.MonsterEnum;

//might make abstract eventually
public abstract class Monster {
    private MonsterEnum type;
    private int hpt;
    private int maxHpt;
    private int amr;
    protected Random rand;
    private int x;
    private int y;

    //this is going to represent if a monster is active and doing what it does
    //does not mean it will attack player all the time. 
    //Monsters become active when players enter the same room as them.
    private boolean active; 

    //this means that the monster will be attacking the player if active.
    //Not all monsters start aggressive but will become aggressive if attacked.
    private boolean aggressive;

    //we will want to parse the flags
    private boolean mean;
    private boolean flying;
    private boolean regenerating;
    private boolean greedy;
    private boolean invisible;

    //potion effects
    private int hasteTimer;
    private int blindTimer;
    private boolean discovered = false;
    private int level;
    private int confusedTimer = 0;

    //scroll effects
    private boolean hold = false;

    public Monster(MonsterEnum type, Random rand){
        this.rand = rand;
        this.type = type;
        this.maxHpt = roll(type.getHpt());
        this.hpt = maxHpt;
        this.amr = type.getAmr();


        String flags = type.getFlag();
        this.mean = flags.contains("M");
        this.flying = flags.contains("F");
        this.regenerating = flags.contains("R");
        this.greedy = flags.contains("G");
        this.invisible = flags.contains("I");
        
        this.hasteTimer= 0;
        this.blindTimer = 0;
        this.level = type.getLvl();
    }

    private int roll(Pair<Integer, Integer> pair){
        int left = pair.getA();
        int right = pair.getB();

        int total = 0;
        for(int i = 0; i < left; i++){
            total += rand.nextInt(right) + 1;
        }

        return total;
    }

    public int calculateDmg(){
        int dmg = 0;
        for(Pair<Integer, Integer> attack: type.getDmg()){
            dmg += roll(attack);
        }
        return dmg;
    }

    public void takeDmg(int dmg){
        this.hpt -= dmg;
        if (!isDead()) {
            this.aggressive = true; // Monster becomes aggressive when attacked
        }
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

    public int getMaxHpt(){
        return this.maxHpt;
    }

    public int getHpt() {
        return this.hpt;
    }

    public void setMaxHpt(int i){
        this.maxHpt = i;
    }

    public void setCurrentHpt(int i){
        this.hpt = i;
    }

    public void heal(int amount) {
        if (this.hpt < this.maxHpt) {
            this.hpt = Math.min(this.maxHpt, this.hpt + amount);
        }
    }

    public int getAmr() {
        return this.amr;
    }

    public void setAmr(int amr){
        this.amr = amr;
    }

    public int getExp() {
        return type.getExp();
    }

    public boolean isActive() {
        return active;
    }

    public void activate() {
        this.active = true;
    }

    public boolean isMean() {
        return mean;
    }

    public boolean isGreedy() {
        return greedy;
    }

    //not sure how to deal with this one yet.
    //too lazy to do it now -D, using for cancellation logic atm
    public boolean isInvisible() {
        return invisible;
    }

    public boolean isFlying(){
        return flying;
    }

    public boolean validFloor(int currentFloor){
        //TODO: Consider about the percentage of monster to spawn.
        // i was thinking about this some do 
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setInvisible(boolean invisible) {
        this.invisible = invisible;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getLevel(){
        return this.level;
    }

    public void increaseLvl(){
        this.level ++;
    }

    public abstract void specialAbility(Player player); 

    public void setAggressive(boolean aggressive) {
        this.aggressive = aggressive;
    }
    
    public boolean isAggressive() {
        return aggressive || mean; // Mean monsters are always aggressive
    }

    public boolean isConfused(){
        return this.confusedTimer > 0;
    }

    public void applyConfused(int duration){
        this.confusedTimer = duration;
    }

    public void decrementConfused(){
        if(isConfused()){
            this.confusedTimer --;
        }
    }
    public boolean isBlind(){
        return this.blindTimer > 0;
    }
    public void applyBlind(int turns){
        this.blindTimer = turns;
    }

    public void decrementBlind(){
        if (isBlind()){
            this.blindTimer--;
        }
    }

    public int getBlindTimer(){
        return this.blindTimer;
    }

    public boolean isHasted(){
        return this.hasteTimer > 0;
    }
    public void applyHaste(int turns){
        this.hasteTimer = turns;
    }

    public int getHasteTimer(){
        return this.hasteTimer;
    }

    public void decrementHaste(){
        if(isHasted()){
            this.hasteTimer--;
        }
    }

    public boolean isDiscovered(){
        return discovered;
    }

    public void discover() {
        discovered = true;
    }

    public void applyHold(){
        this.hold = true;
    }

    public void removeHold(){
        this.hold = false;
    }

    public boolean getHoldStatus(){
        return this.hold;
    }
}
