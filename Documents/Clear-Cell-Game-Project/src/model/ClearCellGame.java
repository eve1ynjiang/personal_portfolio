package model;

import java.awt.Color;
import java.util.Random;

/**
 * This class extends GameModel and implements the logic of the clear cell game.
 * We define an empty cell as BoardCell.EMPTY. An empty row is defined as one
 * where every cell corresponds to BoardCell.EMPTY.
 * 
 * @author Department of Computer Science, UMCP
 */

public class ClearCellGame extends Game {
	private Random random;
	private int score;

	/**
	 * Defines a board with empty cells. It relies on the super class constructor to
	 * define the board. The random parameter is used for the generation of random
	 * cells. The strategy parameter defines which clearing cell strategy to use
	 * (for this project it will be 1). For fun, you can add your own strategy by
	 * using a value different that one.
	 * 
	 * @param maxRows
	 * @param maxCols
	 * @param random
	 * @param strategy
	 */
	public ClearCellGame(int maxRows, int maxCols, Random random, int strategy) {
		super(maxRows, maxCols);
		this.random = random;
		strategy = 1; 
	}

	/**
	 * The game is over when the last board row (row with index board.length -1) is
	 * different from empty row.
	 */
	public boolean isGameOver() {
		int counter = 0;
		for (int j = 0; j < getMaxCols(); j++) {
			//goes through the last row and increments counter if the cell is
			//empty
			if (board[getMaxRows() - 1][j] == BoardCell.EMPTY) {
				counter++;
			}
		}
		
		//if the row is empty return false
		if (counter == getMaxCols()) {
			return false;
		} else {
			return true;
		}
		
	}
	
	//returns the score, the score increments every time a cell is cleared
	public int getScore() {
		return score;
	}

	/**
	 * This method will attempt to insert a row of random BoardCell objects if the
	 * last board row (row with index board.length -1) corresponds to the empty row;
	 * otherwise no operation will take place.
	 */
	public void nextAnimationStep() {
		if (!isGameOver()) {
			//shift the current boxes down one row
			for (int i = getMaxRows() - 2; i >= 0; i--) {
				for (int j = 0; j < getMaxCols(); j++) {
					board[i+1][j] = board[i][j];
				}
			}
			
			//then fill the first row with random color cells
			for (int k = 0; k < getMaxCols(); k++) {
				board[0][k] = BoardCell.getNonEmptyRandomBoardCell(random);
			}
        }
	}
	
	//shifts everything under the row in the parameter up one row to 
	//"collapse it"
	private void collapseCells(int rowIndex) {
		if (!isGameOver()) {
			for (int i = rowIndex + 1; i < getMaxRows(); i++) {
				for (int j = 0; j < getMaxCols(); j++) {
					board[i - 1][j] = board[i][j];
				}
			}
		}
	}
	
	//returns true if a row is filled with empty cells
	private boolean rowEmpty(int rowIndex) {		
		int counter = 0;
		for (int j = 0; j < getMaxCols(); j++) {
			if (board[rowIndex][j] == BoardCell.EMPTY) {
				counter++;
			}
		}
		if (counter == getMaxCols()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method will turn to BoardCell.EMPTY the cell selected and any adjacent
	 * surrounding cells in the vertical, horizontal, and diagonal directions that
	 * have the same color. The clearing of adjacent cells will continue as long as
	 * cells have a color that corresponds to the selected cell. Notice that the
	 * clearing process does not clear every single cell that surrounds a cell
	 * selected (only those found in the vertical, horizontal or diagonal
	 * directions).
	 * 
	 * IMPORTANT: Clearing a cell adds one point to the game's score.<br />
	 * <br />
	 * 
	 * If after processing cells, any rows in the board are empty,those rows will
	 * collapse, moving non-empty rows upward. For example, if we have the following
	 * board (an * represents an empty cell):<br />
	 * <br />
	 * RRR<br />
	 * GGG<br />
	 * YYY<br />
	 * * * *<br/>
	 * <br />
	 * then processing each cell of the second row will generate the following
	 * board<br />
	 * <br />
	 * RRR<br />
	 * YYY<br />
	 * * * *<br/>
	 * * * *<br/>
	 * <br />
	 * IMPORTANT: If the game has ended no action will take place.
	 * 
	 * 
	 */
	public void processCell(int rowIndex, int colIndex) {
		Color colorOfClickedCell = board[rowIndex][colIndex].getColor();

		if (!isGameOver()) {
			if (board[rowIndex][colIndex] != BoardCell.EMPTY) {
				//checking the same color horizontally rightward
				for (int i = 1; i < getMaxCols() - colIndex; i++) {
					if (board[rowIndex][colIndex + i].getColor().equals
							(colorOfClickedCell)) {
						setBoardCell(rowIndex, colIndex + i, BoardCell.EMPTY);
						score++;
					} else {
						//leaves the loop
						i = (getMaxCols() - colIndex) + 500;
					}
				}
				
				//checking the same color horizontally leftward
				for (int i = 1; i <= colIndex; i++) {
					if (board[rowIndex][colIndex - i].getColor().equals
							(colorOfClickedCell)) {
						setBoardCell(rowIndex, colIndex - i, BoardCell.EMPTY);
						score++;
							
					} else {
						//leaves the loop
						i = colIndex + 500;
					}
				}
				
				//checking the same color cells vertically upward
				for (int i = 1; i <= rowIndex; i++) {
					if (board[rowIndex - i][colIndex].getColor().equals
							(colorOfClickedCell)) {
						setBoardCell(rowIndex - i, colIndex, BoardCell.EMPTY);
						score++;
					} else {
						//leaves the loop
						i = rowIndex + 500; 
					}
				}
				
				//checking the same color cells vertially downward
				for (int i = 1; i < getMaxRows() - rowIndex; i++) {
					if (board[rowIndex + i][colIndex].getColor().equals
							(colorOfClickedCell)) {
						setBoardCell(rowIndex + i, colIndex, BoardCell.EMPTY);
						score++;
					} else {
						//leaves the loop
						i = (getMaxRows() - rowIndex) + 500;
					}
				}
				
				//checking the same color diagonally upward to the right
				int row1 = rowIndex - 1; //up
				int col1 = colIndex + 1; //right
				while (row1 > -1 && col1 < getMaxCols()) {
					if (board[row1][col1].getColor().equals(colorOfClickedCell)) {
						setBoardCell(row1, col1, BoardCell.EMPTY);
						score++;
						row1--;
						col1++;
					} else {
						//leaves the loop
						row1 = -500;
					}
					
				}
				
				//checking the same color diagonally upward to the left
				int row2 = rowIndex - 1; //up
				int col2 = colIndex -1; //left
				while (row2 > -1 && col2 > -1) {
					if (board[row2][col2].getColor().equals(colorOfClickedCell)) {
						setBoardCell(row2, col2, BoardCell.EMPTY);
						score++;
						row2--;
						col2--;
					} else {
						//leaves the loop
						row2 = -500;
						
					}
					
				}
				
				//checking the same color diagonally downward to the right
				int row3 = rowIndex + 1; //down
				int col3 = colIndex + 1; //right
				while (row3 < getMaxRows() && col3 < getMaxCols()) {
					if (board[row3][col3].getColor().equals(colorOfClickedCell)) {
						setBoardCell(row3, col3, BoardCell.EMPTY);
						score++;
						row3++;
						col3++;
					} else {
						//leaves the loop
						row3 = getMaxRows() + 500;
						
					}
					
				}
				
				//checking the same color diagonally downward to the left
				int row4 = rowIndex + 1; //down
				int col4 = colIndex - 1; //left
				while (row4 < getMaxRows() && col4 > -1) {
					if (board[row4][col4].getColor().equals(colorOfClickedCell)) {
						setBoardCell(row4, col4, BoardCell.EMPTY);
						score++;
						row4++;
						col4--;
					} else {
						//leaves the loop
						row4 = getMaxRows() + 500; 
					}
				}
				
				//turns the clicked cell into an empty cell
				setBoardCell(rowIndex, colIndex, BoardCell.EMPTY);
				score++;
				
				//checks the entire board if there is a row of empty cells and 
				//then shifts everything under it, up one row to make 
				//the row seem "collapsed"
				for (int i = 0; i < getMaxRows(); i++) {
					if (rowEmpty(i) == true) {
						collapseCells(i);
					}
				}
			}
		} 
	}
}