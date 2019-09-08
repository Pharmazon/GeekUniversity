package ru.shcheglov.Homework4; /**
 * Java 1. Homework 4. TicTacToe game in console with simple AI.
 *
 * @author Alexey Shcheglov
 * @version dated Dec 03, 2017
 * @link https://github.com/Pharmazon
 */

import java.util.Random;
import java.util.Scanner;

public class Main {
    
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
    char[][] map = new char[SIZE][SIZE];
    Scanner sc = new Scanner(System.in);
    Random rand = new Random();
    
    public static void main(String[] args) {
        new Main();
    }
    
    //constructor
    Main() {
        initMap(SIZE);
        while (true) {
            humanTurn(SIZE, DOT_X);
            printMap(SIZE);
            if (checkWin(DOT_X, SIZE, SERIES_TO_WIN)) {
                System.out.println(MSG_YOU_WON);
                break;
            }
            if (isMapFull(SIZE)) {
                System.out.println(MSG_DRAW);
                break;
            }
            aiTurn(SIZE, DOT_O, DOT_X, SERIES_TO_WIN);
            printMap(SIZE);
            if (checkWin(DOT_O, SIZE, SERIES_TO_WIN)) {
                System.out.println(MSG_AI_WON);
                break;
            }
            if (isMapFull(SIZE)) {
                System.out.println(MSG_DRAW);
                break;
            }
        }
        System.out.println(MSG_GAME_OVER);
    }
    
    void initMap(int fieldsize) {
        for (int i = 0; i < fieldsize; i++) {
            for (int j = 0; j < fieldsize; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }
    
    void printMap(int fieldsize) {
        for (int i = 0; i < fieldsize; i++) {
            for (int j = 0; j < fieldsize; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    boolean isMapFull(int fieldsize) {
        for (int i = 0; i < fieldsize; i++) {
            for (int j = 0; j < fieldsize; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true; 
    }
    
    void humanTurn(int fieldsize, char dot) {
        int x, y;
        do {
            System.out.print(MSG_FOR_HUMAN); 
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y, fieldsize));
        map[y][x] = dot;
    }
    
    //Tasks 5
    void aiTurn(int fieldsize, char dot, char enemyDot, int seriestowin) {  // AI action
        int x, y;
        for (x = 0; x < fieldsize; x++)                                     // simple blocking
            for (y = 0; y < fieldsize; y++)
                if (isCellValid(x, y, fieldsize)) {                         // if cell empty
                    map[y][x] = enemyDot;                                   // try to be like enemy
                    if (checkWin(enemyDot, fieldsize, seriestowin)) {       // if win
                        map[y][x] = dot;                                    // block
                        return;                                             // and exit
                    }
                    map[y][x] = DOT_EMPTY;                                  // restore cell
                }
        do {
            x = rand.nextInt(fieldsize);
            y = rand.nextInt(fieldsize);
        } while (!isCellValid(x, y, fieldsize));
        map[y][x] = dot;
    }
    
    //Tasks 3 and 4
    boolean checkWin(char dot, int fieldsize, int seriestowin) {
        int counthor = 0;
        int countver= 0;
        int countdiar = 0;
        int countdial = 0;
        for (int i = 0; i < fieldsize; i++) {
            for (int j = 0; j < fieldsize; j++) {
                //horizontals and verticals
                if (map[i][j] == dot) counthor++;
                if (map[j][i] == dot) countver++;
                if (counthor == seriestowin || countver == seriestowin) return true; 
                if (map[i][j] != dot) counthor = 0;
                if (map[j][i] != dot) countver = 0;
                //diagonals left to right (direct)
                if (i <= fieldsize - seriestowin && j <= fieldsize - seriestowin) {
                    if (map[j][j + i] == dot) countdiar++;  
                    if (countdiar == seriestowin) return true; 
                    if (map[j][j + i] != dot) countdiar = 0;
                }
                //diagonals right to left (reverse)
                if (i >= seriestowin - 1 && j >= seriestowin - 1) {
                    if (map[j][fieldsize - 1 - i] == dot) countdial++; 
                    if (countdial == seriestowin) return true; 
                    if (map[j][fieldsize - 1 - i] != dot) countdial = 0;  
                }
            }    
        }
        return false;
    }

    /*
    boolean checkWin(char dot, int fieldsize, int seriestowin) {                    
        for (int y = 0; y < fieldsize; y++)
            for (int x = 0; x < fieldsize; x++)
                for (int dy = -1; dy < 2; dy++)
                    for (int dx = -1; dx < 2; dx++)
                        if (checkLine(x, y, dx, dy, dot) == seriestowin)
                            return true; 
        return false;
    }    
    
    int checkLine(int x, int y, int dx, int dy, char dot, int fieldsize, int seriestowin) {
        int count = 0;                              
        if (dx == 0 && dy == 0)
            return 0;
        for (int i = 0, xi = x, yi = y;
                i < seriestowin; i++, xi += dx, yi += dy)
            if (xi >= 0 && yi >= 0 && xi < fieldsize &&
                    yi < fieldsize && map[yi][xi] == dot)
                count++;
        return count;
    }

    */
    
    boolean isCellValid (int x, int y, int fieldsize) {
        if (x < 0 || y < 0 || x >= fieldsize || y >= fieldsize) return false;
        if (map[y][x] == DOT_EMPTY) return true;
        return false;
    }
}