package com.models.dungeonofdoom.dungeonfloor;

// Room class to store room information
public class Room {
    int x, y, width, height;
    private boolean discovered = false;
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

    //added for room entry detection
    public boolean contains(int px, int py) {
        return px >= x && px < x + width && py >= y && py < y + height;
    }

    public boolean isDiscovered(){
        return discovered;
    }

    public void discover() {
        discovered = true;
    }
}
