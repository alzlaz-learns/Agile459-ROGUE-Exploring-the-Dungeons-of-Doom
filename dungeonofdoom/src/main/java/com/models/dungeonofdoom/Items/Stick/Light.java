package com.models.dungeonofdoom.Items.Stick;

import com.models.Player;
import com.models.dungeonofdoom.Items.ItemEffect;
import com.models.dungeonofdoom.monster.Monster;

public class Light implements ItemEffect {

    private boolean wasBlind;
    private boolean inCorridor;

    @Override
    public void applyToPlayer(Player player) {
        if (player.isBlind()) {
            player.clearBlind();
            if (wasBlind) {
                wasBlind = true;
            }
        }
        if (inCorridor) {
            // :( no effect
            return;
        } else {
            // will have to discuss this with alex, I am tired
        }
    }

    @Override
    public void applyToMonster(Monster monster) {
        // no effect
    }

    @Override
    public String messageStringPlayer(Player player) {
        if (wasBlind) {
            return "The corridor glows and then fades";
        }
        if (inCorridor) {
            return "You feel a warm glow all around you";
        } else {
            return "The room is lit by a shimmering blue light";
        }
    }

    @Override
    public String messageStringMonster(Monster monster) {
       // no effect
       return "";
    }

}
