import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TylerConnectFour extends JFrame implements ActionListener {

	final static int NUM_OF_ROWS = 6;
	final static int NUM_OF_COLUMNS = 7;
	final static String EMPTY_VALUE = "e";
	final static String X_VALUE = "x";
	final static String O_VALUE = "o";
	static String[][] grid = new String[NUM_OF_ROWS][NUM_OF_COLUMNS];// Grid to be used behind the scenes
	static int menuChosen = 0;
	static String playerSymbol;

	// Start panel components
	JPanel menuPanel = new JPanel();
	JLabel gameName = new JLabel("Connect Four", JLabel.CENTER);
	JButton pvpButton = new JButton("Player vs. Player");
	JButton pvcButton = new JButton("Player vs. Computer");
	JButton instructionsButton = new JButton("Instructions");

	// Instructions panel components
	JPanel instructionsPanel = new JPanel();
	JButton returnButton = new JButton("Return to menu");
	JLabel instructions = new JLabel("Connect Four is a two-player connection game in which the players first choose a color and then ");
	JLabel instructions1 = new JLabel("take turns dropping colored discs into the grid. The pieces fall straight down, occupying the ");
	JLabel instructions2 = new JLabel("next available space within the column. The objective of the game is to connect four of one's ");
	JLabel instructions3 = new JLabel("own discs of the same color next to each other vertically, horizontally, or diagonally ");
	JLabel instructions4 = new JLabel("before your opponent.");

	// Game panel components
	JPanel gamePanel = new JPanel();
	JLabel[][] GUIGrid = new JLabel[NUM_OF_ROWS][NUM_OF_COLUMNS];// Grid to be displayed on GUI

	JPanel gameplayPanel = new JPanel(new FlowLayout());
	JPanel player1Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel player1Wins = new JLabel("Player 1");
	JPanel player2Panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	JLabel player2Wins = new JLabel("Player 2");
	JLabel turnIndicator = new JLabel("Player 1 turn!");
	JPanel userButtons = new JPanel(new FlowLayout());
	ArrayList<JButton> columnButtons = new ArrayList<JButton>();

	JPanel endGamePanel = new JPanel(new FlowLayout());
	JLabel endGameMessage = new JLabel("end");

	static boolean playerTurn = true;
	static boolean doneTurn = false;
	static int columnChosen = 0;
	static int rowPosition = 5;

	public static void main(String[] args) throws IOException {
		TylerConnectFour frame = new TylerConnectFour(grid); // Initializes the GUI frame
	}

	public TylerConnectFour(String[][] grid) { // Constructor Class
		setTitle("Connect Four");
		setSize(600, 400);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		// Adds components to the startPanel
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
		menuPanel.add(gameName);
		pvpButton.addActionListener(this);
		menuPanel.add(pvpButton);
		pvcButton.addActionListener(this);
		menuPanel.add(pvcButton);
		instructionsButton.addActionListener(this);
		menuPanel.add(instructionsButton);

		// Adds components to the instructionsPanel
		instructionsPanel.setLayout(new BoxLayout(instructionsPanel, BoxLayout.Y_AXIS));
		instructionsPanel.add(instructions);
		instructionsPanel.add(instructions1);
		instructionsPanel.add(instructions2);
		instructionsPanel.add(instructions3);
		instructionsPanel.add(instructions4);
		returnButton.addActionListener(this);
		instructionsPanel.add(returnButton);
		

		// Adds components to the gamePanel
		player1Panel.add(player1Wins);
		player2Panel.add(player2Wins);
		gamePanel.setLayout(new GridLayout(NUM_OF_ROWS, NUM_OF_COLUMNS)); // Creates a gridlayout with 7
																			// rowPositions and 8 columnChosens
		for (int i = 0; i < NUM_OF_ROWS; i++) { // Assigns all grid placements to have e
			for (int t = 0; t < NUM_OF_COLUMNS; t++) {
				grid[i][t] = EMPTY_VALUE;
				GUIGrid[i][t] = new JLabel(EMPTY_VALUE);
				gamePanel.add(GUIGrid[i][t]);
			}
		}

		gameplayPanel.setLayout(new FlowLayout());
		gameplayPanel.add(turnIndicator);
		initializeColumnButtons();

		// End of game Panel
		endGamePanel.add(endGameMessage);

		// Adds all of the panels to the frame
		add(menuPanel);
		add(instructionsPanel, BorderLayout.CENTER);
		add(player1Panel);
		add(player2Panel);
		add(gamePanel);
		add(gameplayPanel);
		add(endGamePanel);

		instructionsPanel.setVisible(false);
		gamePanel.setVisible(false);
		gameplayPanel.setVisible(false);
		endGamePanel.setVisible(false);
		player1Panel.setVisible(false);
		player2Panel.setVisible(false);
		setVisible(true);
	}

	private void initializeColumnButtons() {

		for (int i = 0; i < NUM_OF_COLUMNS; i++) {
			String buttonTitle = Integer.toString(i + 1);

			JButton columnButton = new JButton(buttonTitle);
			columnButtons.add(columnButton);
			columnButton.addActionListener(this);
			gameplayPanel.add(columnButton);
		}

	}

	public void actionPerformed(ActionEvent selection) {
		// Menu buttons actions
		if (selection.getSource() == pvpButton || selection.getSource() == pvcButton) {
			menuChosen = 0;
			panelVisibility();
		} else if (selection.getSource() == instructionsButton) {
			menuChosen = 1;
			panelVisibility();
		} else if (selection.getSource() == returnButton) {
			menuChosen = 2;
			panelVisibility();
		}

		// Player vs player background code
		int index = columnButtons.indexOf(selection.getSource());
		if (index != -1) { // If not in an arraylist the index will return -1

			JButton selectedButton = columnButtons.get(index);
//			String selectedButtonString = selectedButton.getText();
//			int selectdButtonToInt = Integer.parseInt(selectedButtonString);
			columnChosen = (Integer.parseInt(selectedButton.getText()) - 1);

			pvpInputAction();
			inputButtonHide();
			findPlayerTokens(grid, playerSymbol);
		}

//		if (selection.getSource() == ) {
//			columnChosen = ;
//			
//		} else if (selection.getSource() == userInput2) {
//			columnChosen = 1;
//			pvpInputAction();
//			inputButtonHide();
//			findPlayerTokens(grid, playerSymbol);
//		} else if (selection.getSource() == userInput3) {
//			columnChosen = 2;
//			pvpInputAction();
//			inputButtonHide();
//			findPlayerTokens(grid, playerSymbol);
//		} else if (selection.getSource() == userInput4) {
//			columnChosen = 3;
//			pvpInputAction();
//			inputButtonHide();
//			findPlayerTokens(grid, playerSymbol);
//		} else if (selection.getSource() == userInput5) {
//			columnChosen = 4;
//			pvpInputAction();
//			inputButtonHide();
//			findPlayerTokens(grid, playerSymbol);
//		} else if (selection.getSource() == userInput6) {
//			columnChosen = 5;
//			pvpInputAction();
//			inputButtonHide();
//			findPlayerTokens(grid, playerSymbol);
//		} else if (selection.getSource() == userInput7) {
//			columnChosen = 6;
//			pvpInputAction();
//			inputButtonHide();
//			findPlayerTokens(grid, playerSymbol);
//		}

	}// End of Action Listener

	// Sets what panels will shown in the screen for the menu buttons
	public void panelVisibility() {
		if (menuChosen == 0) {
			instructionsPanel.setVisible(false);
			gamePanel.setVisible(true);
			gameplayPanel.setVisible(true);
			player1Panel.setVisible(true);
			player2Panel.setVisible(true);
			menuPanel.setVisible(false);
		} else if (menuChosen == 1) {
			instructionsPanel.setVisible(true);
			gamePanel.setVisible(false);
			gameplayPanel.setVisible(false);
			player1Panel.setVisible(false);
			player2Panel.setVisible(false);
			menuPanel.setVisible(false);
		} else if (menuChosen == 2) {
			instructionsPanel.setVisible(false);
			gamePanel.setVisible(false);
			gameplayPanel.setVisible(false);
			player1Panel.setVisible(false);
			player2Panel.setVisible(false);
			menuPanel.setVisible(true);
		}
	}

	// Inputs the selected columnChosen the user places their token
	public void pvpInputAction() {
		doneTurn = false;
		rowPosition = 5;

		do {
			if (grid[rowPosition][columnChosen] == EMPTY_VALUE) {
				// Changes the GUI grid version to o with respective color
				GUIGrid[rowPosition][columnChosen].setText(O_VALUE);

				if (playerTurn == true) {
					GUIGrid[rowPosition][columnChosen].setForeground(Color.red); // Sets the token to be red
					grid[rowPosition][columnChosen] = X_VALUE;
					playerSymbol = X_VALUE;
					playerTurn = false;
					turnIndicator.setText("Player 2 turn!");
				} else {
					GUIGrid[rowPosition][columnChosen].setForeground(Color.blue);// Sets the token to be blue
					grid[rowPosition][columnChosen] = O_VALUE;
					playerSymbol = O_VALUE;
					playerTurn = true;
					turnIndicator.setText("Player 1 turn!");
				}
				doneTurn = true;
			} // End of outer if statement
			rowPosition = rowPosition - 1;
		} while (doneTurn == false);

//		for (int i = 0; i < NUM_OF_ROWS; i++) { // Gets each row
//			System.out.println(" ");
//			for (int t = 0; t < NUM_OF_COLUMNS; t++) {
//				System.out.print(grid[i][t] + " "); // Prints each value in the column
//			}
//		}
//		System.out.println(" "); 
	}

	// Hides the button to add to columnChosen if it is full of tokens
	public void inputButtonHide() {
		
		if (grid[0][columnChosen] != EMPTY_VALUE) {
			if (columnChosen < columnButtons.size()) {
				(columnButtons.get(columnChosen)).setVisible(false);
			}
//			if (columnChosen == 0) {
//				userInput1.setVisible(false);
//			} else if (columnChosen == 1) {
//				userInput2.setVisible(false);
//			} else if (columnChosen == 2) {
//				userInput3.setVisible(false);
//			} else if (columnChosen == 3) {
//				userInput4.setVisible(false);
//			} else if (columnChosen == 4) {
//				userInput5.setVisible(false);
//			} else if (columnChosen == 5) {
//				userInput6.setVisible(false);
//			} else if (columnChosen == 6) {
//				userInput7.setVisible(false);
//			}
		}
	}

	// Finds the players tokens and checks to see if connect four has been made
	public void findPlayerTokens(String[][] grid, String playerSymbol) {
		boolean winnerCheck = false;

		// Checks to see if first letter is in the grid
		// if (grid[rowPosition][columnChosen].equalsIgnoreCase(playerSymbol)) {
		winnerCheck = true; // If finds the first letter in the grid sets true
		winnerCheck = checkWin(grid, playerSymbol);
		// }

		if (winnerCheck == true && playerSymbol == X_VALUE) {
			endGameMessage.setText("Player 1 wins!");
			endGamePanel.setVisible(true);
		} else if (winnerCheck == true && playerSymbol == O_VALUE) {
			endGameMessage.setText("Player 2 wins!");
			endGamePanel.setVisible(true);
		}

//		if (grid[0][0] != EMPTY_VALUE && grid[0][1] != EMPTY_VALUE && grid[0][2] != EMPTY_VALUE
//				&& grid[0][3] != EMPTY_VALUE && grid[0][4] != EMPTY_VALUE && grid[0][5] != EMPTY_VALUE
//				&& grid[0][6] != EMPTY_VALUE && wordCheck == false) {

		if (topRowIsNotEmpty() && winnerCheck == false) {
			endGameMessage.setText("It's a tie!");
			endGamePanel.setVisible(true);
		}
	}

	//Method that checks to see if the top row has tokens or not
	private boolean topRowIsNotEmpty( ) {
		for (int i = 0; i < NUM_OF_COLUMNS; i++) {
			if (grid[0][i] == EMPTY_VALUE) {
				return false;
			}
		}
		return true;
	}
	
	// Checks all around the token to see the direction if 4 in a rowPosition
	public boolean checkWin(String[][] grid, String playerSymbol) {

		int column = columnChosen;
		int row = rowPosition + 1;

		Boolean checkRight = true;
		Boolean checkLeft = true;
		Boolean checkUp = true;
		Boolean checkDown = true;

		// Checks to see if the letters beside first letter will go out of bounds of
		// array index
		if (column + 1 == NUM_OF_COLUMNS) {
			checkRight = false; // Checks if the letter to right exists
		}

		if (column - 1 < 0) {
			checkLeft = false; // Checks if the letter to left exists
		}

		if (row + 1 == NUM_OF_ROWS) {
			checkDown = false; // Checks if the letter below exists
		}

		if (row - 1 < 0) {
			checkUp = false; // Checks if the letter above exists
		}

		int directionX = 0;
		int directionY = 0;

		// Checks to right of firstChar
		if (checkRight == true && grid[row][column + 1].equalsIgnoreCase(playerSymbol)) {
			directionX = 1;
			column += directionX; // Sets the grid to check 1 column to the right
			if (nextLetterCheck(directionY, directionX, row, column, grid, playerSymbol) == true) {
				return true;
			}
		}

		// Checks to left of firstChar
		if (checkLeft == true && grid[row][column - 1].equalsIgnoreCase(playerSymbol)) {
			directionX = -1;
			column += directionX; // Sets the grid to check 1 column to the left
			if (nextLetterCheck(directionY, directionX, row, column, grid, playerSymbol) == true) {
				return true;
			}
		}

		// Checks below firstChar
		if (checkDown == true && grid[row + 1][column].equalsIgnoreCase(playerSymbol)) {
			directionY = 1;
			row += directionY; // Sets the grid to check 1 row down
			if (nextLetterCheck(directionY, directionX, row, column, grid, playerSymbol) == true) {
				return true;
			}
		}

		// Checks above firstChar
		if (checkUp == true && grid[row - 1][column].equalsIgnoreCase(playerSymbol)) {
			directionY = -1;
			row += directionY; // Sets the grid to check 1 row up
			if (nextLetterCheck(directionY, directionX, row, column, grid, playerSymbol) == true) {
				return true;
			}
		}

		// Checks diagonally (upright)
		if (checkUp == true && checkRight == true && grid[row - 1][column + 1].equalsIgnoreCase(playerSymbol)) {
			directionY = -1; // Increments x and y to search in up right direction
			directionX = 1;
			row += directionY;
			column += directionX;
			if (nextLetterCheck(directionY, directionX, row, column, grid, playerSymbol) == true) {
				return true;
			}
		}

		// Checks diagonally(up-left)
		if (checkUp == true && checkLeft == true && grid[row - 1][column - 1].equalsIgnoreCase(playerSymbol)) {
			directionY = -1; // Increments x and y to search in up left direction
			directionX = -1;
			row += directionY;
			column += directionX;
			if (nextLetterCheck(directionY, directionX, row, column, grid, playerSymbol) == true) {
				return true;
			}
		}

		// Checks diagonally (down-right)
		if (checkDown == true && checkRight == true && grid[row + 1][column + 1].equalsIgnoreCase(playerSymbol)) {
			directionY = 1;// Increments x and y to search in down right direction
			directionX = 1;
			row += directionY;
			column += directionX;
			if (nextLetterCheck(directionY, directionX, row, column, grid, playerSymbol) == true) {
				return true;
			}
		}

		// Checks diagonally (down-left)
		if (checkDown == true && checkLeft == true && grid[row + 1][column - 1].equalsIgnoreCase(playerSymbol)) {
			directionY = 1;
			directionX = -1;
			row += directionY; // Increments x and y to search in down left direction
			column += directionX;
			if (nextLetterCheck(directionY, directionX, row, column, grid, playerSymbol) == true) {
				return true;
			}
		}

		return false;
	}

	// Method that checks if there is 4 in a row
	public boolean nextLetterCheck(int directionY, int directionX, int row, int column, String[][] grid,
			String playerSymbol) {

		for (int k = 1; k < 4; k++) {
			
			/*if (column + 1 == NUM_OF_COLUMNS || column - 1 < 0|| row + 1 == NUM_OF_ROWS || row - 1 < 0) {
				return false;
			} else */
			
			if (!playerSymbol.equalsIgnoreCase(grid[row][column])) { // Checks if next characters matches
																		// word
				return false;
			}
			row += directionY; // Sets grid to check the next character
			column += directionX;
		}
		return true; // If all of the characters match return true
	}
}
