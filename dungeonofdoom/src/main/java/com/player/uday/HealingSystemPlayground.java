package com.player.uday;

import java.util.Random;

public class HealingSystemPlayground {
    private static final Random rand = new Random();
    private static int turnCounter = 0;

    public static void healPlayer(PlayerPlayground player) {
        turnCounter++;
        if (turnCounter % (rand.nextInt(10) + 2) == 0) {
            player.heal(1);
        }
        if (player.getPack().containsItem("Ring of Regeneration")) {
            player.heal(1);
        }
    }
}

