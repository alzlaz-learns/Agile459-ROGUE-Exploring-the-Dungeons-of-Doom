package com.player.uday;

public class PlayerPlayground {
    private String name;
    private int health;
    private int maxHealth;
    private int hungerCounter;
    private PlayerPackPlayground pack;
    private int gold;

    public PlayerPlayground(String name) {
        this.name = name;
        this.health = 100;
        this.maxHealth = 100;
        this.hungerCounter = 1300;
        this.pack = new PlayerPackPlayground();
        this.gold = 0;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health <= 0) {
            die();
        }
    }

    public void heal(int amount) {
        this.health = Math.min(this.maxHealth, this.health + amount);
    }

    public void eatFood() {
        if (pack.containsItem("Food")) {
            pack.dropItem(pack.getItem("Food"));
            hungerCounter = 1300;
            System.out.println("You eat some food. You feel refreshed.");
        } else {
            System.out.println("You have no food to eat.");
        }
    }

    public void updateHunger() {
        hungerCounter--;
        if (hungerCounter == 300) {
            System.out.println("You are starting to get hungry.");
        } else if (hungerCounter == 150) {
            System.out.println("You are starting to feel weak.");
        } else if (hungerCounter == 0) {
            System.out.println("You feel very weak. You faint.");
        } else if (hungerCounter == -850) {
            System.out.println("You have starved to death.");
            die();
        }
    }

    public void die() {
        System.out.println(name + " has died.");
        int goldLost = (int) (gold * 0.9);
        gold -= goldLost;
        System.out.println(goldLost + " gold added to the scoreboard.");
    }

    public void attack(MonsterPlayground monster) {
        int damage = 10;
        monster.takeDamage(damage);
        System.out.println("You attack the " + monster.getName() + " for " + damage + " damage.");
    }

    public PlayerPackPlayground getPack() {
        return pack;
    }

    public int getHealth() {
        return health;
    }
}
