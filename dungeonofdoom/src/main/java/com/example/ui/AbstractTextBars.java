package com.example.ui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextArea;

public class AbstractTextBars extends JTextArea{
    public AbstractTextBars(Color bgColor, Color fgColor) {
        this.setEditable(false);
        this.setFocusable(false);
        this.setBackground(bgColor);
        this.setForeground(fgColor);
        this.setFont(new Font("Monospaced", Font.PLAIN, 12));
    }

    public void updateText(String message) {
        this.setText(message);
    }
}
