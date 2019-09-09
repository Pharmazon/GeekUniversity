package ru.shcheglov;

/**
 * Java 1. Homework 8. FifteenPuzzle game.
 * Class: GamePanel
 *
 * @author Alexey Shcheglov
 * @link https://github.com/Pharmazon
 * @version 0.1 dated Dec 20, 2017
 */

import javax.swing.*;

class GamePanel extends JPanel {
    
    public static void main(String[] args) {
    }
    
    GamePanel() {
        super();
    }
    
    //repaint field after changes
    void repaintField(Button[][] arr) {
        for (Button[] buttons : arr) {
            for (int j = 0; j < arr.length; j++) {
                if (buttons[j] != null) {
                    this.add(buttons[j]);
                }
            }
        }
        this.revalidate();
        this.repaint();
    }   
}