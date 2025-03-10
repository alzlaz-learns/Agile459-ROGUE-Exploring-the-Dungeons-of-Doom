package com.models.dungeonofdoom.Items;

import java.util.ArrayList;
import java.util.List;

public class Pack {
    List<Item> pack;

    public Pack(){
        this.pack = new ArrayList<>();
    }

    public void addItem(Item i){
        if (pack.size() < 22) pack.add(i); // if there are 23 items
        else System.out.println("No room for anymore items");
    }

    public List<String> listInventory(){

        List<String> itemDescriptions = new ArrayList<>();
    
        // For example, label them with indices so the user can pick one by number
        for (int i = 0; i < pack.size(); i++) {
            Item item = pack.get(i);
            // The display name might come from item.getDisplayName() or item.toString() 
            
            System.out.print(item.getItemName());
        }
        
        return itemDescriptions;
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

}
