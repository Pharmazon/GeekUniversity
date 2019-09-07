package ru.shcheglov.Homework6.TicTacToe;

import java.util.Scanner;

public class Human extends Player {

    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
    }

    Human(Playfield pf, Message msg) {
        super(pf, msg);
    }

    public void turn(char dot) {
        int x = 0;
        int y = 0;
        msg.yourTurn(pf.getFieldsize());
        do {
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!pf.isCellValid(x, y));
        pf.map[y][x] = dot;
        pf.printMap();
    }
}
