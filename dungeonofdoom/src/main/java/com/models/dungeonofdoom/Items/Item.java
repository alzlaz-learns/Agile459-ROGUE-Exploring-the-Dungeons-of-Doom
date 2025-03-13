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
    private boolean singleUse;
    
    public Item(){
        this.identified = false;
        this.discovered = false;
        this.singleUse = false;
    }

    public abstract String message(Player p);
    public abstract void message(Monster m);

    public abstract void effect(Player p, DungeonFloor dungeonFloor);
    public abstract void effect(Monster m);

    

    public void discover(){
        this.discovered = true;
    }

    public boolean isSingleUse(){
        return true;
    }

    public void setSingleUse(){
        this.singleUse = true;
    }

    public abstract String getItemName();

    public abstract char getSymbol();

}
