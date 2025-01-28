package com.playground.alex;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;

public class GamePanelPlayground extends JPanel{
    //screen settings
    final int tileSize = 16;

    private static final int SCREEN_WIDTH = 80;
    private static final int SCREEN_HEIGHT = 24;

    final int screenWidthScaled = tileSize * SCREEN_WIDTH; //16 * 80 = 1,280
    final int screenHeightScaled = tileSize * SCREEN_HEIGHT; //16 * 24 = 384

    public GamePanelPlayground(){
        this.setPreferredSize(new Dimension(screenWidthScaled, screenHeightScaled));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }
    
    public void drawDungeon(char[][] dungeon) {
        this.removeAll(); 
        for (int i = 0; i < dungeon.length; i++) {
            for (int j = 0; j < dungeon[i].length; j++) {
                JLabel cell = new JLabel(String.valueOf(dungeon[i][j]));
                cell.setForeground(Color.WHITE);
                cell.setBackground(Color.BLACK);
                cell.setOpaque(true); 
                cell.setFont(new Font("Monospaced", Font.PLAIN, 12));
                cell.setHorizontalAlignment(SwingConstants.CENTER);
                this.add(cell); 
            }
        }
        this.revalidate(); 
        this.repaint();   
    }
}
