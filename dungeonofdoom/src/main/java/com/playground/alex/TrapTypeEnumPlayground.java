package com.playground.alex;

public enum TrapTypeEnumPlayground {
    TRAP_DOOR("Trap Door", "You fell into a trap!", TrapEffectEnumPlayground.FALL),
    BEAR_TRAP("Bear Trap", "You are caught in a bear trap.", TrapEffectEnumPlayground.HOLD),
    TELEPORT_TRAP("Teleport Trap", "Test TELEPORT", TrapEffectEnumPlayground.TELEPORT),
    SLEEP_TRAP("Sleep Trap","A strange white mist envelops you and you fall asleep", TrapEffectEnumPlayground.HOLD),
    ARROW_TRAP("Arrow Trap", "IF the arrow hits the player and takes their health", TrapEffectEnumPlayground.ARROW),
    ;
    
    private final String name;
    private final String message;
    private final TrapEffectEnumPlayground effect;

    TrapTypeEnumPlayground(String name, String message, TrapEffectEnumPlayground effect) {
        this.name = name;
        this.message = message;
        this.effect = effect;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public TrapEffectEnumPlayground getEffect() {
        return effect;
    }

    public static int getTrapListCount(){
        return values().length;
    }

}
