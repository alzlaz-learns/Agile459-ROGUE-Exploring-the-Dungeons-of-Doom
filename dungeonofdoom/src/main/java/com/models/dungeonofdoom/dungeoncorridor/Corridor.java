package com.models.dungeonofdoom.dungeoncorridor;

import java.awt.Point;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Corridor {
    private final List<Point> path;
    private final char[][] map;
    private final char[][] originalMap;
    private static final char CORRIDOR = 'X';

    private boolean discovered = false;
    public static Corridor createCorridor(ArgsForBfsCorridorsDto bfsArgs) {
        // Create BFS args
        
        // Find path using BFS
        List<Point> path = BfsCorridors.findPathInBlankSpace(bfsArgs);
        
        return Corridor.builder()
                .path(path)
                .map(bfsArgs.getMap())
                .originalMap(bfsArgs.getOriginalMap())
                .build();
    }

    public void draw() {
        if (path == null) {
            System.out.println("No valid corridor path found");
            return;
        }

        for (int i = 0; i < path.size(); i++) {
            Point p = path.get(i);
            // Skip first and last points (doors)
            if (i != 0 && i != path.size() - 1) {
                    // map[p.y][p.x] = CORRIDOR;
                    map[p.y][p.x] = CORRIDOR;
                    originalMap[p.y][p.x] = CORRIDOR;
            }
        }
    }

    public boolean isDiscovered(){
        return discovered;
    }

    public void discover() {
        this.discovered = true;
        for( Point p: path){
            map[p.y][p.x] = originalMap[p.y][p.x];
        }
    }

    public List<Point> getPath() {
        return path;
    }
}