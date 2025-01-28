package com.playground.alex;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextArea;

public class StatAreaPlayground extends JTextArea{
    public StatAreaPlayground(){
        this.setEditable(false);
        this.setBackground(Color.GREEN);
        this.setForeground(Color.WHITE);
        this.setFont(new Font("Monospaced", Font.PLAIN, 12));
    }
}
