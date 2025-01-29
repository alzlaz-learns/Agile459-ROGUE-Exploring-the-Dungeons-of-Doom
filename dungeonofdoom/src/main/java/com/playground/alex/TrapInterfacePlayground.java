package com.playground.alex;

import com.models.Player;

/*
considering changing this to an abstract class once we get to an
actual implementation there is to much redundant code.
*/
public interface TrapInterfacePlayground {
    String trigger(Player player);
    boolean isHidden();
    void reveal();

    int getX();
    int getY();
    void setPosition(int x, int y);

    TrapEffectEnumPlayground getEffect();
    void applyEffect(Player player);
}
