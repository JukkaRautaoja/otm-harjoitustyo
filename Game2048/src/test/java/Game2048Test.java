/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import game2048.Board;
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
        board.getBoard()[0][1]=2;
        board.getBoard()[0][2]=2;
        board.moveLeft();
        assertEquals(board.getBoard()[0][0], 8);
    }
    @Test
    public void moveRightWorks(){
        board.getBoard()[0][1]=2;
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
        board.moveUp();
        assertEquals(board.getBoard()[0][2], 8);
    }
}
