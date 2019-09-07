package ru.shcheglov.Homework5;

/**
 * Java 1. Homework 5. class Playfield for TicTacToe game
 *
 * @author Alexey Shcheglov
 * @version dated Dec 08, 2017
 * @link https://github.com/Pharmazon
 */
 
class Playfield {

    private int fieldsize;
    private char dot_empty;
    private int seriestowin;
    char[][] map;

    Playfield(int fieldsize, char dot_empty, int seriestowin) {
        setFieldsize(fieldsize);
        setDotempty(dot_empty);
        setSeriestowin(seriestowin);
        this.map = new char[fieldsize][fieldsize];
        for (int i = 0; i < fieldsize; i++) {
            for (int j = 0; j < fieldsize; j++) {
                this.map[i][j] = dot_empty;
            }
        }
    }

    //getter-setter fieldsize
    void setFieldsize(int fieldsize) {
        if (fieldsize >= 3) this.fieldsize = fieldsize;
    }
    
    int getFieldsize() {
        return fieldsize;
    } 
    
    //getter-setter dot_empty
    void setDotempty(char dot_empty) {
        this.dot_empty = dot_empty;
    }
    
    char getDotempty() {
        return dot_empty;
    }     

    //getter-setter seriestowin
    void setSeriestowin(int seriestowin) {
        if (seriestowin > 0) this.seriestowin = seriestowin;
    }
    
    int getSeriestowin() {
        return seriestowin;
    }  
    
    void printMap() {
        for (int i = 0; i < this.fieldsize; i++) {
            for (int j = 0; j < this.fieldsize; j++) {
                System.out.print(this.map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    boolean isMapFull() {
        for (int i = 0; i < this.fieldsize; i++) {
            for (int j = 0; j < this.fieldsize; j++) {
                if (this.map[i][j] == this.dot_empty) return false;
            }
        }
        return true; 
    }

    boolean isCellValid (int x, int y) {
        if (x < 0 || y < 0 || x >= this.fieldsize || y >= this.fieldsize) return false;
        if (this.map[y][x] == this.dot_empty) return true;
        return false;
    }

    boolean checkWin(char dot) {
        int counthor = 0;
        int countver= 0;
        int countdiar = 0;
        int countdial = 0;
        for (int i = 0; i < this.fieldsize; i++) {
            for (int j = 0; j < this.fieldsize; j++) {
                //horizontals and verticals
                if (this.map[i][j] == dot) counthor++;
                if (this.map[j][i] == dot) countver++;
                if (counthor == this.seriestowin || countver == this.seriestowin) return true; 
                if (this.map[i][j] != dot) counthor = 0;
                if (this.map[j][i] != dot) countver = 0;
                //diagonals left to right (direct)
                if (i <= this.fieldsize - this.seriestowin && j <= this.fieldsize - this.seriestowin) {
                    if (this.map[j][j + i] == dot) countdiar++;  
                    if (countdiar == this.seriestowin) return true; 
                    if (this.map[j][j + i] != dot) countdiar = 0;
                }
                //diagonals right to left (reverse)
                if (i >= this.seriestowin - 1 && j >= this.seriestowin - 1) {
                    if (this.map[j][this.fieldsize - 1 - i] == dot) countdial++; 
                    if (countdial == this.seriestowin) return true; 
                    if (this.map[j][this.fieldsize - 1 - i] != dot) countdial = 0;  
                }
            }    
        }
        return false;
    }
}