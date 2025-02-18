package com.models.dungeonofdoom.dungeoncorridor;

import lombok.Data;

@Data
public class ArgsForBfsCorridorsDto {
        private int startX;
        private int startY;
        private int endX;
        private int endY;
        private int height;
        private int width;
        private char[][] map;
        private char[][] originalMap;
}
