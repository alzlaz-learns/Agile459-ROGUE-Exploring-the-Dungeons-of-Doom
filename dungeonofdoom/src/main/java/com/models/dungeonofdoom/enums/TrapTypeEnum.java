package com.models.dungeonofdoom.enums;



public enum TrapTypeEnum {
    TRAP_DOOR("Trap Door", "You fell into a trap!", TrapEffectEnum.FALL),
    BEAR_TRAP("Bear Trap", "You are caught in a bear trap.", TrapEffectEnum.HOLD),
    TELEPORT_TRAP("Teleport Trap", "Test TELEPORT", TrapEffectEnum.TELEPORT),
    SLEEP_TRAP("Sleep Trap","A strange white mist envelops you and you fall asleep", TrapEffectEnum.HOLD),
    ARROW_TRAP("Arrow Trap", "IF the arrow hits the player and takes their health", TrapEffectEnum.ARROW),
    DART_TRAP("Dart trap","", TrapEffectEnum.DART );
    
    private final String name;
    private final String message;
    private final TrapEffectEnum effect;

    TrapTypeEnum(String name, String message, TrapEffectEnum effect) {
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

    public TrapEffectEnum getEffect() {
        return effect;
    }

    public static int getTrapListCount(){
        return values().length;
    }
}
