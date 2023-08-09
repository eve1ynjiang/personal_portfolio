package tests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import model.BoardCell;
import model.ClearCellGame;
import model.Game;

/* The following directive executes tests in sorted order */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class StudentTests {
	//helper method taken from public tests
	private static String getBoardStr(Game game) {
		int maxRows = game.getMaxRows(), maxCols = game.getMaxCols();

		String answer = "Board(Rows: " + maxRows + ", Columns: " + maxCols + ")\n";
		for (int row = 0; row < maxRows; row++) {
			for (int col = 0; col < maxCols; col++) {
				answer += game.getBoardCell(row, col).getName();
			}
			answer += "\n";
		}

		return answer;
	}
	
	//tests if the game ends
	@Test
	public void testEndGame() {
		System.out.println("=============== test endGame ===============");
		int maxRows = 8, maxCols = 8, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);

		ccGame.setBoardWithColor(BoardCell.BLUE);
		ccGame.setRowWithColor(maxRows - 1, BoardCell.EMPTY);

		String answer = "Before next animation step\n\n";
		answer += getBoardStr(ccGame);
		ccGame.nextAnimationStep();
		answer += "\nAfter next animation step\n";
		answer += getBoardStr(ccGame);
		System.out.print(answer);

		assertTrue(ccGame.isGameOver() == true);
	}
	
	//checks if processCell clears a vertical row
	@Test
	public void test01VerticalCells() {
		System.out.println("=============== test 01 ===============");
		int maxRows = 8, maxCols = 8, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);

		ccGame.setBoardWithColor(BoardCell.BLUE);
		ccGame.setColWithColor(1, BoardCell.YELLOW);
		ccGame.setRowWithColor(maxRows - 1, BoardCell.EMPTY);

		String answer = "Before processCell\n\n";
		answer += getBoardStr(ccGame);
		ccGame.processCell(3, 1);
		answer += "\nAfter processCell\n";
		answer += getBoardStr(ccGame);
		System.out.print(answer);
	}
	
	//checks if processCell clears a vertical and horizontal row and clears row
	@Test
	public void test02VerticalandHorizontalCells() {
		System.out.println("=============== test 02 ===============");
		int maxRows = 8, maxCols = 8, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);

		ccGame.setBoardWithColor(BoardCell.BLUE);
		ccGame.setColWithColor(1, BoardCell.YELLOW);
		ccGame.setRowWithColor(3, BoardCell.YELLOW);
		ccGame.setRowWithColor(maxRows - 1, BoardCell.EMPTY);

		String answer = "Before processCell\n\n";
		answer += getBoardStr(ccGame);
		ccGame.processCell(3, 1);
		answer += "\nAfter processCell\n";
		answer += getBoardStr(ccGame);
		System.out.print(answer);
	}
	
	//checks if processCell clears and vertical and horizontal row 
	@Test
	public void test03VerticalandHorizontalCells() {
		System.out.println("=============== test 03 ===============");
		int maxRows = 8, maxCols = 8, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);

		ccGame.setBoardWithColor(BoardCell.BLUE);
		ccGame.setColWithColor(1, BoardCell.YELLOW);
		ccGame.setRowWithColor(3, BoardCell.YELLOW);
		ccGame.setRowWithColor(maxRows - 1, BoardCell.EMPTY);
		ccGame.setBoardCell(3, maxCols - 1, BoardCell.RED);

		String answer = "Before processCell\n\n";
		answer += getBoardStr(ccGame);
		ccGame.processCell(3, 1);
		answer += "\nAfter processCell\n";
		answer += getBoardStr(ccGame);
		System.out.print(answer);
	}
	
	//checks if the diagonals are cleared
	@Test
	public void test04diagonalUpRightCells() {
		System.out.println("=============== test 04 ===============");
		int maxRows = 8, maxCols = 8, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);

		ccGame.setBoardWithColor(BoardCell.BLUE);
		ccGame.setRowWithColor(maxRows - 1, BoardCell.EMPTY);
		ccGame.setBoardCell(4, 2, BoardCell.RED);
		ccGame.setBoardCell(3, 2, BoardCell.RED);
		ccGame.setBoardCell(3, 3, BoardCell.RED);
		ccGame.setBoardCell(2, 4, BoardCell.RED);
		ccGame.setBoardCell(1, 5, BoardCell.RED);
		

		String answer = "Before processCell\n\n";
		answer += getBoardStr(ccGame);
		ccGame.processCell(4, 2);
		answer += "\nAfter processCell\n";
		answer += getBoardStr(ccGame);
		System.out.print(answer);
	}
	
	//checks if the diagonals are cleared
	@Test
	public void test05diagonalUpLeftCells() {
		System.out.println("=============== test 05 ===============");
		int maxRows = 8, maxCols = 8, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);

		ccGame.setBoardWithColor(BoardCell.BLUE);
		ccGame.setRowWithColor(maxRows - 1, BoardCell.EMPTY);
		ccGame.setBoardCell(4, 4, BoardCell.RED);
		ccGame.setBoardCell(3, 3, BoardCell.RED);
		ccGame.setBoardCell(2, 2, BoardCell.RED);
		ccGame.setBoardCell(1, 1, BoardCell.RED);
		ccGame.setBoardCell(0, 0, BoardCell.RED);
		

		String answer = "Before processCell\n\n";
		answer += getBoardStr(ccGame);
		ccGame.processCell(4, 4);
		answer += "\nAfter processCell\n";
		answer += getBoardStr(ccGame);
		System.out.print(answer);
	}
	
	//checks if the diagonals are cleared
	@Test
	public void test06diagonalDownRightCells() {
		System.out.println("=============== test 06 ===============");
		int maxRows = 8, maxCols = 8, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);

		ccGame.setBoardWithColor(BoardCell.BLUE);
		ccGame.setRowWithColor(maxRows - 1, BoardCell.EMPTY);
		ccGame.setBoardCell(4, 4, BoardCell.RED);
		ccGame.setBoardCell(3, 3, BoardCell.RED);
		ccGame.setBoardCell(2, 2, BoardCell.RED);
		ccGame.setBoardCell(1, 1, BoardCell.RED);
		ccGame.setBoardCell(0, 0, BoardCell.RED);
		

		String answer = "Before processCell\n\n";
		answer += getBoardStr(ccGame);
		ccGame.processCell(0, 0);
		answer += "\nAfter processCell\n";
		answer += getBoardStr(ccGame);
		System.out.print(answer);
	}
	
	//checks if the diagonals are cleared
	@Test
	public void test07diagonalDownLeftCells() {
		System.out.println("=============== test 07 ===============");
		int maxRows = 8, maxCols = 8, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);

		ccGame.setBoardWithColor(BoardCell.BLUE);
		ccGame.setRowWithColor(maxRows - 1, BoardCell.EMPTY);
		ccGame.setBoardCell(4, 2, BoardCell.RED);
		ccGame.setBoardCell(3, 2, BoardCell.RED);
		ccGame.setBoardCell(3, 3, BoardCell.RED);
		ccGame.setBoardCell(2, 4, BoardCell.RED);
		ccGame.setBoardCell(1, 5, BoardCell.RED);
		

		String answer = "Before processCell\n\n";
		answer += getBoardStr(ccGame);
		ccGame.processCell(1, 5);
		answer += "\nAfter processCell\n";
		answer += getBoardStr(ccGame);
		System.out.print(answer);
	}
	
	//checks if processCell works properly
	@Test
	public void test08processCellFull() {
		System.out.println("=============== test 08 ===============");
		int maxRows = 8, maxCols = 8, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);

		ccGame.setBoardWithColor(BoardCell.BLUE);
		ccGame.setRowWithColor(maxRows - 1, BoardCell.EMPTY);

		String answer = "Before processCell\n\n";
		answer += getBoardStr(ccGame);
		ccGame.processCell(4, 4);
		answer += "\nAfter processCell\n";
		answer += getBoardStr(ccGame);
		System.out.print(answer);
	}
	
	//checks if processCell works properly
	@Test
	public void test09processCellFull() {
		System.out.println("=============== test 09 ===============");
		int maxRows = 8, maxCols = 8, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);

		ccGame.setBoardWithColor(BoardCell.BLUE);
		ccGame.setRowWithColor(maxRows - 1, BoardCell.EMPTY);

		String answer = "Before processCell\n\n";
		answer += getBoardStr(ccGame);
		ccGame.processCell(0, 0);
		answer += "\nAfter processCell\n";
		answer += getBoardStr(ccGame);
		System.out.print(answer);
	}
	
	//checks if processCell works properly
	@Test
	public void test10processCellFull() {
		System.out.println("=============== test 10 ===============");
		int maxRows = 8, maxCols = 8, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);

		ccGame.setBoardWithColor(BoardCell.BLUE);
		ccGame.setRowWithColor(maxRows - 1, BoardCell.EMPTY);
		ccGame.setBoardCell(3, 4, BoardCell.RED);
		

		String answer = "Before processCell\n\n";
		answer += getBoardStr(ccGame);
		ccGame.processCell(6, 4);
		answer += "\nAfter processCell\n";
		answer += getBoardStr(ccGame);
		System.out.print(answer);
	}
	
	//checks if the correct score is returned
	@Test
	public void test11score() {
		int maxRows = 8, maxCols = 8, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);

		ccGame.setBoardWithColor(BoardCell.BLUE);
		ccGame.setRowWithColor(maxRows - 1, BoardCell.EMPTY);

		String answer = "Before processCell\n\n";
		answer += getBoardStr(ccGame);
		ccGame.processCell(4, 4);
		answer += "\nAfter processCell\n";
		answer += getBoardStr(ccGame);
		
		assertTrue(ccGame.getScore() == 25);
	}
	
		
}
