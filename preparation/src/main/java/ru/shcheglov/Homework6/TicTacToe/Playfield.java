package ru.shcheglov.Homework6.TicTacToe;

/**
 * Java 1. Homework 6. TicTacToe game 
 * Class: PlayField
 *
 * @author Alexey Shcheglov
 * @version dated Dec 16, 2017
 * @link https://github.com/Pharmazon
 */
 
public class Playfield {

    private int fieldsize;
    private char dot_empty;
    private int seriestowin;
    char[][] map;

    public static void main(String[] args) {  
    }
    
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
    public void setFieldsize(int fieldsize) {
        if (fieldsize >= 3) this.fieldsize = fieldsize;
    }
    
    public int getFieldsize() {
        return fieldsize;
    } 
    
    //getter-setter dot_empty
    public void setDotempty(char dot_empty) {
        this.dot_empty = dot_empty;
    }
    
    public char getDotempty() {
        return dot_empty;
    }     

    //getter-setter seriestowin
    public void setSeriestowin(int seriestowin) {
        if (seriestowin > 0) this.seriestowin = seriestowin;
    }
    
    public int getSeriestowin() {
        return seriestowin;
    }  
    
    public void printMap() {
        for (int i = 0; i < this.fieldsize; i++) {
            for (int j = 0; j < this.fieldsize; j++) {
                System.out.print(this.map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public boolean isMapFull() {
        for (int i = 0; i < this.fieldsize; i++) {
            for (int j = 0; j < this.fieldsize; j++) {
                if (this.map[i][j] == this.dot_empty) return false;
            }
        }
        return true; 
    }

    public boolean isCellValid (int x, int y) {
        if (x < 0 || y < 0 || x >= this.fieldsize || y >= this.fieldsize) return false;
        if (this.map[y][x] == this.dot_empty) return true;
        return false;
    }

    public boolean checkWin(char dot) {    
        for (int y = 0; y < this.fieldsize; y++)
            for (int x = 0; x < this.fieldsize; x++)
                for (int dy = -1; dy < 2; dy++)
                    for (int dx = -1; dx < 2; dx++)
                        if (checkLine(x, y, dx, dy, dot) == this.seriestowin)
                            return true;
        return false;
    }
        
    private int checkLine(int x, int y, int dx, int dy, char dot) {
        int count = 0;                  
        if (dx == 0 && dy == 0)
            return 0;
        for (int i = 0, xi = x, yi = y;
                i < this.seriestowin; i++, xi += dx, yi += dy)
            if (xi >= 0 && yi >= 0 && xi < this.fieldsize &&
                    yi < this.fieldsize && map[yi][xi] == dot)
                count++;
        return count;
    }
}