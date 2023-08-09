package model;

/**
 * This class represents the logic of a game where a board is updated on each
 * step of the game animation. The board can also be updated by selecting a
 * board cell.
 * 
 * @author Department of Computer Science, UMCP
 */

public abstract class Game {
	protected BoardCell[][] board;
	protected int maxRows;
	protected int maxCols;

	/**
	 * Defines a board with BoardCell.EMPTY cells.
	 * 
	 * @param maxRows
	 * @param maxCols
	 */
	public Game(int maxRows, int maxCols) {
		board = new BoardCell[maxRows][maxCols];
		for (int i = 0; i < maxRows; i++) {
			for (int j = 0; j < maxCols; j++) {
				board[i][j] = BoardCell.EMPTY;
			}
		}
	}

	//returns the number of rows
	public int getMaxRows() {
		return board.length;
	}
	
	//returns the number of columns
	public int getMaxCols() {
		return board[0].length;
	}
	
	//sets the cell with the indexs in the parameter equal to the cell in the
	//parameter
	public void setBoardCell(int rowIndex, int colIndex, BoardCell boardCell) {
		board[rowIndex][colIndex] = boardCell;
	}

	//returns the cell in the given parameters
	public BoardCell getBoardCell(int rowIndex, int colIndex) {
		return board[rowIndex][colIndex]; 
	}

	/**
	 * Initializes row with the specified color.
	 * 
	 * @param rowIndex
	 * @param cell
	 */
	public void setRowWithColor(int rowIndex, BoardCell cell) {
		for (int j = 0; j < getMaxCols(); j++) {
			board[rowIndex][j] = cell;
		}
	}

	/**
	 * Initializes column with the specified color.
	 * 
	 * @param colIndex
	 * @param cell
	 */
	public void setColWithColor(int colIndex, BoardCell cell) {
		for (int i = 0; i < getMaxRows(); i++) {
			board[i][colIndex] = cell;
		}
	}

	/**
	 * Initializes the board with the specified color.
	 * 
	 * @param cell
	 */
	public void setBoardWithColor(BoardCell cell) {
		for (int i = 0; i < getMaxRows(); i++) {
			for (int j = 0; j < getMaxCols(); j++) {
				board[i][j] = cell;
			}
		}
	}
	
	//returns true if the last row in the board is not empty
	public abstract boolean isGameOver();
	
	//returns the score and gets incremented when a cell is cleared
	public abstract int getScore();

	/**
	 * Advances the animation one step.
	 */
	public abstract void nextAnimationStep();

	/**
	 * Adjust the board state according to the current board state and the selected
	 * cell.
	 * 
	 * @param rowIndex
	 * @param colIndex
	 */
	public abstract void processCell(int rowIndex, int colIndex);
}