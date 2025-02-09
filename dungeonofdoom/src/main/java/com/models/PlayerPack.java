package com.models;

import java.util.ArrayList;
import java.util.List;

public class PlayerPack {
    private static final int MAX_CAPACITY = 23;
    private List<Object> items = new ArrayList<>(); // to be made into an item class

    public boolean addItem(Object item) {
        if (items.size() >= MAX_CAPACITY) {
            return false; // Pack full
        }
        items.add(item);
        return true;
    }

    public boolean dropItem(Object item) {
        return items.remove(item);
    }

    public void listItems() {
        for (Object item : items) {
            //TODO: implement item stuff
            // System.out.println(item.getName() + " (" + item.getType() + ")");
        }
    }

    public boolean containsItem(String itemName) {
        //TODO: implement item stuff
        // return items.stream().anyMatch(item -> item.getName().equalsIgnoreCase(itemName));
        return false;
    }

    public Object getItem(String itemName) {
        //TODO: implement item stuff
        // return items.stream().filter(item -> item.getName().equalsIgnoreCase(itemName)).findFirst().orElse(null);
        return false;
    }
}

