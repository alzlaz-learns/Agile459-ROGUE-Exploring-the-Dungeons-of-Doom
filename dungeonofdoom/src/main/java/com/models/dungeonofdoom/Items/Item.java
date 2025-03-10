package com.models.dungeonofdoom.Items;

import lombok.Data;
import java.awt.Point;
import com.models.Player;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.monster.Monster;

@Data
public abstract class Item {
    private boolean identified;
    private String description; //potion (colors), staves & wands (materials), rings (stone)
    private Point position;
    private boolean discovered;
    
    public Item(){
        this.identified = false;
    }

    public abstract void message(Player p);
    public abstract void message(Monster m);

    public abstract void effect(Player p);
    public abstract void effect(Monster m);

    public void effect(Player p, DungeonFloor df){
        
    }

    public abstract String getItemName();

    public abstract char getSymbol();

}
