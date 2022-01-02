package com.checkers.testing;

import com.checkers.board.Board;
import com.checkers.board.Movement;
import com.checkers.figures.Figure;
import com.checkers.figures.FigureColor;
import com.checkers.figures.Pawn;
import com.checkers.figures.Queen;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

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

        //When
        boolean firstPick = newBoard.isInRange(4, 1);
        boolean isThereAFigure = newBoard.isFigurePresent(4, 1);
        boolean tileColor = newBoard.checkTileColor(4, 2);

        //Then
        assertTrue(firstPick);
        assertTrue(isThereAFigure);
        assertFalse(tileColor);
        assertFalse(newBoard.checkPlayerPick(4, 1, 5, 2));
    }

    @DisplayName("testing ppawn hit")
    @Test
    void testBooleanCheckPawnHit() {
        //Given
        Board newBoard = new Board();
        List<Movement> possibleMoves = new ArrayList<>();
        newBoard.setFigure(4, 1, new Pawn(FigureColor.BLACK));

        //When
        newBoard.generatePossibleMoves(FigureColor.BLACK);

        //Then
        assertEquals(2, possibleMoves.size());
    }
}
