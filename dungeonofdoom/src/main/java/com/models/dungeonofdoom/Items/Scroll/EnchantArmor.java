package com.models.dungeonofdoom.Items.Scroll;

import com.models.Player;
import com.models.dungeonofdoom.Items.ItemEffect;
import com.models.dungeonofdoom.Items.Armor.Armor;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.monster.Monster;

public class EnchantArmor implements ItemEffect{

    @Override
    public void applyToPlayer(Player player, DungeonFloor df) {
        Armor armor = player.getEquippedArmor();
        if (armor == null) {
            return;
        }

        if (armor.isCursed()) {
            armor.removeCurse();
        } else {
            armor.enchantArmor();
        }
    }

    @Override
    public void applyToMonster(Monster monster) {

    }

    @Override
    public String messageStringPlayer(Player player) {
        return "Your armor glows faintly for a moment.";
    }

    @Override
    public String messageStringMonster(Monster monster) {
        return "";
    }

}
