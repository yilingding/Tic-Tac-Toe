package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.OurObserver;
import model.ComputerPlayer;
import model.TicTacToeGame;
//import view.ButtonView.ButtonListener;

/*+----------------------------------------------------------------------
 ||
 ||  public class TextAreaView extends JPanel implements OurObserver
 ||
 ||         Author:  Yiling Ding
 ||
 ||        Purpose:  This class creates the TextAreaView layout for the game,
 ||                 it basically has the same function as the ButtonView
 ||
 ++-----------------------------------------------------------------------*/
public class TextAreaView extends JPanel implements OurObserver {

	private TicTacToeGame theGame;
	public JTextField row;
	public JTextField column;
	private JButton stateButton = new JButton("Make the move");
	JTextField textRow = new JTextField(2);
	JTextField textColumn = new JTextField(2);
	JTextArea area = new JTextArea();
	// private ComputerPlayer computerPlayer;
	private char[][] text = new char[3][6];
	private ComputerPlayer computerPlayer;
	private int height, width;

	/*
	 * building the constructor for the TextAreaView constructor
	 */
	public TextAreaView(TicTacToeGame TicTacToeGame, int width, int height) {
		theGame = TicTacToeGame;
		this.height = height;
		this.width = width;
		computerPlayer = theGame.getComputerPlayer();
		initializeAreaPanel(width, height);
	}

	/*---------------------------------------------------------------------
	| private void initializeAreaPanel(int width, int height)
	|
	|  Purpose:  initialize the TextArea panel
	|           
	|
	|  Parameters:
	|      int width, int height of the game board 
	|         
	|
	|  Returns:  none
	*-------------------------------------------------------------------*/
	private void initializeAreaPanel(int width, int height) {
		// TODO Auto-generated method stub
		row = new JTextField("");
		column = new JTextField("");
		setBackground(Color.CYAN);
		setLayout(new FlowLayout());
		JLabel rowLabel = new JLabel("row");
		add(rowLabel);

		add(textRow);
		JLabel columnLabel = new JLabel("Column");
		add(columnLabel);
		add(textColumn);
		stateButton.setFont(new Font("Arial", Font.BOLD, 18));
		stateButton.setBackground(Color.WHITE);

		add(stateButton);
		ButtonListener buttonListener = new ButtonListener();
		stateButton.addActionListener(buttonListener);
		area.setPreferredSize(new Dimension(160, 180));
		Font myFont = new Font("Courier", Font.PLAIN, 45);
		area.setFont(myFont);
		String output = "";
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 6; j++) {
				if (j % 2 == 0) {
					text[i][j] = '_';
				} else if (j == 5) {
					text[i][j] = '\n';
				} else {
					text[i][j] = ' ';
				}
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 6; j++) {
				output = output + text[i][j];
			}
		}
		area.setText(output);
		area.setEditable(false);
		add(area, BorderLayout.CENTER);
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

	public void update() {
		if (theGame.maxMovesRemaining() == theGame.size() * theGame.size())
			resetArea();

		if (!theGame.stillRunning()) {
			if (theGame.didWin('X')) {
				stateButton.setText("X wins");
				textRow.setText("");
				textColumn.setText("");
				updateField();
			}
			if (theGame.tied()) {
				stateButton.setText("Tied");
				textRow.setText("");
				textColumn.setText("");
				updateField();
			}
			if (theGame.didWin('O')) {
				stateButton.setText("O wins");
				textRow.setText("");
				textColumn.setText("");
				updateField();
			}
		} else {
			updateField();
			textRow.setText("");
			textColumn.setText("");
			stateButton.setText("Make the move");
		}
	}

	/*---------------------------------------------------------------------
	| private void resetArea()
	|
	|  Purpose: Reset the content of the TextArea, all point to _
	|           
	|
	|  Parameters:
	|      None
	|         
	|
	|  Returns:  none
	*-------------------------------------------------------------------*/
	private void resetArea() {
		//System.out.println("resting ");
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 6; j++) {
				if (j % 2 == 0) {
					text[i][j] = '_';
				} else if (j == 5) {
					text[i][j] = '\n';
				} else {
					text[i][j] = ' ';
				}
			}
		}
	}

	/*---------------------------------------------------------------------
	| private void updateField()
	|
	|  Purpose: Update the palyers' status on the textAreaView 
	|           
	|
	|  Parameters:
	|      None
	|         
	|
	|  Returns:  none
	*-------------------------------------------------------------------*/
	private void updateField() {
		char[][] temp = theGame.getTicTacToeBoard();
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[i].length; j++) {
				char text2 = temp[i][j];
				if (text2 == 'X' || text2 == 'O') {
					text[i][j * 2] = text2;
				}
			}
		}

		String output = "";
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 6; j++) {
				output = output + text[i][j];
			}
		}
		area.setText(output);
	}

	/*---------------------------------------------------------------------
	| private class ButtonListener implements ActionListener
	|
	|  Purpose: Do the corresponding action according to the action 
	|           of the button and both players' status and also 
	|           give corresponding message. 
	|  Parameters:
	|      None
	|         
	|
	|  Returns:  none
	*-------------------------------------------------------------------*/
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String value1 = textRow.getText();
			String value2 = textColumn.getText();
			int notInput = 0;
			if (value1.equals("") || value2.equals("")) {
				notInput = 1;
				JOptionPane.showMessageDialog(null, "Selection not available");
			}
			if (notInput == 0) {
				int rowNumber = Integer.parseInt(value1);
				int columnNumber = Integer.parseInt(value2);

				if (rowNumber > 2 || rowNumber < 0) {
					JOptionPane.showMessageDialog(null, "Selection not available");
					textRow.setText("");
					textColumn.setText("");
				} else if (columnNumber > 2 || columnNumber < 0) {
					JOptionPane.showMessageDialog(null, "Selection not available");
					textRow.setText("");
					textColumn.setText("");
				} else {
					if (text[rowNumber][columnNumber * 2] == '_') {
						theGame.choose(rowNumber, columnNumber);

						if (theGame.tied()) {
							stateButton.setText("Tied");
							updateField();
						} else if (theGame.didWin('X')) {
							stateButton.setText("X wins");
							updateField();
						} else {
							Point play = computerPlayer.desiredMove(theGame);
							theGame.choose(play.x, play.y);
							if (theGame.didWin('O')) {
								stateButton.setText("O wins");
								updateField();
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "Selection not available");
						textRow.setText("");
						textColumn.setText("");
					}
				}
			}

		}

	}

}