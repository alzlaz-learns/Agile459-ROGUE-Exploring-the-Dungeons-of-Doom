package com.example.ui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.List;
import java.awt.*;

import javax.swing.JLabel;

import javax.swing.JPanel;

import javax.swing.SwingConstants;

import com.models.Player;
import com.models.dungeonofdoom.Items.Item;

import com.models.dungeonofdoom.enums.ItemOptions;

public class InventoryScreen extends JPanel {
    private JFrameUI frame;
    

    private JPanel inventoryPanel; // Panel for items
    private JLabel title;
    

    public InventoryScreen(JFrameUI frame) {
        this.frame = frame;
        
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        setDoubleBuffered(true);

        title = new JLabel("Inventory", SwingConstants.CENTER);
        title.setFont(new Font("Monospaced", Font.BOLD, 16));
        title.setForeground(Color.WHITE);

        inventoryPanel = new JPanel(new GridLayout(12, 2, 10, 10)); // 12 rows, 2 columns
        inventoryPanel.setBackground(Color.BLACK);

        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) { // Press SPACE to exit inventory
                    frame.showGameScreen();
                }
            }
        });

        add(title, BorderLayout.NORTH);
        add(inventoryPanel, BorderLayout.CENTER);
    }


    

    public void updateInventory(Player player, ItemOptions filter) {
        inventoryPanel.removeAll(); // Clear previous items

        List<Item> items = player.getPack().getItemsByType(filter);
        
        int maxItems = 23;
       
            
        if (items.isEmpty()) {
            JLabel emptyLabel = new JLabel("empty", SwingConstants.CENTER);
            emptyLabel.setForeground(Color.WHITE);
            emptyLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
            inventoryPanel.add(emptyLabel);
        } else {
            for (int i = 0; i < maxItems; i++) {
                String selectionLetter = " " + ((char)('a' + i));
                if (i < items.size()) {
                    JLabel itemLabel;
                    if (items.get(i).isEquipped()) {
                        itemLabel = new JLabel(selectionLetter + ") " + items.get(i).getItemName() + " (E)", SwingConstants.CENTER);
                    } else {
                        itemLabel = new JLabel(selectionLetter + ") " + items.get(i).getItemName(), SwingConstants.CENTER);
                    }
                    itemLabel.setForeground(Color.WHITE);
                    itemLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
                    inventoryPanel.add(itemLabel);
                } else {
                    inventoryPanel.add(new JLabel("")); // Fill empty grid slots for alignment
                }
            }
        }

        revalidate();
        repaint(); // Refresh the panel
    }
            

}
