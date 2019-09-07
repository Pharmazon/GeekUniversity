package ru.shcheglov.Homework6.TicTacToe;

/**
 * Java 1. Homework 6. TicTacToe game 
 * Class: Player, Human, Ai
 *
 * @author Alexey Shcheglov
 * @version dated Dec 16, 2017
 * @link https://github.com/Pharmazon
 */

abstract class Player {

    Playfield pf;
    Message msg;
    
    public static void main(String[] args) {  
    }
    
    Player(Playfield pf, Message msg) {
        this.pf = pf;
        this.msg = msg;
    }
}



