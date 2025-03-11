package com.example.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.example.managers.GameManager;
import com.models.Player;
import com.models.dungeonofdoom.Items.Item;
import com.models.dungeonofdoom.Traps.AbstractTrap;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.monster.Monster;



public class GamePanel extends JPanel {
    //screen settings
    final int tileSize = 16;
    private static final int SCREEN_WIDTH = 80;
    private static final int SCREEN_HEIGHT = 24;

    final int screenWidthScaled = tileSize * SCREEN_WIDTH; //16 * 80 = 1,280
    final int screenHeightScaled = tileSize * SCREEN_HEIGHT; //16 * 24 = 384

    private GameManager gameManager;
    private JLabel[][] gridLabels;

    public GamePanel(GameManager gameManager) {
        //set the gamePanel area
        this.setPreferredSize(new Dimension(screenWidthScaled, screenHeightScaled));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        
        this.gameManager = gameManager;

        DungeonFloor currentFloor = gameManager.getCurrentFloor();
        char[][] dungeon = currentFloor.getMap();

        this.setLayout(new GridLayout(dungeon.length, dungeon[0].length));
        gridLabels = new JLabel[dungeon.length][dungeon[0].length];

        for (int i = 0; i < dungeon.length; i++) {
            for (int j = 0; j < dungeon[i].length; j++) {
                JLabel cell = new JLabel(String.valueOf(dungeon[i][j]), SwingConstants.CENTER);
                cell.setForeground(Color.WHITE);
                cell.setBackground(Color.BLACK);
                cell.setOpaque(true);
                cell.setFont(new Font("Monospaced", Font.PLAIN, 12));
                this.add(cell);
                gridLabels[i][j] = cell;
            }
        }

        this.setFocusable(true);
        this.requestFocusInWindow();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //for trouble shooting weird weird focus problem.
                // System.out.println("Key pressed: " + KeyEvent.getKeyText(e.getKeyCode()));
                gameManager.handleMovement(e);
                updateDungeonView();
            }
        });

        

        updateDungeonView();
    }

    public void updateDungeonView() {
        DungeonFloor currentFloor = gameManager.getCurrentFloor();
        char[][] map = currentFloor.getMap();

        Player player = gameManager.getPlayer();

        // Original 
        char[][] originalMap = currentFloor.getOriginalMap();
        // for (int y = 0; y < map.length; y++) {
        //     for (int x = 0; x < map[y].length; x++) {
        //         map[y][x] = originalMap[y][x];
        //     }
        // }

        map[currentFloor.getStairY()][currentFloor.getStairX()] = '>';

        for (AbstractTrap trap : currentFloor.traps) {
            if (trap.isDiscovered() == true) {
                map[trap.getY()][trap.getX()] = '!';
            }
        }

        //spawning display items 
        for(Item item: currentFloor.getItems()){
            if(item.isDiscovered()){
                map[(int)item.getPosition().getY()][(int)item.getPosition().getX()] = item.getSymbol();
            }
            
        }

        //DRAWING MONSTERS ON THE MAP.
        for (Monster m: currentFloor.getMonsters()){
            if (m.isDiscovered() || player.isRevealed()) {
                map[m.getY()][m.getX()] = m.getSymbol();
            } else {
                map[m.getY()][m.getX()] = ' '; 
            }
        }

        // Only draw the player if they're alive
        if (!player.isDead()) {
            map[player.getY()][player.getX()] = player.getIcon();
        }

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                JLabel cell = gridLabels[y][x];
                char c = map[y][x];

                if (c == '>') {
                    cell.setBackground(Color.GREEN);
                    cell.setText("");
                } else if (c == '!') {
                    cell.setBackground(Color.RED);
                    cell.setText("");
                } else if (c == player.getIcon()) {
                    cell.setBackground(Color.BLACK);
                    cell.setForeground(Color.ORANGE);
                    cell.setText(String.valueOf(c));
                } else if (c == '░') {
                    cell.setBackground(Color.GREEN);
                } else if (c == ' ') { // Hide undiscovered areas
                    cell.setBackground(Color.BLACK);
                    cell.setForeground(Color.BLACK);
                    cell.setText(" ");
                } else {
                    cell.setBackground(Color.BLACK);
                    cell.setForeground(Color.WHITE);
                    cell.setText(String.valueOf(c));
                }
            }
        }

        revalidate();
        repaint();
    }
}
