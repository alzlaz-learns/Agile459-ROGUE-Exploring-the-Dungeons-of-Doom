package com.player.uday;

public class MonsterPlayground {
    private String name;
    private int health;

    public MonsterPlayground(String name, int health) {
        this.name = name;
        this.health = health;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health <= 0) {
            System.out.println(name + " has been defeated.");
        }
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }
}

