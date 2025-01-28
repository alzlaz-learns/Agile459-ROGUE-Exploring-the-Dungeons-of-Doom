package com.playground.alex;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;

public class GamePanelPlayground extends JPanel{
    //screen settings
    final int tileSize = 16;

    private static final int SCREEN_WIDTH = 80;
    private static final int SCREEN_HEIGHT = 24;

    final int screenWidthScaled = tileSize * SCREEN_WIDTH; //16 * 80 = 3840
    final int screenHeightScaled = tileSize * SCREEN_HEIGHT; //16 * 24 = 1152

    public GamePanelPlayground(){
        this.setPreferredSize(new Dimension(screenWidthScaled, screenHeightScaled));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }
}
