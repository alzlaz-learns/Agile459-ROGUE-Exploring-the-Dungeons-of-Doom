package com.example.managers;

import java.awt.Point;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.ArrayList;


import com.models.Player;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.dungeonfloor.Room;
import com.models.dungeonofdoom.enums.MonsterEnum;
import com.models.dungeonofdoom.monster.Aquator;
import com.models.dungeonofdoom.monster.GeneralMonster;
import com.models.dungeonofdoom.monster.IceMonster;
import com.models.dungeonofdoom.monster.Leprechaun;
import com.models.dungeonofdoom.monster.Medusa;
import com.models.dungeonofdoom.monster.Monster;
import com.models.dungeonofdoom.monster.Nymph;
import com.models.dungeonofdoom.monster.Rattlesnake;
import com.models.dungeonofdoom.monster.Vampire;
import com.models.dungeonofdoom.monster.Wraith;
import com.models.dungeonofdoom.monster.Xeroc;
import com.example.ui.JFrameUI;

public class MonsterManager {
    private Random rand;
    private JFrameUI frame;

    public MonsterManager(Random rand, JFrameUI frame){
        this.rand = rand;
        this.frame = frame;
    }

    public Monster monsterFactory(MonsterEnum monsterEnum){
         

        // a few for example
        //TODO:change eventually the only ones going to be listed are the ones with special abilities because they weill work differently.
            
        switch (monsterEnum) {
            case AQUATOR:
                return new Aquator(rand);
            case VAMPIRE:
                return new Vampire(rand);
            case LEPRECHAUN:
                return new Leprechaun(rand);
            case MEDUSA:
                return new Medusa(rand);
            case NYMPH:
                return new Nymph(rand);
            case RATTLESNAKE:
                return new Rattlesnake(rand);
            case WRAITH:
                return new Wraith(rand);
            case XEROC:
                return new Xeroc(rand);
            case ICEMONSTER:
                return new IceMonster(rand);
            default:
                return new GeneralMonster(monsterEnum, rand);
                
        }
    }

    //loop through all monsters and if they are moving they move.
    public void monsterAction(DungeonFloor floor, Player player) {
        /*TODO: right now this is going to be basic movement once we get to combat 
        section this is going to be a method that checks if the player is in the target if they 
        are attack if not move
        */
    
        //https://www.quora.com/What-does-the-exception-in-thread-main-Java-util-concurrentmodificationexception-mean-in-Java
        //https://www.reddit.com/r/roguelikedev/comments/o8dy9l/calculate_distance_between_2_entities_on_a_tile/?rdt=54629
        //Iterator<Monster> monsterIterator = floor.getMonsters().iterator(); // Use an iterator

        // while (monsterIterator.hasNext()) {
        //     Monster monster = monsterIterator.next();
        List<Monster> monstersToRemove = new ArrayList<>();
        
        for (Monster monster : floor.getMonsters()) {
            if (monster.isDead()) {
                // monsterIterator.remove();
                monstersToRemove.add(monster);
                continue;
            }
            
            if (!monster.isActive()) {
                continue;
            }

            // Only chase and attack if monster is mean or has been provoked
            if (monster.isAggressive()) {
                // Check if monster is adjacent to player
                if (Math.abs(monster.getX() - player.getX()) + Math.abs(monster.getY() - player.getY()) == 1) {
                    CombatManager.combatOrdering(player, monster, floor, frame);
                } else {
                    moveMonster(monster, floor, player);
                }
            }
            // Non-aggressive monsters will stay in place
        }
        
        // Remove dead monsters after iteration
        floor.getMonsters().removeAll(monstersToRemove);
    }

    //to be implemented but add logic for monster
    private void moveMonster(Monster monster, DungeonFloor floor, Player player) {
       
       List<Point> output = BFSMonsterPath.findPath(monster, player, floor);
    
        if (output == null || output.size() < 2) { 
            return; 
        }
       Point move = output.get(1);
       monster.setPosition(move.x, move.y);
    }

    static class BFSMonsterPath{

        
        //https://www.tutorialspoint.com/breadth-first-traversal-bfs-on-a-2d-array-using-java
        public static List<Point> findPath(Monster m, Player p, DungeonFloor df){
            char[][] map = df.getMap();
            int rows = map.length;
            int cols = map[0].length;

            boolean[][] visited = new boolean[rows][cols];
            Map<Point, Point> cameFrom = new HashMap<>();
            
            
            // Define the directions for exploring neighbors (up, down, left, right)
            int[] dx = {1, -1, 0, 0};
            int[] dy = {0, 0, 1, -1};

            Queue<Point> queue = new LinkedList<>();

            Point start = new Point(m.getX(), m.getY()); //monster starting position
            Point target = new Point(p.getX(), p.getY()); //player starting position

            queue.add(start);
            visited[start.y][start.x] = true;
            cameFrom.put(start, null);

            while (!queue.isEmpty()){
                Point current = queue.poll();
                if (current.equals(target)) {
                    List<Point> test = reconstructPath(cameFrom, target);
                    // System.out.println(test);
                    return test;
                }

                int i = current.x;
                int j = current.y;


                for (int k = 0; k < 4; k++) { // Four directions: up, down, left, right
                    int ni = i + dx[k];
                    int nj = j + dy[k];
                    
                 // Check if the new cell (ni, nj) is within the grid boundaries check if is walkable check if there is no monster.
                    if (ni >= 0 && ni < cols && nj >= 0 && nj < rows && !visited[nj][ni] && df.isWalkable(ni, nj) &&  !df.monsterOccupies(ni, nj)) { 
                        List<Monster> monsters = df.getMonsters();
                        // check if a monster already occupies the space might mess with pathing for the monsters if the monster is considered an obstacle to others
                        for (Monster monster : monsters) {
                            if (!(monster.getX() == ni && monster.getY() == nj)) {
                                Point nextPoint = new Point(ni, nj);
                                queue.add(nextPoint);
                                visited[nj][ni] = true;
                                cameFrom.put(nextPoint, current);
                            }
                        }
                        
                    }
                }
            }
            return null;
        }        

    }

    public void activateRoomMonsters(DungeonFloor floor, int playerX, int playerY) {
        // Find which room the player is in
        Room playerRoom = floor.getRoomAt(playerX, playerY);
        
        if (playerRoom == null) {
            return; // Not inside a room
        }
    
        // Only activate monsters that are inside the same room as the player
        for (Monster monster : floor.getMonsters()) {
            if (!monster.isActive() && playerRoom.contains(monster.getX(), monster.getY())) {
                monster.activate();
                frame.updateMessage("The " + monster.getName() + " wakes up!");
            }
        }
    }

    private static List<Point> reconstructPath(Map<Point, Point> cameFrom, Point target) {
        List<Point> path = new LinkedList<>();
        for (Point at = target; at != null; at = cameFrom.get(at)) {
            path.add(at);
        }
        Collections.reverse(path); 
        return path;
    }

}
