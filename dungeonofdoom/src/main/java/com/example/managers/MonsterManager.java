package com.example.managers;

import java.awt.Point;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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


        //https://www.reddit.com/r/roguelikedev/comments/o8dy9l/calculate_distance_between_2_entities_on_a_tile/?rdt=54629
        for (Monster monster : monsters) {
            // if(!monster.isActive()){
            //     continue;
            // }
            if(Math.abs(monster.getX() - player.getX()) + Math.abs(monster.getY() - player.getY()) == 1) {
                System.out.println("Imma attack");
            } else{
                moveMonster(monster, floor, player);
            }
            
        }
    }

    //to be implemented but add logic for monster
    private void moveMonster(Monster monster, DungeonFloor floor, Player player) {
       
       List<Point> output = BFSMonsterPath.findPath(monster, player, floor);
    
        if (output == null || output.size() < 2) { 
            return; 
        }
       Point move = output.get(1);
       System.out.println(move);
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

    private static List<Point> reconstructPath(Map<Point, Point> cameFrom, Point target) {
        List<Point> path = new LinkedList<>();
        for (Point at = target; at != null; at = cameFrom.get(at)) {
            path.add(at);
        }
        Collections.reverse(path); 
        return path;
    }

}
