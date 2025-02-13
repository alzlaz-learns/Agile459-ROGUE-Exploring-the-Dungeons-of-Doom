package com.example.managers;

import java.util.Random;

import com.models.dungeonofdoom.enums.MonsterEnum;
import com.models.dungeonofdoom.monster.Monster;

public class MonsterManager {
    private Random rand;

    public MonsterManager(Random rand){
        this.rand = rand;
    }

    public Monster monsterFactory(MonsterEnum monsterEnum){
        Monster monsterObject = null;

        // a few for example
        //TODO:change eventually the only ones going to be listed are the ones with special abilities because they weill work differently.
        switch(monsterEnum){
            case AQUATOR:
                monsterObject = new Monster(monsterEnum, rand);
                break;
            case BAT:
                monsterObject = new Monster(monsterEnum, rand);
                break;
            
            case SNAKE:
                monsterObject = new Monster(monsterEnum, rand);
                break;

            default:
                monsterObject = new Monster(monsterEnum, rand);
        }
        
        return monsterObject;
    }

}
