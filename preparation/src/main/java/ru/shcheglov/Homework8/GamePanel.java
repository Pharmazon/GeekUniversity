package ru.shcheglov.Homework8;

/**
 * Java 1. Homework 8. FifteenPuzzleGame game.
 * Class: GamePanel
 *
 * @author Alexey Shcheglov
 * @link https://github.com/Pharmazon
 * @version 0.1 dated Dec 20, 2017
 */

import javax.swing.*;

public class GamePanel extends JPanel {

    public GamePanel() {
        super();
    }
    
    void repaintField(Button[][] arr) {
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr.length; j++) {
                if (arr[i][j] != null) {
                    this.add(arr[i][j]);
                }
            }
        }
        this.revalidate();
        this.repaint();
    }   
}