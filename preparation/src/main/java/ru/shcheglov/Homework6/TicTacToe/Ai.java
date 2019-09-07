package ru.shcheglov.Homework6.TicTacToe;

import java.util.Random;

public class Ai extends Player {

    Random rand = new Random();

    public static void main(String[] args) {
    }

    Ai(Playfield pf, Message msg) {
        super(pf, msg);
    }

    public void turn(char dot, char enemyDot) {
        int x = 0;
        int y = 0;
        msg.aiTurn();
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

