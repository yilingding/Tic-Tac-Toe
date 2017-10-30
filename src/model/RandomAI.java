package model;

import java.awt.Point;
import java.util.Random;

/*+----------------------------------------------------------------------
||
||  public class RandomAI implements TicTacToeStrategy
||
||         Author:  Yiling Ding
||
||        Purpose:  This class makes the random move for the computer
||                 player during the game. 
||
++-----------------------------------------------------------------------*/
/**
 * This strategy selects the first available move at random. It is easy to beat
 * 
 * @throws IGotNowhereToGoException
 *             whenever asked for a move that is impossible to deliver
 * 
 * @author mercer
 */

// There is an intentional compile time error. Implement this interface
public class RandomAI implements TicTacToeStrategy {

	private static Random generator;

	/*
	 * building the constructor for the class
	 */
	public RandomAI() {
		generator = new Random();
	}
	/*---------------------------------------------------------------------
	|  public Point desiredMove(TicTacToeGame theGame)
	|
	|  Purpose:  make the random move on the game board 
	|           
	|
	|  Parameters:
	|      TicTacToeGame theGame
	|         
	|
	|  Returns:  Point --the point on the board 
	*-------------------------------------------------------------------*/

	public Point desiredMove(TicTacToeGame theGame) {
		return randomSelection(theGame);
	}
	/*---------------------------------------------------------------------
	|  private Point randomSelection(TicTacToeGame theGame)
	|
	|  Purpose: random select a point on the game board and give 
	|           the corresponding warning message. 
	|
	|  Parameters:
	|      TicTacToeGame theGame
	|         
	|
	|  Returns:  Point --the point on the board 
	*-------------------------------------------------------------------*/

	private Point randomSelection(TicTacToeGame theGame) {
		boolean set = false;
		while (!set) {
			if (theGame.maxMovesRemaining() == 0)
				throw new IGotNowhereToGoException(" -- Hey there programmer, the board is filled");
			// Otherwise, try to randomly find an open spot
			int row = generator.nextInt(theGame.size());
			int col = generator.nextInt(theGame.size());
			if (theGame.available(row, col)) {
				set = true;
				return new Point(row, col);
			}
		}
		return null;
	}
}
