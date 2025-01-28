package com.playground.alex;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextArea;

public class MessageAreaPlayground extends JTextArea{
    
    public MessageAreaPlayground(){
        
        this.setEditable(false);
        this.setBackground(Color.GREEN);
        this.setForeground(Color.WHITE);
        this.setFont(new Font("Monospaced", Font.PLAIN, 12));
    }

    //tentative representation of a message.
    public void updateMessage(String message) {
        this.setText(message);
    }
}
