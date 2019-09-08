package ru.shcheglov;

/**
 * Java 1. Homework 8. FifteenPuzzle game.
 * Class: MainClass FifteenPuzzle
 *
 * @author Alexey Shcheglov
 * @link https://github.com/Pharmazon
 * @version 0.1 dated Dec 20, 2017
 */

import java.awt.*;
import javax.swing.*;
import java.awt.Button;
import java.awt.event.*;
import java.util.Random;
import java.lang.Integer;

class FifteenPuzzle extends JFrame implements ActionListener {
    
    final String WINDOW_TITLE = "Fifteen Puzzle Game by Alexey Shcheglov";
    final int WINDOW_WIDTH = 500;
    final int WINDOW_HEIGHT = 550;
    final String BTN_NEWGAME = "New game";
    final String BTN_EXIT = "Exit";   
    java.awt.Button[][] arr = new java.awt.Button[4][4];
    
    GamePanel gamepanel = new GamePanel();
    JPanel panelbtn = new JPanel();
    java.awt.Button mem = new java.awt.Button("");
    
    public static void main(String[] args) {
        new FifteenPuzzle();
    }
    
    public FifteenPuzzle() {
        //initialize the main window
        this.setTitle(WINDOW_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        //Button panel
        panelbtn.setLayout(new GridLayout());
        this.add(panelbtn, BorderLayout.NORTH);
        
        //Game panel
        gamepanel.setLayout(new GridLayout(4, 4));
        this.add(gamepanel, BorderLayout.CENTER);

        //initial fill the field
        initGameField();

        //Shuffling gamefield
        shuffle();

        //Button "New game"
        java.awt.Button btnnewgame = new java.awt.Button(BTN_NEWGAME);
        btnnewgame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int resnew = JOptionPane.showConfirmDialog(null, "Do you want to start a new game?", 
                        "New game", JOptionPane.YES_NO_OPTION);
                if (resnew == JOptionPane.YES_OPTION) {
                    initGameField();
                    shuffle();
                }
            }
        });
        panelbtn.add(btnnewgame);
        
        //Button "Exit"
        java.awt.Button btnexit = new java.awt.Button(BTN_EXIT);
        btnexit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int resexit = JOptionPane.showConfirmDialog(null, "Do you want to exit?", 
                        "Exit confirmation", JOptionPane.YES_NO_OPTION);
                if (resexit == JOptionPane.YES_OPTION) System.exit(0);
            }
        });
        panelbtn.add(btnexit);

        setVisible(true); 
        
        //show message if win
        if (isGameOver()) {
            JOptionPane.showMessageDialog(null, "YOU WIN!", "Congratulations!", JOptionPane.INFORMATION_MESSAGE);
            initGameField();
            shuffle();
        }
    }

    //click on field buttons events
    @Override
    public void actionPerformed(ActionEvent e) {
        java.awt.Button curbtn = (java.awt.Button)(e.getSource());
        int[] xy = curbtn.getElementByXY(curbtn.getX(), curbtn.getY());
        int k = xy[0];
        int l = xy[1];
        mem = null;
        if (l + 1 < 4) {
            if (arr[k][l + 1].getText() == "") {
                gamepanel.remove(arr[k][l]);
                gamepanel.remove(arr[k][l + 1]);
                this.repaint();
                mem = arr[k][l + 1];
                arr[k][l + 1] = arr[k][l];
                arr[k][l] = mem;
                gamepanel.add(arr[k][l]);
                gamepanel.add(arr[k][l + 1]);
            }
        }
        if (l - 1 >= 0) {
            if (arr[k][l - 1].getText() == "") {
                gamepanel.remove(arr[k][l]);
                gamepanel.remove(arr[k][l - 1]);
                this.repaint();
                mem = arr[k][l - 1];
                arr[k][l - 1] = arr[k][l];
                arr[k][l] = mem;
                gamepanel.add(arr[k][l]);
                gamepanel.add(arr[k][l - 1]);
            }
        }
        if (k + 1 < 4) {
            if (arr[k + 1][l].getText() == "") {
                gamepanel.remove(arr[k][l]);
                gamepanel.remove(arr[k + 1][l]);
                this.repaint();
                mem = arr[k + 1][l];
                arr[k + 1][l] = arr[k][l];
                arr[k][l] = mem;
                gamepanel.add(arr[k][l]);
                gamepanel.add(arr[k + 1][l]);
            }
        }
        if (k - 1 >= 0) {
            if (arr[k - 1][l].getText() == "") {
                gamepanel.remove(arr[k][l]);
                gamepanel.remove(arr[k - 1][l]);
                this.repaint();
                mem = arr[k - 1][l];
                arr[k - 1][l] = arr[k][l];
                arr[k][l] = mem;
                gamepanel.add(arr[k][l]);
                gamepanel.add(arr[k - 1][l]);
            }
        }
        gamepanel.repaintField(arr);
    }
    
    //Initial fill the array by buttons
    void initGameField() {
        gamepanel.removeAll();
        int textnum = 1;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (textnum == 16) {
                    arr[i][j] = new java.awt.Button("", 70);
                } else {
                    arr[i][j] = new java.awt.Button(Integer.toString(textnum), 70);
                }
                arr[i][j].addActionListener(this);
                gamepanel.add(arr[i][j]);
                textnum++;
            }
        }
        gamepanel.repaintField(arr);
    }

    //check if win method
    boolean isGameOver() {
        int count = 1;
        int sum = 0;
        boolean status = false;
        
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr.length; j++) {
                if (arr[i][j].getText() != "") {
                    if (Integer.parseInt(arr[i][j].getText()) == count) {
                        sum += count;
                    }
                }
                count++;
            }
        }    
        if (sum == 120) status = true;
        return status;
    }
    
    //shuffling method
    void shuffle() {
        Random rnd = new Random();
        java.awt.Button mem = new Button("");
        int[][] txtarr = new int[4][4];
        int txtnum = 1;
        int ind1 = 0;
        int ind2 = 0;
        int a = 0;
        gamepanel.removeAll();
        
        //fill index array 'txtarr' by numbers from 1 to 16
        for (int i = 0; i < txtarr.length; i++) {
            for (int j = 0; j < txtarr.length; j++) {
                txtarr[i][j] = txtnum;      
                txtnum++;                
            }
        }        

        //shuffling index array 'txtarr'
        for (int i = txtarr.length - 1; i >= 0; i--) {
            for (int j = txtarr.length - 1; j >= 0; j--) {
                ind1 = rnd.nextInt(i + 1);
                ind2 = rnd.nextInt(j + 1);
                a = txtarr[ind1][ind2];
                txtarr[ind1][ind2] = txtarr[i][j];
                txtarr[i][j] = a;                
            }
        }

        //shuffling Buttons array 'arr'
        for (int i = 0; i < txtarr.length; i++) {
            for (int j = 0; j < txtarr.length; j++) {
                for (int k = 0; k < arr.length; k++) {
                    for (int l = 0; l < arr.length; l++) {  
                        if (arr[k][l].getText() != "" && txtarr[i][j] < 16) {
                            if (Integer.parseInt(arr[k][l].getText()) == txtarr[i][j]) {
                                mem = arr[i][j];
                                arr[i][j] = arr[k][l];
                                arr[k][l] = mem;
                            }
                        } else if (arr[k][l].getText() == "" && txtarr[i][j] == 16) {
                            mem = arr[i][j];
                            arr[i][j] = arr[k][l];
                            arr[k][l] = mem;
                        } 
                    }
                }
            }
        }
        gamepanel.repaintField(arr);
    }
}