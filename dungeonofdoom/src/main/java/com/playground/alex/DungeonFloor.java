package com.playground.alex;

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
