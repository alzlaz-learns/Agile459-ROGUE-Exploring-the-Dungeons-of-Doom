package com.models.dungeonofdoom.Traps;

import com.models.Player;
import com.models.dungeonofdoom.enums.TrapTypeEnum;
public class TrapDoorTrap extends AbstractTrap {

    public TrapDoorTrap(boolean hidden) {
        super(hidden, TrapTypeEnum.TRAP_DOOR);
        //TODO Auto-generated constructor stub
    }

    @Override
    public String trigger(Player player) {
        this.reveal();
        return trapType.getMessage();
    }

}
