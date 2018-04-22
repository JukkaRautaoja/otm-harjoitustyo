/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import game2048.domain.Board;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jukka
 */
public class Game2048Test {
    Board board;
    Board helperBoard;
    public Game2048Test() {
        
    }
    
    @Before
    public void setUp() {
        board=new Board();
        helperBoard=new Board();
    }
    @Test
    public void toStringWorks(){
        assertEquals(board.toString(), "4 0 0 0"+"\n" +"\n"
                + "0 0 0 0"+"\n"+"\n"
                + "0 0 0 0"+"\n"+"\n"
                + "0 0 0 0");
    }
    @Test
    public void numbersUnite(){
        board.getBoard()[0][1]=4;
        board.moveLeft();
        assertEquals(board.getBoard()[0][0], 8);
    }
    @Test 
    public void maxValueChanges(){
        board.getBoard()[0][1]=4;
        board.moveLeft();
        assertEquals(board.getMax(), 8);
    }
    @Test
    public void moveLeftWorks(){
        board.getBoard()[0][3]=2;
        board.getBoard()[0][2]=2;
        board.moveLeft();
        assertEquals(board.getBoard()[0][0], 8);
    }
    @Test
    public void moveRightWorks(){
        board.getBoard()[0][3]=2;
        board.getBoard()[0][2]=2;
        board.moveRight();
        assertEquals(board.getBoard()[0][3], 8);
    }
    @Test
    public void moveUpWorks(){
        board.getBoard()[1][2]=2;
        board.getBoard()[0][2]=2;
        board.getBoard()[2][2]=4;
        board.moveUp();
        assertEquals(board.getBoard()[0][2], 8);
    }
    @Test
    public void moveDownWorks(){
        board.getBoard()[1][2]=2;
        board.getBoard()[0][2]=2;
        board.getBoard()[2][2]=4;
        board.moveDown();
        assertEquals(board.getBoard()[3][2], 8);
    }
    @Test
    public void generatingNewValue(){
        assertTrue(board.newValue());
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board.getBoard()[i][j]=4;
            }
        }
        assertFalse(board.newValue());
    }
    @Test
    public void gameHasEnded(){
        int filler=0;
        for (int i = 0; i < 4; i++) {
            filler++;
            for (int j = 0; j < 4; j++) {
                if (filler%2==0){
                    board.getBoard()[i][j]=2;
                }else{
                    board.getBoard()[i][j]=4;
                }
                filler++;
            }
        }
        assertEquals(board.end(), true);
    }
    @Test
    public void gameHasEndedV2(){
      board.getBoard()[0][0]=2048;
      board.checkMax();
      assertTrue(board.end());
        
    }
    @Test
    public void gameHasHasNotEnded(){
      assertFalse(board.end());
      board.getBoard()[0][1]=4;
      board.getBoard()[1][0]=4;
      assertFalse(board.end());
        
    }
    @Test
    public void gameHasHasNotEndedv2(){
        board.getBoard()[0][2]=4;
        board.getBoard()[0][3]=4;
        board.getBoard()[0][1]=4;
        assertFalse(board.end());
    }
    @Test
    public void gameHasHasNotEndedv3(){
        board.getBoard()[1][0]=4;
        board.getBoard()[2][0]=4;
        board.getBoard()[3][0]=4;
        board.getBoard()[3][1]=4;
        assertFalse(board.end());
    }
    @Test
    public void gameHasHasNotEndedv4(){
        board.getBoard()[2][2]=4;
        board.getBoard()[2][3]=4;
        board.getBoard()[3][2]=4;
        board.getBoard()[3][1]=4;
        assertFalse(board.end());
        board.restart();
        board.getBoard()[2][0]=4;
        board.getBoard()[3][0]=4;
        board.getBoard()[3][1]=4;
        board.getBoard()[3][2]=4;
        assertFalse(board.end());
        board.restart();
        board.getBoard()[2][3]=4;
        board.getBoard()[3][3]=4;
        board.getBoard()[3][1]=4;
        board.getBoard()[3][2]=4;
        assertFalse(board.end());
        board.restart();
        board.getBoard()[3][0]=4;
        board.getBoard()[2][1]=4;
        board.getBoard()[3][1]=4;
        board.getBoard()[3][2]=4;
        assertFalse(board.end());
        board.restart();
        board.getBoard()[3][3]=4;
        board.getBoard()[2][3]=4;
        board.getBoard()[1][3]=4;
        board.getBoard()[3][2]=4;
        board.getBoard()[3][1]=4;
        assertFalse(board.end());
        board.restart();
        board.getBoard()[1][3]=4;
        board.getBoard()[1][2]=4;
        board.getBoard()[3][1]=4;
        board.getBoard()[2][1]=4;
        board.getBoard()[3][0]=4;
        assertFalse(board.end());
        board.restart();
        board.getBoard()[3][3]=4;
        board.getBoard()[2][2]=4;
        board.getBoard()[1][1]=4;
        board.getBoard()[3][2]=4;
        board.getBoard()[3][1]=4;
        assertFalse(board.end());
    }
    @Test
    public void restartWorks(){
        int filler=0;
        for (int i = 0; i < 4; i++) {
            filler++;
            for (int j = 0; j < 4; j++) {
                if (filler%2==0){
                    board.getBoard()[i][j]=2;
                }else{
                    board.getBoard()[i][j]=4;
                }
                filler++;
            }
        }
        board.restart();
        int helper = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board.getBoard()[i][j]==0) {
                    helper++;
                }
            }
        }
        assertEquals(board.getBoard()[0][0], 4);
        assertEquals(helper, 15);
    }
    @Test
    public void setBoardWorks(){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                helperBoard.getBoard()[i][j]=2;
                }
            }
        board.setBoard(helperBoard.getBoard());
        int helper=0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (helperBoard.getBoard()[i][j]==2){
                    helper++;
                }
            }
        }
        assertEquals(helper, 16);
    }
}
