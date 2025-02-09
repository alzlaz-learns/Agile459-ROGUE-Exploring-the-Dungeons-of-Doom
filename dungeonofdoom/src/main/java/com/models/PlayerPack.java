package com.models;

public class PlayerPack {
    private static final int MAX_CAPACITY = 23;
    private List<Item> items = new ArrayList<>();

    public boolean addItem(Item item) {
        if (items.size() >= MAX_CAPACITY) {
            return false; // Pack full
        }
        items.add(item);
        return true;
    }

    public boolean dropItem(Item item) {
        return items.remove(item);
    }

    public void listItems() {
        for (Item item : items) {
            System.out.println(item.getName() + " (" + item.getType() + ")");
        }
    }

    public boolean containsItem(String itemName) {
        return items.stream().anyMatch(item -> item.getName().equalsIgnoreCase(itemName));
    }

    public Item getItem(String itemName) {
        return items.stream().filter(item -> item.getName().equalsIgnoreCase(itemName)).findFirst().orElse(null);
    }
}

