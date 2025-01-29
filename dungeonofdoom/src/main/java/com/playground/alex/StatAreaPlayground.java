package com.playground.alex;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextArea;

public class StatAreaPlayground extends JTextArea{
    public StatAreaPlayground(){
        this.setEditable(false);
        this.setBackground(Color.GRAY);
        this.setForeground(Color.YELLOW);
        this.setFont(new Font("Monospaced", Font.PLAIN, 12));
    }

    //tentative representation of a stats.
    public void updateStats(String message) {
        this.setText(message);
    }
}
