package com.checkers.testing;

import com.checkers.board.Board;
import com.checkers.figures.FigureColor;
import com.checkers.figures.Pawn;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Checkers Test Suite")
public class CheckersTestSuite {
    @BeforeEach
    public void before() {
        System.out.println("Test stage: begin");
    }

    @AfterEach
    public void after() {
        System.out.println("Test stage: complete");
    }

    @DisplayName("testing player pick")
    @Test
    void testBooleanCheckPlayerPick() {
        //Given
        Board newBoard = new Board();
        newBoard.setFigure(4, 1, new Pawn(FigureColor.BLACK));
        boolean result = true;
        FigureColor whoseMove = FigureColor.BLACK;

        //When
        boolean firstPick = newBoard.isInRange(4, 1);
        boolean isThereAFigure = newBoard.isFigurePresent(4, 1);
        boolean colorRound = newBoard.checkColor(4,1);
        boolean tileColor = newBoard.checkTileColor(4, 2);

        //Then
        assertTrue(firstPick);
        assertTrue(isThereAFigure);
        //assertFalse(colorRound);
        assertFalse(tileColor);
        assertFalse(newBoard.checkPlayerPick(4, 1, 5, 2, result));
        assertFalse(newBoard.move(4,1, 5, 3));
    }

    /*@Test
    void testPlayerRoundSwitch() {
        //Given
        Board newBoard = new Board();
        newBoard.setFigure(4, 1, new Pawn(FigureColor.WHITE));
        boolean result = true;
        FigureColor whoseMove = FigureColor.WHITE;

        //When
        newBoard.move(4, 1, 5, 2);

        //Then
        assertEquals(FigureColor.BLACK, whoseMove);
    }*/
}
