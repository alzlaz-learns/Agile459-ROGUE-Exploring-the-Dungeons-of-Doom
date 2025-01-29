package com.playground.alex;

import com.models.Player;

public interface TrapInterfacePlayground {
    String trigger(Player player);
    boolean isHidden();
    void reveal();

    int getX();
    int getY();
    void setPosition(int x, int y);

    TrapEffectEnumPlayground getEffect();
}
