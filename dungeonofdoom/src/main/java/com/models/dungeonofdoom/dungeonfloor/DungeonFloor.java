package com.models.dungeonofdoom.dungeonfloor;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.managers.MonsterManager;
import com.models.Player;
import com.models.dungeonofdoom.Traps.AbstractTrap;
import com.models.dungeonofdoom.Traps.ArrowTrap;
import com.models.dungeonofdoom.Traps.BearTrap;
import com.models.dungeonofdoom.Traps.DartTrap;
import com.models.dungeonofdoom.Traps.SleepTrap;
import com.models.dungeonofdoom.Traps.TeleportTrap;
import com.models.dungeonofdoom.Traps.TrapDoorTrap;
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
    private static final char CORRIDOR = 'X';
    private static final char FLOOR = '.';
    private static final int MIN_ROOM_SIZE = 4;
    private static final int MAX_ROOM_SIZE = 18;
    private static final int MAX_ROOMS = 10;

    // Room class to store room information
    private class Room {
        int x, y, width, height;

        Room(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        boolean intersects(Room other) {
            return (x <= other.x + other.width && x + width >= other.x &&
                   y <= other.y + other.height && y + height >= other.y);
        }
    }

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
        generateCorridors();
        placeDoors();
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


    private void generateCorridors() {
        if (rooms.isEmpty()) return;

        // Sort rooms by X first, then Y for natural connection flow
        rooms.sort((r1, r2) -> {
            if (r1.x == r2.x) return Integer.compare(r1.y, r2.y);
            return Integer.compare(r1.x, r2.x);
        });

        for (int i = 0; i < rooms.size() - 1; i++) {
            Room roomA = rooms.get(i);
            Room roomB = rooms.get(i + 1);

            int startX = roomA.x + roomA.width / 2;
            int startY = roomA.y + roomA.height / 2;
            int endX = roomB.x + roomB.width / 2;
            int endY = roomB.y + roomB.height / 2;

            // Draw an L-shaped corridor
            drawCorridor(startX, startY, endX, endY);
        }

        // Ensure all rooms have at least one connection by connecting to random neighbors
        connectLonelyRooms();
    }


    private void drawCorridor(int x1, int y1, int x2, int y2) {
        //TODO: GOT THIS TENTATIVELY WORKING BUT JFRAME AINT LOVING THE Path ascii right
        int corridorChar = '░'; // Corridor character

        // Move horizontally first
        int minX = Math.min(x1, x2);
        int maxX = Math.max(x1, x2);
        for (int x = minX; x <= maxX; x++) {
            if (map[y1][x] == '║' || map[y1][x] == '═') { 
                map[y1][x] = 'd'; // Place a door if a corridor intersects a wall
            } else if (map[y1][x] == ' ') {
                map[y1][x] = CORRIDOR; // Draw the corridor
                originalMap[y1][x] = CORRIDOR;
                // originalMap[y1][x] = (char) corridorChar;
            }
        }

        // Move vertically
        int minY = Math.min(y1, y2);
        int maxY = Math.max(y1, y2);
        for (int y = minY; y <= maxY; y++) {
            if (map[y][x2] == '║' || map[y][x2] == '═') { 
                map[y][x2] = 'd'; // Place a door if a corridor intersects a wall
            } else if (map[y][x2] == ' ') {
                map[y][x2] = CORRIDOR; // Draw the corridor
                originalMap[y][x2] = CORRIDOR;
                // originalMap[y][x2] = (char) corridorChar;
            }
        }
    }

    private void connectLonelyRooms() {
        Random rand = new Random();

        for (Room room : rooms) {
            boolean hasCorridor = false;

            // Check if any corridor is adjacent
            for (int y = room.y - 1; y <= room.y + room.height; y++) {
                for (int x = room.x - 1; x <= room.x + room.width; x++) {
                    if (x >= 0 && y >= 0 && x < width && y < height && map[y][x] == (char) 176) {
                        hasCorridor = true;
                        break;
                    }
                }
                if (hasCorridor) break;
            }

            // If no corridor is found, connect to a random room
            if (!hasCorridor) {
                Room target = rooms.get(rand.nextInt(rooms.size()));
                drawCorridor(room.x + room.width / 2, room.y + room.height / 2,
                            target.x + target.width / 2, target.y + target.height / 2);
            }
        }
    }


    //DOORS
    private void placeDoors() {
        for (Room room : rooms) {
            // Check the perimeter of the room
            for (int x = room.x; x < room.x + room.width; x++) {
                // Top wall
                if (room.y > 0 && map[room.y - 1][x] == CORRIDOR) {
                    map[room.y][x] = 'd';
                    originalMap[room.y][x] = 'd';
                }
                // Bottom wall
                if (room.y + room.height < height && map[room.y + room.height][x] == CORRIDOR) {
                    map[room.y + room.height - 1][x] = 'd';
                    originalMap[room.y + room.height - 1][x] = 'd';
                }
            }
            for (int y = room.y; y < room.y + room.height; y++) {
                // Left wall
                if (room.x > 0 && map[y][room.x - 1] == CORRIDOR) {
                    map[y][room.x] = 'd';
                    originalMap[y][room.x] = 'd';
                }
                // Right wall
                if (room.x + room.width < width && map[y][room.x + room.width] == CORRIDOR) {
                    map[y][room.x + room.width - 1] = 'd';
                    originalMap[y][room.x + room.width - 1] = 'd';
                }
            }
        }
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
}
