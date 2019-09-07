package ru.shcheglov.Homework6.TicTacToe;

/**
 * Java 1. Homework 6. TicTacToe game 
 * Class: Message
 *
 * @author Alexey Shcheglov
 * @version dated Dec 16, 2017
 * @link https://github.com/Pharmazon
 */
 
public class Message {
    
    Message() {
    }

    public static void main(String[] args) {  
    }
    
    public void youWon() {
        System.out.println("YOU WON!");
    }

    public void aiWon() {
        System.out.println("AI WON!");
    }

    public void aiTurn() {
        System.out.println("AI turn:");
    }

    public void yourTurn(int size) {
        System.out.print("Enter X and Y [1.." + size + "]: ");
    }
    
    public void draw() {
        System.out.println("Sorry, DRAW!");
    }

    public void gameOver() {
        System.out.println("GAME OVER.");
    }
}