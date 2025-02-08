package com.playground.alex;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.models.dungeonofdoom.Traps.AbstractTrap;
import com.models.dungeonofdoom.Traps.ArrowTrap;
import com.models.dungeonofdoom.Traps.BearTrap;
import com.models.dungeonofdoom.Traps.DartTrap;
import com.models.dungeonofdoom.Traps.SleepTrap;
import com.models.dungeonofdoom.Traps.TeleportTrap;
import com.models.dungeonofdoom.Traps.TrapDoorTrap;

public class DungeonFloor {
    private final int width;
    private final int height;
    private final char[][] map;
    private final char[][] originalMap;
    private final int level;
    private final char floorSymbol;
    private final Random random;

    private int stairX;
    private int stairY;
   
    //testing abstract version of things
    public List<AbstractTrap> traps;

    //Sample to make simple floors
    private static final char[] FLOOR_SYMBOLS = {
        '.', ',', '~', ':', '-', '=', '*', '+', 'o', '^', 'x', '%', '!', '?', '#', '$', '&', '@', 'Ω', '∆', '§', '¶', '¤', '‡', '†', '∑'
    };

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

    public DungeonFloor(int level, int width, int height) {
        this.level = level;
        this.width = width;
        this.height = height;
        this.map = new char[height][width];
        this.originalMap = new char[height][width];
        this.random = new Random();
        // Cycle symbols to be removed when procedural generation is setup
        this.floorSymbol = FLOOR_SYMBOLS[(level - 1) % FLOOR_SYMBOLS.length]; 
        this.rooms = new ArrayList<>();
        this.traps = new ArrayList<>();
        generateDungeon();
        // Debugging
        // System.out.println("Floor " + level + " - Stairs Position: " + stairX + ", " + stairY);
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

        // Place stairs randomly in a room
        placeStairs();

        // Generate traps
        generateTraps();
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
        int trapType = rand.nextInt(TrapTypeEnumPlayground.getTrapListCount());
    
        return switch (trapType) {
            case 0 -> new BearTrap(false, rand);
            case 1 -> new TrapDoorTrap(false);
            case 2 -> new TeleportTrap(false, map, rand);
            case 3 -> new SleepTrap(false, rand);
            case 4 -> new ArrowTrap(false, rand);
            case 5 -> new DartTrap(false, rand);
            default -> new BearTrap(false, rand);
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

    public char getFloorSymbol() {
        return floorSymbol;
    }

    public int getStairX() {
        return stairX;
    }

    public int getStairY() {
        return stairY;
    }
}
