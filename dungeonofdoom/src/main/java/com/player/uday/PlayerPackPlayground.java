package com.player.uday;

import java.util.ArrayList;
import java.util.List;

public class PlayerPackPlayground {
    private static final int MAX_CAPACITY = 23;
    private List<ItemPlayground> items;

    public PlayerPackPlayground() {
        this.items = new ArrayList<>();
    }

    public boolean addItem(ItemPlayground item) {
        if (items.size() >= MAX_CAPACITY) {
            return false; // Pack is full
        }
        items.add(item);
        return true;
    }

    public boolean dropItem(ItemPlayground item) {
        return items.remove(item);
    }

    public void listItems() {
        for (ItemPlayground item : items) {
            System.out.println(item.getName());
        }
    }

    public boolean containsItem(String itemName) {
        return items.stream().anyMatch(item -> item.getName().equalsIgnoreCase(itemName));
    }

    public ItemPlayground getItem(String itemName) {
        return items.stream().filter(item -> item.getName().equalsIgnoreCase(itemName)).findFirst().orElse(null);
    }

    public int getSize() {
        return items.size();
    }

    public int getMaxCapacity() {
        return MAX_CAPACITY;
    }
}
