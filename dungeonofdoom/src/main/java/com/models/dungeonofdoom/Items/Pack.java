package com.models.dungeonofdoom.Items;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.models.Player;
import com.models.dungeonofdoom.Helper.Pair;
import com.models.dungeonofdoom.Items.Armor.Armor;
import com.models.dungeonofdoom.Items.Potion.Potion;
import com.models.dungeonofdoom.Items.Ring.Ring;
import com.models.dungeonofdoom.Items.Scroll.Scroll;
import com.models.dungeonofdoom.Items.Weapon.Weapon;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.enums.ItemOptions;

public class Pack {
    List<Item> pack;

    public Pack(){
        this.pack = new ArrayList<>();
    }

    public void addItem(Item i){
        
        if (pack.size() < 23) pack.add(i); // if there are 23 items
        else System.out.println("No room for anymore items");
    }

    public List<Item> listInventory(){

        List<Item> items = new ArrayList<>();
    
        // For example, label them with indices so the user can pick one by number
        for (int i = 0; i < pack.size(); i++) {
            Item item = pack.get(i);
            // The display name might come from item.getDisplayName() or item.toString() 
            items.add(item);
            // System.out.print(item.getItemName());
        }
        
        return items;
    }

    public boolean isEmpty(){
        return pack.size() == 0;
    }

    public Item selectiveInventory(int index) {
        if (index < 0 || index >= pack.size()) {
            System.out.println("Invalid index. No item selected.");
            return null;
        }
        return pack.get(index);
    }

    public void dropObject(int index,  Player p, DungeonFloor d) {
        if (index < 0 || index >= pack.size()) {
            System.out.println("Invalid index. No item was dropped.");
            return;
        }
        
        
        Item droppedItem = pack.remove(index);
        int xPos = (int) p.getPosition().getX();
        int yPos = (int) p.getPosition().getY();
        Point tile = new Point(xPos,yPos);
        droppedItem.setPosition(tile);
        List<Item> dungeonItems = d.getItems();
        dungeonItems.add(droppedItem);
        
        
    }
   
    public String equipItem(int index, Player p, DungeonFloor d){
        
        if (index < 0 || index >= pack.size()) {
            System.out.println("Invalid index. No item was dropped.");
            return "Nothing to use!";
        }
        
        Item i = pack.get(index);
        String res = "";
        if(i instanceof Ring){
            p.equipRing(i);
            res = i.message(p);
        }

        if(i instanceof Weapon){
            
            
            res = p.equipWeapon(i);
        }

        if(i instanceof Armor){
            res = p.equipArmor(i);
        }

        return res;
    }

    public Pair<Item, String> readItem(int index, Player p){
        if (index < 0 || index >= pack.size()) {
            System.out.println("Invalid index. No item was read.");
            return new Pair<>(null, "Nothing to use!");
        }
        Item i = pack.get(index);
        if( !(i instanceof Scroll)) return new Pair<>(null, "Not a viable selection!");
        
        if(i.isSingleUse() ){
            pack.remove(index);
        }

        return new Pair<>(i, i.message(p));
    }

    public String identifyItem(int index, Player p){
        if (index < 0 || index >= pack.size()) {
            System.out.println("Invalid index. No item was dropped.");
            return "Nothing to use!";
        }

        Item i = pack.get(index);
        System.out.println(i.getItemName());
        i.identify();
        System.out.println(i.isIdentified());
        p.addToIdentified(i);

        return "You identified " + i.getItemName();
        
    }

    public String useItem(int index, Player p, DungeonFloor d){
        
        if (index < 0 || index >= pack.size()) {
            System.out.println("Invalid index. No item was dropped.");
            return "Nothing to use!";
        }
        
        Item i = pack.get(index);
        if(i.isSingleUse()){
            pack.remove(index);
        }

        i.effect(p, d);
        
        return i.message(p);
        
    }

    public List<Item>  getItemsByType(ItemOptions option){
        List<Item> filteredItems = new ArrayList<>();
        for(Item i: pack){
            if(matchesFilter(i, option)){
                filteredItems.add(i);
            }
        }

        return filteredItems;
    }

    private boolean matchesFilter(Item i, ItemOptions filter) {
        switch (filter) {
            case QUAFFABLE:
                return i instanceof Potion;
            case PUTTABLE:
                return i instanceof Ring;
            case WIELDABLE:
                return i instanceof Weapon;
            case WEARABLE:
                return i instanceof Armor;
            case READABLE:
                return i instanceof Scroll;
            case IDENTIFIABLE: //waterfalling for now
            case ALL:
                return true;
            default:
                return false;
        }
    }

}
