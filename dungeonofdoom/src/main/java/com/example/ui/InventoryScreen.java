package com.example.ui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Optional;
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
    private List<Item> items;
    private ItemOptions currentFilter;
    private int selectedIndex = 0;
    private JLabel[] itemLabels;

    public InventoryScreen(JFrameUI frame) {
        this.frame = frame;
        
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        setDoubleBuffered(true);

        title = new JLabel("Inventory - Use arrow keys to navigate, Enter to select, Escape to go back", SwingConstants.CENTER);
        title.setFont(new Font("Monospaced", Font.BOLD, 16));
        title.setForeground(Color.WHITE);

        inventoryPanel = new JPanel(new GridLayout(12, 2, 10, 10)); // 12 rows, 2 columns
        inventoryPanel.setBackground(Color.BLACK);

        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }
        });

        add(title, BorderLayout.NORTH);
        add(inventoryPanel, BorderLayout.CENTER);
    }

    private void handleKeyPress(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_ESCAPE) {
            frame.showGameScreen();
            return;
        }

        if(items == null || items.isEmpty()) {
            frame.updateMessage("No items in inventory, not much to do here");
            return;
        }

        switch (keyCode) {
            case KeyEvent.VK_UP:
                // Move selection up
                if(selectedIndex > 0) {
                    selectedIndex--;
                }
                updateSelection();
                break;
            case KeyEvent.VK_DOWN:
                // Move selection down
                if(selectedIndex < items.size() - 1) {
                    selectedIndex++;
                }
                updateSelection();
                break;

            case KeyEvent.VK_D:
                frame.showGameScreen();
                frame.dropItemAtIndex(selectedIndex);
                break;

            case KeyEvent.VK_ENTER:
                // Select the current item
                frame.showGameScreen();
                frame.processInventorySelection(selectedIndex);
                break;
            }
        }

        private void updateSelection() {
            if (itemLabels == null || items == null || items.isEmpty()) return;
            
            for (int i = 0; i < itemLabels.length; i++) {
                if (itemLabels[i] != null) {  // Check each label exists
                    boolean isSelected = (i == selectedIndex && i < items.size());
                    
                    if (isSelected) {
                        itemLabels[i].setForeground(Color.BLACK);
                        itemLabels[i].setBackground(Color.YELLOW);
                    } else {
                        itemLabels[i].setForeground(Color.WHITE);
                        itemLabels[i].setBackground(Color.BLACK);
                    }
                }
            }
        }

    public void updateInventory(Player player, Optional<ItemOptions> option) {
        inventoryPanel.removeAll(); // Clear previous items
        this.currentFilter = option.orElse(ItemOptions.ALL);
        this.items = player.getPack().getItemsByType(currentFilter);
        this.selectedIndex = 0;

        int maxItems = 23;
        itemLabels = new JLabel[maxItems];
            
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
                    itemLabel.setForeground(i == 0 ? Color.BLACK : Color.WHITE);
                    itemLabel.setBackground(i == 0 ? Color.YELLOW : Color.BLACK);
                    itemLabel.setOpaque(true);
                    itemLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
                    inventoryPanel.add(itemLabel);
                    itemLabels[i] = itemLabel;
                } else {
                    inventoryPanel.add(new JLabel("")); // Fill empty grid slots for alignment
                }
            }
        }

        revalidate();
        repaint(); // Refresh the panel
    }
}