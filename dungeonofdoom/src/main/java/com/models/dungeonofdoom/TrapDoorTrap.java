package com.models.dungeonofdoom;

import com.models.Player;
import com.models.dungeonofdoom.enums.TrapTypeEnum;
public class TrapDoorTrap extends AbstractTrap {

    public TrapDoorTrap(boolean hidden) {
        super(hidden, TrapTypeEnum.TRAP_DOOR);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void applyEffect(Player player) {

    }
    
}
