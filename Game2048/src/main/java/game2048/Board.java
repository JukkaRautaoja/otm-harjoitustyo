/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game2048;

import java.util.Random;
import javafx.scene.control.Button;

/**
 *
 * @author jukka
 */
public class Board {

    private int[][] array;
    private Random random;
    private int max;

    public Board() {
        this.array = new int[4][4];
        this.array[0][0] = 4;
        this.max = 4;
        this.random = new Random();
    }

    //returns largest number in tiles
    public void checkMax() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (array[i][j] > max) {
                    max = array[i][j];
                }
            }
        }
    }
    
    //returns current largest number in tiles
    public int getMax() {
        return this.max;
    }

    //returns the array of the board
    public int[][] getBoard() {
        return this.array;
    }

    //sets given board
    public void setBoard(int[][] newBoard) {
        this.array = newBoard;
    }
    
    //resets the board for a new game
    public void restart(){
        this.array = new int[4][4];
        this.array[0][0] = 4;
    }

    public void moveRight() {
        for (int i = 0; i < 3; i++) {
            for (int y = 0; y < 4; y++) {
                for (int x = 3; x > 0; x--) {
                    int value = array[y][x-1];
                    if (array[y][x - 1] == array[y][x] || array[y][x] == 0) {
                        array[y][x] += value;
                        array[y][x-1] = 0;
                    }
                }
            }
        }
        checkMax();
        newValue();
    }

    public void moveUp() {
        for (int i = 0; i < 3; i++) {
            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < 4; x++) {
                    int value = array[y + 1][x];
                    if (array[y][x] == array[y + 1][x] || array[y][x] == 0) {
                        array[y][x] += value;
                        array[y + 1][x] = 0;
                    }
                }
            }
        }
        checkMax();
        newValue();
    }

    public void moveDown() {
        for (int i = 0; i < 3; i++) {
            for (int y = 3; y > 0; y--) {
                for (int x = 0; x < 4; x++) {
                    int value = array[y - 1][x];
                    if (array[y - 1][x] == array[y][x] || array[y][x] == 0) {
                        array[y][x] += value;
                        array[y - 1][x] = 0;
                    }
                }
            }
        }
        checkMax();
        newValue();
    }

    public void moveLeft() {
        for (int i = 0; i < 3; i++) {
            for (int y = 0; y < 4; y++) {
                for (int x = 0; x < 3; x++) {
                    int value = array[y][x + 1];
                    if (array[y][x + 1] == array[y][x] || array[y][x] == 0) {
                        array[y][x] += value;
                        array[y][x + 1] = 0;
                    }
                }
            }
        }
        checkMax();
        newValue();
    }

    //random generates new value after each move if possible
    public void newValue() {
        int counter = 0;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (array[y][x] != 0) {
                    counter++;
                }
            }
        }
        if (counter < 16) {
            while (true) {
                int newY = random.nextInt(4);
                int newX = random.nextInt(4);
                if (array[newY][newX] == 0) {
                    array[newY][newX] = 2;
                    break;
                }
            }
        }
    }

    @Override
    public String toString() {
        return "" + array[0][0] + " " + array[0][1] + " " + array[0][2] + " " + array[0][3] + "\n" + "\n"
                + array[1][0] + " " + array[1][1] + " " + array[1][2] + " " + array[1][3] + "\n" + "\n"
                + array[2][0] + " " + array[2][1] + " " + array[2][2] + " " + array[2][3] + "\n" + "\n"
                + array[3][0] + " " + array[3][1] + " " + array[3][2] + " " + array[3][3];
    }

    //checks if the game has ended
    public boolean end() {
        if (max == 2048) {
            return true;
        }
        int isRunning = 0;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (array[y][x] == 0) {
                    isRunning++;
                }
                if (y == 0 && x == 0) {
                    if (array[y][x] == array[y + 1][x] || array[y][x] == array[y][x + 1]) {
                        isRunning++;
                    }
                } else if (y == 0 && x == 3) {
                    if (array[y][x] == array[y + 1][x] || array[y][x] == array[y][x - 1]) {
                        isRunning++;
                    }
                } else if (y == 0) {
                    if (array[y][x] == array[y + 1][x] || array[y][x] == array[y][x + 1] || array[y][x] == array[y][x - 1]) {
                        isRunning++;
                    }
                } else if (y == 3 && x == 0) {
                    if (array[y][x] == array[y - 1][x] || array[y][x] == array[y][x + 1]) {
                        isRunning++;
                    }
                } else if (y == 3 && x == 3) {
                    if (array[y][x] == array[y - 1][x] || array[y][x] == array[y][x - 1]) {
                        isRunning++;
                    }
                } else if (y == 3) {
                    if (array[y][x] == array[y - 1][x] || array[y][x] == array[y][x + 1] || array[y][x] == array[y][x - 1]) {
                        isRunning++;
                    }
                } else if (x == 0) {
                    if (array[y][x] == array[y - 1][x] || array[y][x] == array[y][x + 1] || array[y][x] == array[y + 1][x]) {
                        isRunning++;
                    }
                } else if (x == 3) {
                    if (array[y][x] == array[y - 1][x] || array[y][x] == array[y][x - 1] || array[y][x] == array[y + 1][x]) {
                        isRunning++;
                    }
                } else {
                    if (array[y][x] == array[y - 1][x] || array[y][x] == array[y][x - 1] || array[y][x] == array[y + 1][x] || array[y][x] == array[y][x + 1]) {
                        isRunning++;
                    }
                }
            }

        }
        if (isRunning > 0) {
            return false;
        } else {
            return true;
        }
    }
}
