package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.OurObserver;
import model.ComputerPlayer;
import model.TicTacToeGame;

/*+----------------------------------------------------------------------
||
||  public class GraphicView extends JPanel implements OurObserver, MouseListener
||
||         Author:  Yiling Ding
||
||        Purpose:  This class creates the GraphicView layout for the game,
||                 it basically has the same function as the ButtonView
|| 				   and TextView, it will responds as long as the player clicks. 
||
++-----------------------------------------------------------------------*/
public class GraphicView extends JPanel implements OurObserver, MouseListener {

	private TicTacToeGame theGame;
	public int width = 300;
	public int height = 360;
	public char[][] graph = new char[3][3];
	private ComputerPlayer computerPlayer;
	/*
	 * building the constructor for the GraphicView constructor
	 */
	public GraphicView(TicTacToeGame TicTacToeGame, int width, int height) {
		theGame = TicTacToeGame;
		this.height = height;
		this.width = width;
		computerPlayer = theGame.getComputerPlayer();
		this.addMouseListener(this);

	}
	/*---------------------------------------------------------------------
	| public void paintComponent(Graphics g)
	|
	|  Purpose:  for redraw the graph
	|           
	|
	|  Parameters:
	|     the Graphics g
	|         
	|
	|  Returns:  none
	*-------------------------------------------------------------------*/
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		if (theGame.maxMovesRemaining() == theGame.size() * theGame.size()) {
			resetGraph(g2);
		}

		if (!theGame.stillRunning()) {
			if (theGame.didWin('X')) {
				updateField(g2);
				Font aFont = new Font("SansSerif", Font.BOLD, 32);
				g2.setFont(aFont);
				g2.setPaint(Color.WHITE);
				g2.drawString("X wins", 100, 100);
				closeGraph();

			} else if (theGame.tied()) {
				updateField(g2);
				Font aFont = new Font("SansSerif", Font.BOLD, 32);
				g2.setFont(aFont);
				g2.setPaint(Color.WHITE);
				g2.drawString("Tied", 100, 100);
				closeGraph();

			} else if (theGame.didWin('O')) {
				updateField(g2);
				Font aFont = new Font("SansSerif", Font.BOLD, 32);
				g2.setFont(aFont);
				g2.setPaint(Color.WHITE);
				g2.drawString("O wins!", 100, 100);
				closeGraph();
			}
		} else {
			updateField(g2);

		}
	}
	/*---------------------------------------------------------------------
	| private void updateField()
	|
	|  Purpose: Update the palyers' status on the graphAreaView 
	|           
	|
	|  Parameters:
	|      Graphics g
	|         
	|
	|  Returns:  none
	*-------------------------------------------------------------------*/
	private void updateField(Graphics g) {
		// TODO Auto-generated method stub
		char[][] temp = theGame.getTicTacToeBoard();
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(Color.CYAN);
		g2.fillRect(0, 0, 300, 360);
		g2.setPaint(Color.BLACK);
		g2.draw(new Line2D.Double(0, 100, 300, 100));
		g2.draw(new Line2D.Double(0, 200, 300, 200));
		g2.draw(new Line2D.Double(100, 0, 100, 360));
		g2.draw(new Line2D.Double(200, 0, 200, 360));
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[i].length; j++) {
				String text = "" + temp[i][j];
				if (text.equals("X") || text.equals("O")) {
					graph[i][j] = 'o';
					if (text.equals("X")) {
						Font aFont = new Font("SansSerif", Font.BOLD, 48);
						g2.setFont(aFont);
						g2.setPaint(Color.RED);
						g2.drawString("X", 100 * j + 30, 100 * (i + 1) - 25);
						// g2.drawString("X", 100*i+40, 100*(j+1)-40);
					} else {
						Font aFont = new Font("SansSerif", Font.BOLD, 48);
						g2.setFont(aFont);
						g2.setPaint(Color.BLUE);
						g2.drawString("O", 100 * j + 30, 100 * (i + 1) - 25);
						// g2.drawString("0", 100*i+40, 100*(j+1)-40);
					}
				}
			}
		}
	}
	/*---------------------------------------------------------------------
	| private void closeGraph()
	|
	|  Purpose: lock the graph 
	|           
	|
	|  Parameters:
	|     None
	|         
	|
	|  Returns:  none
	*-------------------------------------------------------------------*/
	private void closeGraph() {
		for (int i = 0; i < 3; i++) {
			for (int k = 0; k < 3; k++) {
				graph[i][k] = 'o';
			}
		}
	}
	/*---------------------------------------------------------------------
	| private void resetGraph(Graphics g)
	|
	|  Purpose: reset the graph
	|           
	|
	|  Parameters:
	|      Graphics g
	|         
	|
	|  Returns:  none
	*-------------------------------------------------------------------*/
	private void resetGraph(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// TODO Auto-generated method stub
		g2.setPaint(Color.CYAN);
		g2.fillRect(0, 0, 300, 360);
		g2.setPaint(Color.BLACK);
		g2.draw(new Line2D.Double(0, 100, 300, 100));
		g2.draw(new Line2D.Double(0, 200, 300, 200));
		g2.draw(new Line2D.Double(100, 0, 100, 360));
		g2.draw(new Line2D.Double(200, 0, 200, 360));

		for (int i = 0; i < 3; i++) {
			for (int k = 0; k < 3; k++) {
				graph[i][k] = ' ';
			}
		}
	}
	/*---------------------------------------------------------------------
	| public void mouseClicked(MouseEvent e) 
	|
	|  Purpose: update the graph as long as click something 
	|           
	|
	|  Parameters:
	|     MouseEvent e
	|         
	|
	|  Returns:  none
	*-------------------------------------------------------------------*/
	@Override
	public void mouseClicked(MouseEvent e) {

		int y = e.getX() / 100;
		int x = e.getY() / 100;
		if (x == 3) {
			x = 2;
		}
		if (y == 3) {
			y = 2;
		}

		if (graph[x][y] != 'o') {
			theGame.choose(x, y);
			repaint();
		}

		if (theGame.didWin('X')) {
			repaint();
		} else if (theGame.tied()) {
			repaint();
		} else {
			// If the game is not over, let the computer player choose
			// This algorithm assumes the computer player always
			// goes after the human player and is represented by 'O', not
			// 'X'
			Point play = computerPlayer.desiredMove(theGame);
			theGame.choose(play.x, play.y);
			repaint();
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	/*---------------------------------------------------------------------
	| public void update()
	|
	|  Purpose: update the game status on the TextAreaView. 
	|           
	|
	|  Parameters:
	|      None
	|         
	|
	|  Returns:  none
	*-------------------------------------------------------------------*/
	@Override
	public void update() {

		repaint();

	}

	

}
