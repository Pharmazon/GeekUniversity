package ru.shcheglov;

/**
 * Java 1. Homework 8. FifteenPuzzle game.
 * Class: Button
 *
 * @author Alexey Shcheglov
 * @link https://github.com/Pharmazon
 * @version 0.1 dated Dec 20, 2017
 */

import java.awt.*;
import javax.swing.*;

class Button extends JButton {
    
    public static void main(String[] args) {
    }
    
    public Button(String text, int fontsize) {
        super(text);
        this.setFont(new Font("Arial", Font.PLAIN, fontsize)); 
    }  
    
    public Button(String text) {
        super(text);
    }
    
    int[] getElementByXY(int x, int y) {
        int[] array = {5, 5};
        if (x == 1 && y == 0) {
            array[0] = 0;
            array[1] = 0;
        } else if (x == 124 && y == 0) {
            array[0] = 0;
            array[1] = 1;            
        } else if (x == 247 && y == 0) {
            array[0] = 0;
            array[1] = 2;            
        } else if (x == 370 && y == 0) {
            array[0] = 0;
            array[1] = 3;
        } else if (x == 1 && y == 124) {
            array[0] = 1;
            array[1] = 0;            
        } else if (x == 124 && y == 124) {
            array[0] = 1;
            array[1] = 1;            
        } else if (x == 247 && y == 124) {
            array[0] = 1;
            array[1] = 2;
        } else if (x == 370 && y == 124) {
            array[0] = 1;
            array[1] = 3;
        } else if (x == 1 && y == 248) {
            array[0] = 2;
            array[1] = 0;            
        } else if (x == 124 && y == 248) {
            array[0] = 2;
            array[1] = 1;            
        } else if (x == 247 && y == 248) {
            array[0] = 2;
            array[1] = 2;
        } else if (x == 370 && y == 248) {
            array[0] = 2;
            array[1] = 3;
        } else if (x == 1 && y == 372) {
            array[0] = 3;
            array[1] = 0;            
        } else if (x == 124 && y == 372) {
            array[0] = 3;
            array[1] = 1;            
        } else if (x == 247 && y == 372) {
            array[0] = 3;
            array[1] = 2;
        } else if (x == 370 && y == 372) {
            array[0] = 3;
            array[1] = 3;
        }
        return array;
    }
}