package com.player.uday;

public class ItemPlayground {
    private String name;
    private ItemTypePlayground type;

    public ItemPlayground(String name, ItemTypePlayground type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public ItemTypePlayground getType() {
        return type;
    }
}
