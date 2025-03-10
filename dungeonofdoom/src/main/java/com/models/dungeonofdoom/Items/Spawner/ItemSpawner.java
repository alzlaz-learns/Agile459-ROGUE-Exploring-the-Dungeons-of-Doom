package com.models.dungeonofdoom.Items.Spawner;

import java.util.ArrayList;
import java.util.List;
import java.awt.Point;
import java.util.Random;

import com.models.dungeonofdoom.Items.AmuletOfYendor;
import com.models.dungeonofdoom.Items.Item;
import com.models.dungeonofdoom.Items.Armor.Armor;
import com.models.dungeonofdoom.Items.Potion.Potion;
import com.models.dungeonofdoom.enums.ArmorEnum;
import com.models.dungeonofdoom.enums.PotionEnum;

public class ItemSpawner {
    private Random random;

    public ItemSpawner(){
        this.random = new Random();
    }


     /**
     * Spawns a random number of items on the dungeon floor
     * @param validTiles List of valid tiles where items can be placed
     * @param level Current dungeon level
     * @param minItems Minimum number of items to spawn
     * @param maxItems Maximum number of items to spawn
     * @return List of spawned items with their positions set
     */
    public List<Item> spawnItems(List<Point> validTiles, int level, int minItems, int maxItems){
        List<Item> spawnedItems = new ArrayList<>();
        List<Point> availableTiles = new ArrayList<>(validTiles);

        int itemCount = random.nextInt(maxItems - minItems + 1) + minItems;
        System.out.println("Spawning " + itemCount + " items on level " + level);

        for(int i = 0; i < itemCount && !availableTiles.isEmpty(); i++){

            int index = random.nextInt(availableTiles.size());
            Point tile = availableTiles.remove(index);

            Item item = createRandomItem(level);
            item.setPosition(tile);
            spawnedItems.add(item);
            // System.out.println("Item placed at: (" + tile.x + ", " + tile.y + ")");
        }

        return spawnedItems;
    }


    private Item createRandomItem(int dungeonLevel){

        if (dungeonLevel > 20 && random.nextInt(100) < 5){
            return new AmuletOfYendor();
        }

        int itemType = random.nextInt(2);

        return switch(itemType){
            case 0 -> createPotion();
            case 1 -> createArmor();
            default -> createPotion();
        };
    }

    private Potion createPotion(){

        PotionEnum[] potions = PotionEnum.values();
        PotionEnum potiontype = potions[random.nextInt(potions.length)];
        return new Potion(potiontype);
    }

    private Armor createArmor(){
        ArmorEnum[] armors = ArmorEnum.values();
        ArmorEnum armorType = armors[random.nextInt(armors.length)];
        Armor armor = new Armor(armorType);

        //chance to get cursed or enchanted armor 
        int enchantmentRoll = random.nextInt(100);
        if (enchantmentRoll < 10){
            armor.enchantArmor();
        } else if (enchantmentRoll >= 90){
            armor.curseArmor();
        }

        return new Armor(armorType);
    }
    
}
