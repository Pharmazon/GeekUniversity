package ru.shcheglov.Homework5;

/**
 * Java 1. Homework 5. TicTacToe game in console with simple AI
 * using object-oriented approach
 *
 * @author Alexey Shcheglov
 * @version dated Dec 08, 2017
 * @link https://github.com/Pharmazon
 */

import java.util.Random;
import java.util.Scanner;

class TicTacToe {
    
    final int SIZE = 7;
    final int SERIES_TO_WIN = 4;
    final char DOT_X = 'x';
    final char DOT_O = 'o';
    final char DOT_EMPTY = '.';
    final String MSG_FOR_HUMAN = "Enter X and Y [1.." + SIZE + "]: ";
    final String MSG_YOU_WON = "YOU WON!";
    final String MSG_AI_WON = "AI WON!";
    final String MSG_AI_TURN = "AITurn:";
    final String MSG_DRAW = "Sorry, DRAW!";
    final String MSG_GAME_OVER = "GAME OVER.";
    Playfield pf = new Playfield(SIZE, DOT_EMPTY, SERIES_TO_WIN);
    Scanner sc = new Scanner(System.in);
    Random rand = new Random();
    
    public static void main(String[] args) {
        new TicTacToe();
    }
    
    TicTacToe() {
        while (true) {
            humanTurn(DOT_X);
            if (pf.checkWin(DOT_X)) {
                System.out.println(MSG_YOU_WON);
                break;
            }
            if (pf.isMapFull()) {
                System.out.println(MSG_DRAW);
                break;
            }
            aiTurn(DOT_O, DOT_X);
            if (pf.checkWin(DOT_O)) {
                System.out.println(MSG_AI_WON);
                break;
            }
            if (pf.isMapFull()) {
                System.out.println(MSG_DRAW);
                break;
            }
        }
        System.out.println(MSG_GAME_OVER);
    }
    
    void humanTurn(char dot) {
        int x, y;
        do {
            System.out.print(MSG_FOR_HUMAN); 
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!pf.isCellValid(x, y));
        pf.map[y][x] = dot;
        pf.printMap();
    }    
    
    void aiTurn(char dot, char enemyDot) {  
        int x, y;
        for (x = 0; x < pf.getFieldsize(); x++)
            for (y = 0; y < pf.getFieldsize(); y++)
                if (pf.isCellValid(x, y)) {
                    pf.map[y][x] = enemyDot;
                    if (pf.checkWin(enemyDot)) {
                        pf.map[y][x] = dot;
                        return;
                    }
                    pf.map[y][x] = pf.getDotempty();
                }
        do {
            x = rand.nextInt(pf.getFieldsize());
            y = rand.nextInt(pf.getFieldsize());
        } while (!pf.isCellValid(x, y));
        pf.map[y][x] = dot;
        pf.printMap();
    }    
}