package com.example.managers;

import java.awt.Point;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import com.models.Player;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
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

    //loop through all monsters and if they are moving they move.
    
    public void monsterAction(DungeonFloor floor, Player player) {
        /*TODO: right now this is going to be basic movement once we get to combat 
        section this is going to be a method that checks if the player is in the target if they 
        are attack if not move
        */
        List<Monster> monsters = floor.getMonsters();

        for (Monster monster : monsters) {
            // if(!monster.isActive()){
            //     continue;
            // }
            moveMonster(monster, floor, player);
        }
    }

    //to be implemented but add logic for monster
    private void moveMonster(Monster monster, DungeonFloor floor, Player player) {
        int x = monster.getX();
        int y = monster.getY();
        int newX = x, newY = y;
    }

    class BFSMonsterPath{

        public static List<Point> findPath(Monster m, Player p, DungeonFloor df){
            Queue<Point> queue = new LinkedList<>();
            Set<Point> visited = new HashSet<>();
            
            return null;
        }

    }

}
