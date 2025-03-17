package com.models.dungeonofdoom.Items;

import lombok.Data;
import java.awt.Point;
import com.models.Player;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.enums.ItemOptions;
import com.models.dungeonofdoom.monster.Monster;

@Data
public abstract class Item {
    private boolean identified;
    private String description; //potion (colors), staves & wands (materials), rings (stone)
    private Point position;
    private boolean discovered;
    private boolean singleUse;
    private boolean equipped;
    
    public Item(){
        this.identified = false;
        this.discovered = false;
        this.singleUse = false;
        this.equipped = false;
    }

    public abstract String message(Player p);
    public abstract void message(Monster m);

    public abstract void effect(Player p, DungeonFloor dungeonFloor);
    public abstract void effect(Monster m);

    public abstract ItemOptions getItemOption();
    
    public String performAction(Player p, DungeonFloor df) {
        // Default implementation - subclasses can override
        effect(p, df);
        switch (getItemOption()) {
            case WIELDABLE:
                equip();
                return p.equipWeapon(this);
            case WEARABLE:
                equip();
                return p.equipArmor(this);
            case PUTTABLE:
                equip();
                return p.equipRing(this);
            case QUAFFABLE:
            case READABLE:
                // I am ignoring identify scrolls for now
                effect(p, df);
                return message(p);
            case CONSUMABLE:
                effect(p, df);
                return message(p);
            case ALL:
            default:
                return "Nothing happens.";
        }
    }

    public void equip(){
        this.equipped = true;
    }

    public void unEquip(){
        this.equipped = false;
    }
    
    public boolean isEquipped(){
        return this.equipped;
    }

    public void discover(){
        this.discovered = true;
    }

    public boolean isSingleUse(){
        return true;
    }

    public void setSingleUse(){
        this.singleUse = true;
    }

    public void identify(){
        this.identified = true;
    }

    public boolean isItemIdentified(){
        return identified;
    }

    public abstract String getItemName();

    public abstract char getSymbol();

}
