package com.models.dungeonofdoom.Items.Potion;

import java.util.Random;

import com.models.Player;
import com.models.dungeonofdoom.monster.Monster;

public class Haste implements PotionEffect{
    private final Random random;

    public Haste(Random random) {
        this.random = random;
    }

    public Haste() {
        this.random = new Random();
    }

    @Override
    public void applyToPlayer(Player player) {
        if(player.isHasted()){
            //player faints for  1d8
            player.applyFaint(randomDuration(8));
            return;
        }

        player.applyHaste(randomDuration(4));
    }

    @Override
    public void applyToMonster(Monster monster) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyToMonster'");
    }

    @Override
    public String messageStringPlayer(Player player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'messageStringPlayer'");
    }

    @Override
    public String messageStringMonster(Monster monster) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'messageStringMonster'");
    }

    private int randomDuration(int i){
        return random.nextInt(i) + 1;
    }

}
