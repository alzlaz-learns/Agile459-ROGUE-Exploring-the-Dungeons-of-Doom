package com.example.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import com.models.Player;

import com.models.dungeonofdoom.Items.Pack;


public class InventoryScreen extends JPanel  {
    private JFrameUI frame;
    private Player player;
    private DefaultListModel<String> listModel;
    private JList<String> itemList;

    public InventoryScreen(JFrameUI frame, Player player) {
        this.frame = frame;
        this.player = player;
        setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFont(getFont());

        JLabel title = new JLabel("Inventory", SwingConstants.CENTER);
        title.setFont(new Font("Monospaced", Font.BOLD, 16));

        listModel = new DefaultListModel<>();
        itemList = new JList<>(listModel);
        
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
        
    }

    public void updateInventory() {
    listModel.clear(); // Clear the previous inventory list
    
    List<String> items = player.getPack().listInventory();
    
    if (items.isEmpty()) {
        listModel.addElement("empty");
    } else {
        for (String item : items){
            listModel.addElement(item); 
        }
    }
}
}
