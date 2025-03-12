package com.models.dungeonofdoom.Items.Stick;

import com.models.Player;
import com.models.dungeonofdoom.Items.ItemEffect;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.monster.Monster;

public class TeleportAway implements ItemEffect {

    private DungeonFloor dungeonFloor;

    public TeleportAway(DungeonFloor dungeonFloor) {
        this.dungeonFloor = dungeonFloor;
    }

    @Override
    public void applyToPlayer(Player player) {
    }

    @Override
    public void applyToMonster(Monster monster) {
        dungeonFloor.teleportMonster(monster);
    }

    @Override
    public String messageStringPlayer(Player player) {
        return "";
    }

    @Override
    public String messageStringMonster(Monster monster) {
        return "";

    }
}
