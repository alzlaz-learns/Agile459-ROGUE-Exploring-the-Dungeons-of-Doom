package com.models.dungeonofdoom.Items;

import java.util.ArrayList;
import java.util.List;

import com.models.Player;
import com.models.dungeonofdoom.Items.Potion.Potion;
import com.models.dungeonofdoom.Items.Ring.Ring;
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

    public void dropObject(int index) {
        if (index < 0 || index >= pack.size()) {
            System.out.println("Invalid index. No item was dropped.");
            return;
        }
        
        
        Item droppedItem = pack.remove(index);
        //TODO 
        System.out.println("Dropped: " );
        
    }

    public String equipItem(int index, Player p, DungeonFloor d){
        if (index < 0 || index >= pack.size()) {
            System.out.println("Invalid index. No item was dropped.");
            return "Nothing to use!";
        }

        Item i = pack.get(index);
        String res = "";
        if(i instanceof Ring){
            System.out.println("Instance of ring");
            res = p.equipRing(i);
            System.out.println(res);
        }

        return res;
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
            case ALL:
                return true;
            default:
                return false;
        }
    }

}
