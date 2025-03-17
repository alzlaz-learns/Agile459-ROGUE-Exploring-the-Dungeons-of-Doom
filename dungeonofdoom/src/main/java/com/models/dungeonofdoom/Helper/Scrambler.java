package com.models.dungeonofdoom.Helper;

public class Scrambler {
    public static String scrambleString(String s){
        char[] characters = s.toCharArray();
        for(int i = 0; i < s.length(); i++){
            characters[i] = (char) ('a' + (int) (Math.random() * 26)); // Generates a random lowercase letter
        
        }
        return new String(characters);
    }
}
