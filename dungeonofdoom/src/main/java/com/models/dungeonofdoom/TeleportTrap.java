package com.models.dungeonofdoom;

import com.models.Player;
import com.models.dungeonofdoom.enums.TrapTypeEnum;
public class TeleportTrap extends AbstractTrap{

    public TeleportTrap(boolean hidden) {
        super(hidden, TrapTypeEnum.TELEPORT_TRAP);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void applyEffect(Player player) {
        //effect is applied by game logic so this empty for nbow to be considered how to handle once a dungeons are properly generated.
    }
    
}
