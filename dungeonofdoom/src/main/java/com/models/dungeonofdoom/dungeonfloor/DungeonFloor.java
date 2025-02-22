package com.models.dungeonofdoom.dungeonfloor;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import com.example.managers.MonsterManager;
import com.models.Player;
import com.models.dungeonofdoom.dungeoncorridor.ArgsForBfsCorridorsDto;
import com.models.dungeonofdoom.Traps.AbstractTrap;
import com.models.dungeonofdoom.Traps.ArrowTrap;
import com.models.dungeonofdoom.Traps.BearTrap;
import com.models.dungeonofdoom.Traps.DartTrap;
import com.models.dungeonofdoom.Traps.SleepTrap;
import com.models.dungeonofdoom.Traps.TeleportTrap;
import com.models.dungeonofdoom.Traps.TrapDoorTrap;
import com.models.dungeonofdoom.dungeoncorridor.Corridor;
import com.models.dungeonofdoom.enums.MonsterEnum;
import com.models.dungeonofdoom.enums.TrapTypeEnum;
import com.models.dungeonofdoom.monster.Monster;

public class DungeonFloor {
    private final int width;
    private final int height;
    private final char[][] map;
    private final char[][] originalMap;
    private final int level;
    private final Random random;

    MonsterManager monsterManager; //THIS IS TESTING CODE
    public List<Monster> monsters;

    private int stairX;
    private int stairY;
   
    //testing abstract version of things
    public List<AbstractTrap> traps;

    // Room generation constants
    private static final char VERTICAL_WALL = '║';
    private static final char HORIZONTAL_WALL = '═';
    private static final char TOP_LEFT_CORNER = '╔';
    private static final char TOP_RIGHT_CORNER = '╗';
    private static final char BOTTOM_LEFT_CORNER = '╚';
    private static final char BOTTOM_RIGHT_CORNER = '╝';
    private static final char FLOOR = '.';
    private static final int MIN_ROOM_SIZE = 4;
    private static final int MAX_ROOM_SIZE = 18;
    private static final int MAX_ROOMS = 10;

    private List<Room> rooms;

    public DungeonFloor(int level, int width, int height, MonsterManager monsterManager) {
        this.level = level;
        this.width = width;
        this.height = height;
        this.map = new char[height][width];
        this.originalMap = new char[height][width];
        this.random = new Random();

        this.rooms = new ArrayList<>();
        this.traps = new ArrayList<>();

        this.monsterManager = monsterManager; //this is testing code.
        this.monsters = new ArrayList<>();

        generateDungeon();
    }

    public Monster getMonsterAt(int x, int y) {
        for (Monster monster : monsters) {
            if (monster.getX() == x && monster.getY() == y) {
                return monster;
            }
        }
        return null; 
    }

    public void removeMonster(Monster monster) {
        if(monsters.contains(monster)){
            monsters.remove(monster);
            // Get the original tile from originalMap instead of assuming it's a floor
            char originalTile = originalMap[monster.getY()][monster.getX()];
            map[monster.getY()][monster.getX()] = originalTile;
        }
    }

    public List<Point> getValidRoomTiles() {
        List<Point> validTiles = new ArrayList<>();
        
        for (Room room : rooms) {
            for (int y = room.y + 1; y < room.y + room.height - 1; y++) {
                for (int x = room.x + 1; x < room.x + room.width - 1; x++) {
                    // Ensure we are selecting only floor tiles
                    if (map[y][x] == FLOOR) {
                        validTiles.add(new Point(x, y));
                    }
                }
            }
        }
        return validTiles;
    }


    private void generateDungeon() {
        // Fill map with empty space instead of walls
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                map[i][j] = ' ';
                originalMap[i][j] = ' ';
            }
        }

        // Generate rooms
        generateRooms();
        // generateCorridors();
        generateCorridorsWithKruskal();
        //placeDoors();
        // Place stairs randomly in a room
        placeStairs();
        // Generate traps
        generateTraps();
        //Spawn Monsters
        spawnMonster();
    }

    private void generateRooms() {
        int attempts = 0;
        while (rooms.size() < MAX_ROOMS && attempts < 1000) {
            int width = MIN_ROOM_SIZE + random.nextInt(MAX_ROOM_SIZE - MIN_ROOM_SIZE + 1);
            int height = MIN_ROOM_SIZE + random.nextInt(MAX_ROOM_SIZE - MIN_ROOM_SIZE + 1);
            int x = random.nextInt(this.width - width - 1) + 1;
            int y = random.nextInt(this.height - height - 1) + 1;

            Room newRoom = new Room(x, y, width, height);
            boolean intersects = false;

            // Check if room intersects with existing rooms
            for (Room room : rooms) {
                if (newRoom.intersects(room)) {
                    intersects = true;
                    break;
                }
            }

            if (!intersects) {
                createRoom(newRoom);
                rooms.add(newRoom);
            }
            attempts++;
        }
    }

    private void createRoom(Room room) {
        // Draw horizontal walls
        for (int x = room.x; x < room.x + room.width; x++) {
            map[room.y][x] = HORIZONTAL_WALL;
            map[room.y + room.height - 1][x] = HORIZONTAL_WALL;
            originalMap[room.y][x] = HORIZONTAL_WALL;
            originalMap[room.y + room.height - 1][x] = HORIZONTAL_WALL;
        }

        // Draw vertical walls
        for (int y = room.y; y < room.y + room.height; y++) {
            map[y][room.x] = VERTICAL_WALL;
            map[y][room.x + room.width - 1] = VERTICAL_WALL;
            originalMap[y][room.x] = VERTICAL_WALL;
            originalMap[y][room.x + room.width - 1] = VERTICAL_WALL;
        }

        // Draw corners
        map[room.y][room.x] = TOP_LEFT_CORNER;
        map[room.y][room.x + room.width - 1] = TOP_RIGHT_CORNER;
        map[room.y + room.height - 1][room.x] = BOTTOM_LEFT_CORNER;
        map[room.y + room.height - 1][room.x + room.width - 1] = BOTTOM_RIGHT_CORNER;

        // Copy corners to original map
        originalMap[room.y][room.x] = TOP_LEFT_CORNER;
        originalMap[room.y][room.x + room.width - 1] = TOP_RIGHT_CORNER;
        originalMap[room.y + room.height - 1][room.x] = BOTTOM_LEFT_CORNER;
        originalMap[room.y + room.height - 1][room.x + room.width - 1] = BOTTOM_RIGHT_CORNER;

        // Fill floor
        for (int y = room.y + 1; y < room.y + room.height - 1; y++) {
            for (int x = room.x + 1; x < room.x + room.width - 1; x++) {
                map[y][x] = FLOOR;
                originalMap[y][x] = FLOOR;
            }
        }
    }

    private void placeStairs() {
        if (rooms.isEmpty()) return;

        // Get all valid room tiles
        List<Point> validTiles = getValidRoomTiles();
        if (validTiles.isEmpty()) return; // No valid tiles to place stairs

        // Randomly pick a tile from valid room tiles
        Point stairTile = validTiles.get(random.nextInt(validTiles.size()));

        // Set the stairs' position
        stairX = stairTile.x;
        stairY = stairTile.y;

        // Mark the stairs on the map
        map[stairY][stairX] = '>';
        System.out.println("Stairs placed at: (" + stairX + ", " + stairY + ")");
    }

    public void setStairs(int x, int y, boolean isDescending) {
        stairX = x;
        stairY = y;
        map[y][x] = isDescending ? '>' : '<'; // '>' for downward stairs, '<' for upward stairs

        System.out.println((isDescending ? "Downward" : "Upward") + " stairs placed at: (" + x + ", " + y + ")");
    }


    private void generateTraps() {
        List<Point> validTiles = getValidRoomTiles(); // Get valid room tiles
        Random rand = new Random();

        int numTraps = rand.nextInt(4); // 0-3 traps
        System.out.println("Number of traps to generate: " + numTraps);

        for (int i = 0; i < numTraps && !validTiles.isEmpty(); i++) {
            int index = rand.nextInt(validTiles.size());
            Point tile = validTiles.remove(index);

            // Skip tiles that are walls
            char currentTile = map[tile.y][tile.x];
            if (currentTile == '║' || currentTile == '═' || currentTile == '╔' ||
                currentTile == '╗' || currentTile == '╚' || currentTile == '╝') {
                System.out.println("Skipping wall tile: (" + tile.x + ", " + tile.y + ") - Symbol: " + currentTile);
                continue;
            }

            // Debug before placing
            System.out.println("Attempting to place trap at: (" + tile.x + ", " + tile.y + ") - Current Symbol: " + currentTile);

            // Place the trap
            AbstractTrap trap = getRandomTrap();
            trap.setPosition(tile.x, tile.y);
            traps.add(trap);

            // Update the map
            map[tile.y][tile.x] = '!';

            // Debug after placing
            System.out.println("Trap placed at: (" + tile.x + ", " + tile.y + ")");
        }

        // Print the final map for verification
        printMap();
    }


    public void printMap() {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                System.out.print(map[y][x] + " ");
            }
            System.out.println(); // Newline after each row
        }
        System.out.println(); // Extra newline for separation
    }




    private AbstractTrap getRandomTrap() {
        Random rand = new Random();
        int trapType = rand.nextInt(TrapTypeEnum.getTrapListCount());
    
        return switch (trapType) {
            case 0 -> new BearTrap(false, rand);
            case 1 -> new TrapDoorTrap(false);
            case 2 -> new TeleportTrap(false, this, rand);
            case 3 -> new SleepTrap(false, rand);
            case 4 -> new ArrowTrap(false, rand);

            case 5 -> new DartTrap(false, rand); //TODO: Make sure that the functionality of DARTTRAP IS WORKING
            default -> new BearTrap(false, rand); 
            // default -> new TeleportTrap(false, this, rand);
        };
    }
    

    public AbstractTrap getTrapAt(int x, int y) {
        for (AbstractTrap trap : traps) {
            if (trap.getX() == x && trap.getY() == y) {
                return trap;
            }
        }
        return null;
    }

    public char[][] getMap() {
        return map;
    }

    // Return the initial layout
    public char[][] getOriginalMap() {
        return originalMap; 
    }

    public int getLevel() {
        return level;
    }


    public int getStairX() {
        return stairX;
    }

    public int getStairY() {
        return stairY;
    }


    public void spawnMonster(){
        List<Point> validTiles = getValidRoomTiles();
        int monsterCount = random.nextInt(6);
        for(int i = 0; i < monsterCount; i++){
            int index = random.nextInt(validTiles.size());
            Point tile = validTiles.remove(index);

            // Skip tiles that are walls
            char currentTile = map[tile.y][tile.x];
            if (currentTile == '║' || currentTile == '═' || currentTile == '╔' ||
                currentTile == '╗' || currentTile == '╚' || currentTile == '╝') {
                System.out.println("Skipping wall tile: (" + tile.x + ", " + tile.y + ") - Symbol: " + currentTile);
                continue;
            }

            MonsterEnum randomMonster = MonsterEnum.values()[random.nextInt(MonsterEnum.values().length)];
            Monster monster = monsterManager.monsterFactory(randomMonster);
            monster.setPosition(tile.x, tile.y);

            monsters.add(monster);
            
            // Update the map
            map[tile.y][tile.x] = monster.getSymbol();

        }

    }

    public List<Monster> getMonsters() {
        return monsters;
    }


    public void placePlayer(Player player) {
        // Get all valid room tiles
        List<Point> validTiles = getValidRoomTiles();
        if (validTiles.isEmpty()) {
            System.err.println("No valid tiles available to place the player!");
            return;
        }

        // Randomly select a valid tile
        Point spawnTile = validTiles.get(random.nextInt(validTiles.size()));

        // Set the player's position
        player.moveTo(spawnTile.x, spawnTile.y);

        // Debug print for confirmation
        System.out.println("Player spawned at: (" + spawnTile.x + ", " + spawnTile.y + ")");
    }


    // Revised generateCorridors method that computes one door per room connection
    // private void generateCorridors() {
    //     if (rooms.isEmpty()) return;

    //     // Sort rooms for a natural connection order.
    //     rooms.sort((r1, r2) -> {
    //         if (r1.x == r2.x) return Integer.compare(r1.y, r2.y);
    //         return Integer.compare(r1.x, r2.x);
    //     });

    //     // Connect each adjacent room pair.
    //     for (int i = 0; i < rooms.size() - 1; i++) {
    //         Room roomA = rooms.get(i);
    //         Room roomB = rooms.get(i + 1);
    //         connectRooms(roomA, roomB);
    //     }

    //     // Optionally, connect any "lonely" rooms.
    //     connectLonelyRooms();
    // }


    // Replace or add a new corridor generation method using Kruskal's algorithm:
    private void generateCorridorsWithKruskal() {
        if (rooms.isEmpty()) return;

        // Build all possible edges between rooms with their weight (distance between room centers)
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < rooms.size(); i++) {
            for (int j = i + 1; j < rooms.size(); j++) {
                Room roomA = rooms.get(i);
                Room roomB = rooms.get(j);
                int centerAx = roomA.x + roomA.width / 2;
                int centerAy = roomA.y + roomA.height / 2;
                int centerBx = roomB.x + roomB.width / 2;
                int centerBy = roomB.y + roomB.height / 2;
                double distance = Math.sqrt(Math.pow(centerAx - centerBx, 2) + Math.pow(centerAy - centerBy, 2));
                edges.add(new Edge(i, j, distance));
            }
        }

        // Sort edges by distance (lowest weight first)
        Collections.sort(edges, Comparator.comparingDouble(e -> e.weight));

        // Initialize union-find (disjoint set) for the rooms
        UnionFind uf = new UnionFind(rooms.size());
        for (Edge edge : edges) {
            if (uf.find(edge.roomIndex1) != uf.find(edge.roomIndex2)) {
                uf.union(edge.roomIndex1, edge.roomIndex2);
                // Retrieve the rooms to connect
                Room roomA = rooms.get(edge.roomIndex1);
                Room roomB = rooms.get(edge.roomIndex2);
                // Use your existing connectRooms method to add a corridor between these rooms.
                connectRooms(roomA, roomB);
            }
        }
    }

    private static class Edge {
        int roomIndex1, roomIndex2;
        double weight;

        public Edge(int roomIndex1, int roomIndex2, double weight) {
            this.roomIndex1 = roomIndex1;
            this.roomIndex2 = roomIndex2;
            this.weight = weight;
        }
    }


    private static class UnionFind {
        int[] parent;

        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int i) {
            if (parent[i] != i) {
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

        public void union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if (rootI != rootJ) {
                parent[rootJ] = rootI;
            }
        }
    }




    // Revised connectLonelyRooms to also use door endpoints.
    // private void connectLonelyRooms() {
    //     Random rand = new Random();
    //     for (Room room : rooms) {
    //         boolean hasDoor = false;
    //         // Check if a door ('d') exists on or immediately outside the room perimeter.
    //         for (int y = room.y - 1; y <= room.y + room.height; y++) {
    //             for (int x = room.x - 1; x <= room.x + room.width; x++) {
    //                 if (x >= 0 && y >= 0 && x < width && y < height && map[y][x] == 'd') {
    //                     hasDoor = true;
    //                     break;
    //                 }
    //             }
    //             if (hasDoor) break;
    //         }
    //         if (!hasDoor) {
    //             // Pick a random room (other than the current one) and connect.
    //             Room target = rooms.get(rand.nextInt(rooms.size()));
    //             if (target != room) {
    //                 connectRooms(room, target);
    //             }
    //         }
    //     }
    // }

    private void connectRooms(Room roomA, Room roomB) {
        // Compute room centers
        int centerA_x = roomA.x + roomA.width / 2;
        int centerA_y = roomA.y + roomA.height / 2;
        int centerB_x = roomB.x + roomB.width / 2;
        int centerB_y = roomB.y + roomB.height / 2;
    
        int doorA_x, doorA_y, doorB_x, doorB_y;
        // Choose door positions based on the dominant axis of separation.
        if (Math.abs(centerB_x - centerA_x) > Math.abs(centerB_y - centerA_y)) {
            // Horizontal connection
            if (centerB_x > centerA_x) {
                doorA_x = roomA.x + roomA.width - 1;  // right wall of roomA
                doorA_y = centerA_y;
                doorB_x = roomB.x;                    // left wall of roomB
                doorB_y = centerB_y;
            } else {
                doorA_x = roomA.x;                    // left wall of roomA
                doorA_y = centerA_y;
                doorB_x = roomB.x + roomB.width - 1;    // right wall of roomB
                doorB_y = centerB_y;
            }
        } else {
            // Vertical connection
            if (centerB_y > centerA_y) {
                doorA_x = centerA_x;
                doorA_y = roomA.y + roomA.height - 1;   // bottom wall of roomA
                doorB_x = centerB_x;
                doorB_y = roomB.y;                      // top wall of roomB
            } else {
                doorA_x = centerA_x;
                doorA_y = roomA.y;                      // top wall of roomA
                doorB_x = centerB_x;
                doorB_y = roomB.y + roomB.height - 1;     // bottom wall of roomB
            }
        }
    
        // Place door markers at the computed positions.
        map[doorA_y][doorA_x] = 'd';
        originalMap[doorA_y][doorA_x] = 'd';
        map[doorB_y][doorB_x] = 'd';
        originalMap[doorB_y][doorB_x] = 'd';
    
        // Temporarily set the door cells to blank so the BFS corridor carving works.
        char origA = map[doorA_y][doorA_x];
        char origB = map[doorB_y][doorB_x];
        map[doorA_y][doorA_x] = ' ';
        map[doorB_y][doorB_x] = ' ';
    
        ArgsForBfsCorridorsDto bfsArgs = buildArgsForDfs(doorA_x, doorA_y, doorB_x, doorB_y);
        Corridor corridor = Corridor.createCorridor(bfsArgs);
        corridor.draw();
    
        // Restore the door markers
        map[doorA_y][doorA_x] = origA;
        map[doorB_y][doorB_x] = origB;
    }
    

    //method to check walkable spaces
    //code was getting redundant so made a method to clean stuff up
    //probably will need to do this eventually for alot of places.
    public boolean isWalkable(int x, int y) {
        char tile = map[y][x];
        if (tile == '║' || tile == '═' || tile == '╔' || tile == '╗' || tile == '╚' || tile == '╝' || tile == ' '){
            return false;
        }

        return true;
    }

    //checks where monsters are occupying was seperated from other logics so we can use it for attacking monster as player.
    public boolean monsterOccupies(int x, int y){
        for (Monster monster : monsters) {
            if (monster.getX() == x && monster.getY() == y) {
                return true; 
            }
        }
        return false;
    }


    private ArgsForBfsCorridorsDto buildArgsForDfs(int startDoorX, int startDoorY, int endDoorX, int endDoorY){

        ArgsForBfsCorridorsDto bfsArgs = new ArgsForBfsCorridorsDto();
        bfsArgs.setStartX(startDoorX);
        bfsArgs.setStartY(startDoorY);
        bfsArgs.setEndX(endDoorX);
        bfsArgs.setEndY(endDoorY);
        bfsArgs.setHeight(height);
        bfsArgs.setWidth(width);
        bfsArgs.setMap(map);
        bfsArgs.setOriginalMap(originalMap);

        return bfsArgs;
        
    }

    //used to check when a player enters a room
    public boolean isInsideRoom(int x, int y) {
        for (Room room : rooms) {
            if (x >= room.x && x < room.x + room.width &&
                y >= room.y && y < room.y + room.height) {
                return true;
            }
        }
        return false;
    }
    
    public Room getRoomAt(int x, int y) {
        for (Room room : rooms) {
            if (room.contains(x, y)) {
                return room; // Return the room the coordinates belong to
            }
        }
        return null; // Not inside a room
    }


}
