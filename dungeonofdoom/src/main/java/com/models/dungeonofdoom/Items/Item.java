package com.models.dungeonofdoom.Items;

import com.models.Player;
import com.models.dungeonofdoom.monster.Monster;

public abstract class Item {
    private boolean identified;
    private String description; //potion (colors), staves & wands (materials), rings (stone)
    private int x;
    private int y;
    public Item(){
        this.identified = false;
    }

    public abstract void message(Player p);
    public abstract void message(Monster m);

    public abstract void effect(Player p);
    public abstract void effect(Monster m);

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
