package com.example.managers;

import java.util.Random;
import com.models.Player;
import com.example.ui.JFrameUI;

public class HealingManager {
    private static final Random rand = new Random();
    private static int nonCombatTurnCounter = 0;
    private static int turnsUntilNextHeal = 0;

    public static void resetNonCombatCounter() {
        nonCombatTurnCounter = 0;
        // Roll 2d10 for next healing interval
        turnsUntilNextHeal = rand.nextInt(10) + 1 + rand.nextInt(10) + 1;
    }

    public static void processHealing(Player player, boolean isCombatTurn, JFrameUI frame) {
        // Only process non-combat healing if it's not a combat turn
        if (!isCombatTurn) {
            nonCombatTurnCounter++;
            
            if (nonCombatTurnCounter >= turnsUntilNextHeal) {
                if (player.getCurrentHealth() < player.getMaxHealth()) {
                    player.heal(1);
                    frame.updateMessage("You regain 1 health point.");
                }
                // Roll new interval for next heal (2d10)
                turnsUntilNextHeal = rand.nextInt(10) + 1 + rand.nextInt(10) + 1;
                nonCombatTurnCounter = 0;
            }
        }
    }
} 