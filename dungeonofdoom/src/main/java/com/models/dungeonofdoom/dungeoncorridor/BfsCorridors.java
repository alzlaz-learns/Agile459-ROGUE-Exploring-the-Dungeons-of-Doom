package com.models.dungeonofdoom.dungeoncorridor;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

public class BfsCorridors {




    public static List<Point> findPathInBlankSpace(ArgsForBfsCorridorsDto args) {
        // We’ll allow BFS to treat the start and end cells specially
        // (even if they're not blank), but it will only move through cells
        // that are strictly ' ' (space).

        //unpack
        int startX = args.getStartX();
        int startY = args.getStartY();
        int endX = args.getEndX();
        int endY = args.getEndY();
        int width = args.getWidth();
        int height = args.getHeight();
        char[][] map = args.getMap();
    
        // If the start or end are out of bounds, bail out.
        if (!inBounds(startX, startY, width, height) || !inBounds(endX, endY, width, height)) {
            return null;
        }
    
        boolean[][] visited = new boolean[height][width];
        Point[][] parent = new Point[height][width];
    
        // Mark the start as visited, even if it's not ' '.
        visited[startY][startX] = true;
    
        // Standard BFS queue
        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(new Point(startX, startY));
    
        // 4 possible directions (N, S, E, W)
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
    
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            int cx = current.x;
            int cy = current.y;
    
            // If we've reached the end cell, reconstruct the path
            if (cx == endX && cy == endY) {
                return reconstructPath(parent, current);
            }
    
            // Explore neighbors
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
    
                // Check bounds
                if (!inBounds(nx, ny, width, height)) continue;


                if (!visited[ny][nx]) {
                    char cell = map[ny][nx];
                    
                    // If it's the end cell or one of the passable cell types, allow BFS
                    if ((nx == endX && ny == endY) 
                         || cell == ' ' 
                         || cell == 'X' 
                         || cell == 'd') {
                        visited[ny][nx] = true;
                        parent[ny][nx] = current;
                        queue.offer(new Point(nx, ny));
                    }
                }
            }
        }
    
        // No path found
        return null;
    }
    
    // Reconstructs the path from end -> start using the parent[][] array
    // and returns it in start->end order.
    public static List<Point> reconstructPath(Point[][] parent, Point end) {
        Deque<Point> stack = new ArrayDeque<>();
        Point current = end;
        while (current != null) {
            stack.push(current);
            current = parent[current.y][current.x];
        }
        return new ArrayList<>(stack);  // now in start->end order
    }
    
    // Utility to check array bounds
    public static boolean inBounds(int x, int y, int width, int height) {
        return (x >= 0 && x < width && y >= 0 && y < height);
    }
    
}
