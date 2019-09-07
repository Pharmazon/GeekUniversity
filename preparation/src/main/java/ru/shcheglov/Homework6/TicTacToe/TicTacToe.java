package ru.shcheglov.Homework6.TicTacToe;

/**
 * Java 1. Homework 6. TicTacToe game with simple AI.
 *
 * @author Alexey Shcheglov
 * @version dated Dec 16, 2017
 * @link https://github.com/Pharmazon
 */

class TicTacToe {
    
    final int SIZE = 3;
    final int SERIES_TO_WIN = 3;
    final char DOT_X = 'x';
    final char DOT_O = 'o';
    final char DOT_EMPTY = '.';
    
    Playfield pf = new Playfield(SIZE, DOT_EMPTY, SERIES_TO_WIN);
    Message msg = new Message();
    Human human = new Human(pf, msg);
    Ai ai = new Ai(pf, msg);

    public static void main(String[] args) {
        new TicTacToe();
    }
    
    TicTacToe() {
        while (true) {
            human.turn(DOT_X);
            if (pf.checkWin(DOT_X)) {
                msg.youWon();
                break;
            }
            if (pf.isMapFull()) {
                msg.draw();
                break;
            }
            ai.turn(DOT_O, DOT_X);
            if (pf.checkWin(DOT_O)) {
                msg.aiWon();
                break;
            }
            if (pf.isMapFull()) {
                msg.draw();
                break;
            }
        }
        msg.gameOver();
    }  
}