package com.models.dungeonofdoom.Items.Stick;

import java.util.Random;

import com.models.Player;
import com.models.dungeonofdoom.Items.ItemEffect;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.monster.Monster;
import com.models.dungeonofdoom.enums.MonsterEnum;
import com.models.dungeonofdoom.monster.GeneralMonster;

public class Polymorph implements ItemEffect {

    private final Random random;

    public Polymorph(Random random) {
        this.random = random;
    }

    @Override
    public void applyToPlayer(Player player, DungeonFloor df) {
        // Nothing to do here
    }

    @Override
    public void applyToMonster(Monster monster) {
        // Get a random monster type from the MonsterEnum values
        MonsterEnum[] monsterTypes = MonsterEnum.values();
        MonsterEnum newType = monsterTypes[random.nextInt(monsterTypes.length)];
        
        // Create a new monster of the random type to copy properties from
        Monster polymorphedMonster = new GeneralMonster(newType, random);
        
        // Copy the properties from the new monster to the existing one
        monster.setCurrentHpt(polymorphedMonster.getHpt());
        monster.setMaxHpt(polymorphedMonster.getMaxHpt());
        monster.setAmr(polymorphedMonster.getAmr());
        
        // Update the message strings to reflect the transformation
    }

    @Override
    public String messageStringPlayer(Player player) {
        return "You wave the polymorph stick!";
    }

    @Override
    public String messageStringMonster(Monster monster) {
        return "The monster transforms into a " + monster.getName() + "!";
    }
}
