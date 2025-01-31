package com.playground.alex;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    public List<TrapInterfacePlayground> traps; // eventually use lombok or make getter for this.
    
    //Sample to make simple floors
    private static final char[] FLOOR_SYMBOLS = {
        '.', ',', '~', ':', '-', '=', '*', '+', 'o', '^', 'x', '%', '!', '?', '#', '$', '&', '@', 'Ω', '∆', '§', '¶', '¤', '‡', '†', '∑'
    };

    public DungeonFloor(int level, int width, int height) {
        this.level = level;
        this.width = width;
        this.height = height;
        this.map = new char[height][width];
        this.originalMap = new char[height][width];
        this.random = new Random();
        // Cycle symbols to be removed when procedural generation is setup
        this.floorSymbol = FLOOR_SYMBOLS[(level - 1) % FLOOR_SYMBOLS.length]; 
        this.traps = new ArrayList<>();
        generateDungeon();
        // Debugging
        // System.out.println("Floor " + level + " - Stairs Position: " + stairX + ", " + stairY);
    }

    //Sample to make simple floors
    private void generateDungeon() {
        // Fill map with different floor symbol
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                map[i][j] = floorSymbol;
                originalMap[i][j] = floorSymbol;
            }
        }

        // Place stairs down > (Always at the bottom as a sample)
        stairX = random.nextInt(width);
        stairY = height - 2;
        map[stairY][stairX] = '>';

        // Generate traps after placing stairs
        generateTraps();
    }
    private void generateTraps() {
        int numTraps = random.nextInt(4); // Randomly place 0-3 traps per floor

        for (int i = 0; i < numTraps; i++) {
            int x, y;

            // Find a valid position that is NOT the stair position
            do {
                x = random.nextInt(width);
                y = random.nextInt(height);
            } while ((x == stairX && y == stairY)); // Avoid placing traps on stairs

            TrapInterfacePlayground trap = getRandomTrap();
            // TrapInterfacePlayground trap = new TeleportTrapPlayground(false);
            trap.setPosition(x, y);
            traps.add(trap);
            map[y][x] = '!'; // Mark trap location (for debugging, can be hidden)
        }
    }

    private TrapInterfacePlayground getRandomTrap() {
        Random rand = new Random();
        int trapType = rand.nextInt(TrapTypeEnumPlayground.getTrapListCount());

        return switch (trapType) {
            case 0 -> new BearTrapPlayground(false);
            case 1 -> new TrapDoorPlayground(false);
            case 2 -> new TeleportTrapPlayground(false);
            default -> new BearTrapPlayground(false);
        };
    }

    public TrapInterfacePlayground getTrapAt(int x, int y) {
        for (TrapInterfacePlayground trap : traps) {
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
