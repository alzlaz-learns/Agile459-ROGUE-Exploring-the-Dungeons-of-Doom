package com.models.dungeonofdoom.enums;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.models.dungeonofdoom.enums.MonsterEnum.Pair;

public enum MonsterEnum {

    AQUATOR("Aquator", 'A', "M", 20, 5, 9, new Pair<>(5,8), new Pair<>(0,0), new Pair<>(1,2), new Pair<>(1,2), new Pair<>(1,4)),

;

    private final String name;
    private final char symbol;
    private final String flag;
    private final int carryPercent;
    private final int exp;
    private final int lvl;
    private final Pair<Integer, Integer> hpt;
    private final Pair<Integer, Integer> floorFound;
    private final List<Pair<Integer, Integer>> dmg;

    private MonsterEnum(String name, char symbol, String flag, int carryPercent, int exp, int lvl, Pair<Integer, Integer> hpt, Pair<Integer, Integer> floorFound,  List<Pair<Integer, Integer>> dmg){
        this.name = name;
        this.symbol = symbol;
        this.flag = flag;
        this.carryPercent = carryPercent;
        this.exp = exp;
        this.lvl = lvl;
        this.hpt = hpt;
        this.floorFound = floorFound;
        this.dmg = Arrays.asList(dmg);
    }

     static class Pair<A, B> {
        private final A a;
        private final B b;

        public Pair(A a, B b){
            this.a = a;
            this.b = b;
        }

        public A getA(){
            return a;
        }

        public B getB(){
            return b;
        }
    }
}
