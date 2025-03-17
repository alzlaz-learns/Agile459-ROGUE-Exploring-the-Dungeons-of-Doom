package com.example.managers;

import com.models.Player;

import java.util.Random;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.ui.JFrameUI;
import com.models.dungeonofdoom.Helper.Pair;
import com.models.dungeonofdoom.Items.Stick.Lightning;
/**
 * Centralized manager for game turns and turn-based effects
 */
public class TurnManager {
    private static int globalTurnCounter = 0;
    private static int nonCombatTurnCounter = 0;
    private static int turnsUntilNextHeal = 0;
    private static final Random rand = new Random();
    private static Pair<Integer, Integer> healingInterval = new Pair<>(2, 10);
    private static List<Lightning> activeLightningEffects = new ArrayList<>();
    /**
     * Increments the global turn counter and updates all turn-dependent systems
     * @param isCombatTurn whether the current turn involves combat
     * @param player the player character
     * @param frame the UI frame
     */
    public static void incrementTurn(boolean isCombatTurn, Player player, JFrameUI frame) {
        globalTurnCounter++;

        // Call processTurn for each active lightning effect
        for (Lightning lightning : activeLightningEffects) {
            String result = lightning.processTurn(player);
            if (result != null) {
                frame.updateMessage(result);
            }
        }

        // Handle healing on non-combat turns
        if (!isCombatTurn) {
            processNonCombatTurn(player, frame);
        } else {
            resetNonCombatCounter();
        }
    }
    
    /**
     * Processes a non-combat turn, including potential healing
     * @param player the player character
     * @param frame the UI frame
     */
    public static void processNonCombatTurn(Player player, JFrameUI frame) {
        nonCombatTurnCounter++;
        
        if (nonCombatTurnCounter >= turnsUntilNextHeal) {
            if (player.getCurrentHealth() < player.getMaxHealth()) {
                player.heal(1);
                frame.updateMessage("You regain 1 health point.");
            }
            // Roll new interval for next heal (2d10)
            turnsUntilNextHeal = rollHealingInterval();
            nonCombatTurnCounter = 0;
        }
    }

    public static int rollHealingInterval() {
        // Roll healingInterval.getA() number of healingInterval.getB()-sided dice
        int total = 0;
        for (int i = 0; i < healingInterval.getA(); i++) {
            total += rand.nextInt(healingInterval.getB()) + 1;
        }
        return total;
    }
    
    /**
     * Resets the non-combat turn counter (called after combat)
     */
    public static void resetNonCombatCounter() {
        nonCombatTurnCounter = 0;
        turnsUntilNextHeal = rollHealingInterval();
    }
    
    /**
     * Gets the current global turn count
     * @return the current global turn count
     */
    public static int getTurnCounter() {
        return globalTurnCounter;
    }

    public static void addLightningEffect(Lightning lightning) {
        activeLightningEffects.add(lightning);
    }
} 