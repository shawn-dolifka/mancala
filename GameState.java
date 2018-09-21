package Mancala;

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

//import java.util.ArrayList;

/*This class is for checking the game state to determine if the game should keep
 * running or not. It also contains useful functions to operate the game. If you need to add
 * in functionality, like the rules, updating the game board and the AI, make a new class for
 * it, then create that class here. There will be a function called "Update Board" where all the
 * game changes from movements can be implemented
 */

public class GameState 
{

	/*=================================================================
    Game Variables
    =================================================================*/
	private boolean runGame;
	private boolean pieRule;

	//Scanner used for Terminal implementation. Kept for posterity.
	//Scanner reader = new Scanner(System.in);

	/*=================================================================
    The Game's Board
    =================================================================*/

	//Board for both players
	int[] player1Board;
	int[] player2Board;
	int[] player1BoardCopy;
	int[] player2BoardCopy;

	/*=================================================================
    Game Accessor Methods
    =================================================================*/
	//Return state of runGame boolean
	public boolean getRunGame()
	{
		return runGame;
	}
	//Return score of Player 1
	public int getP1Score()
	{
		return player1Board[player1Board.length-1];
	}

	//Return score of Player 2
	public int getP2Score()
	{
		return player2Board[player2Board.length-1];
	}

	/*=================================================================
    Function to build the board
    =================================================================*/

	//create specified number of houses on each side
	public void createHouses(int houses, int seeds, boolean randomizeSeed) {
		player1Board = new int[houses+1];
		player2Board = new int[houses+1];
		player1BoardCopy = new int[houses+1];
		player2BoardCopy = new int[houses+1];
		
		int maxSeeds = houses*seeds;

		int tempSeeds = 0;
		if(randomizeSeed && seeds != 1) {
			for(int i=0; i<houses; i++)
			{
				player1Board[i]+=1;
				player2Board[i]+=1;
			}
			maxSeeds-=houses;
			for(int j=maxSeeds; j>0; j--)
			{
				Random rand = new Random();
				tempSeeds=rand.nextInt(houses);
				player1Board[tempSeeds]+=1;
				player2Board[tempSeeds]+=1;
				
			}
			}
		else {
			for (int i = 0; i < player1Board.length-1; i++) {
				player1Board[i]=seeds;
				player2Board[i]=seeds;
			}
		}
		player1Board[player1Board.length-1] = 0;
		player2Board[player2Board.length-1] = 0;
	}

		//This function is for seeing if the game loop should be ended or not
		public GameState()
		{
			//Boolean to keep the game running
			runGame = true;
			pieRule = true;

		}


		
		//Checks the current state of the game and determines whether game continues
		public void checkState()
		{
			int boardOneSum = 0;
			int boardTwoSum = 0;
			for(int i = 0; i < (player1Board.length-1); i++)
			{
				boardOneSum += player1Board[i];
				boardTwoSum += player2Board[i];
			}

			/*The rules about capturing all the seeds from one side if a player's houses on
			their side are all empty needs to be implemented*/
			if ((boardOneSum == 0))
			{
				player2Board[player2Board.length-1]+= boardTwoSum;
				for(int i = 0; i < (player2Board.length-1); i++)
				{
					player2Board[i] = 0;
				}

				runGame = false;
			}else if(boardTwoSum == 0) {

				player1Board[player1Board.length-1]+= boardOneSum;
				for(int i = 0; i < (player1Board.length-1); i++)
				{
					player1Board[i] = 0;
				}
				runGame = false;
			}
		}


	/*=================================================================
    Check Valid Moves
    =================================================================*/
	//Checks to ensure hut/move selected is valid (MUST SELCT HUT WHERE BEADS >0)
	public boolean validMove(int selectPosition, boolean isPlayer2)
	{
		if((selectPosition > player1Board.length-1)|| (selectPosition < 0)) {
			return false;
		}
		if(isPlayer2)
		{
			if(player2Board[selectPosition]!=0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			if(player1Board[selectPosition]!=0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}

	/*=================================================================
    Terminal Player Movement (Kept for posterity
    =================================================================*/
	/*
	//Player Move Method - Takes User Input and Calls moveBeads
	public void playerMove(boolean isPlayer2)
	{
		int selectHut=0;
		int maxInput = player1Board.length-2;
		if(!isPlayer2) {
			System.out.println("Player1: Select a hut 0 - " + maxInput + " to move the beeds from:");
		}else {
			System.out.println("Player2: Select a hut 0 - " + maxInput + " to move the beeds from:");
		}

		selectHut=reader.nextInt();
		moveBeads(selectHut, isPlayer2, false);

	}
	*/

	/*=================================================================
    Move Seeds on board. For the GUI use
    =================================================================*/
	//Moves beads around board from selected Hut
	public boolean moveBeads(int selectHut, boolean isPlayer2, boolean isComputer)
	{
		checkState();
		if(getRunGame()==false) {
			return false;
		}
		//If the computer is playing (AI)
		if(isComputer)
		{
			for(int i=0; i<player1Board.length-1; i++)
			{
				player2BoardCopy[i]=player2Board[i];
				player1BoardCopy[i]=player1Board[i];
			}
			if(isPlayer2)
			{
				getAIMove(player2BoardCopy, player1BoardCopy, isPlayer2);
			}
			else
			{
				getAIMove(player1BoardCopy, player2BoardCopy, !isPlayer2);
			}

			return false;
		}
		else {
		
		if(pieRule && isPlayer2 && selectHut == -1) {
			int[] tempBoard = new int[player1Board.length]; 
			tempBoard = player1Board;
			player1Board = player2Board;
			player2Board = tempBoard;
			pieRule = false;
			return false;
		}
		if(!validMove(selectHut, isPlayer2)) {
			return false;
		}
		
		int movedBeads = 0;
		boolean goAgain = false;
		//Player 1 Move Beads
		if(!isPlayer2)
			{
			movedBeads = player1Board[selectHut];
			player1Board[selectHut]=0;
			
			//If the beads will end EXACTLY on the players scoring hut, then repeat turn
			if(movedBeads+selectHut==player1Board.length-1)
			{
				//Landed in Hut -> Go Again
				//System.out.println("Player1: Go again!");
				goAgain=true;
			}
			/*this can be put in a while loop to make it handle moving more than 2 times the huts 
			also need to exclude placing in opponent score hut*/
			int startingPosition = selectHut +1;
			while(movedBeads>0)
			{
				//Moving Around Player1's Board (Player 1 Turn)
				for(int i = startingPosition; i < player1Board.length; i++)
				{
					//Empty Capture
					if(movedBeads == 1 && player1Board[i]==0 && i != player1Board.length-1)
					{
						player1Board[i]=player1Board[i]+1;	
						movedBeads--;
						player1Board[player1Board.length-1]+=player2Board[player2Board.length-2-i];
						player2Board[player2Board.length-2-i]=0;
						
					}
					//Normal Move
					else if(movedBeads > 0)
					{
						player1Board[i]=player1Board[i]+1;	
						movedBeads--;
					}
				}
				//Moving Around Player2's Board (Player 1 Turn)
				for(int i = 0; i < player2Board.length-1; i++)
				{
					if(movedBeads > 0)
					{
						player2Board[i]=player2Board[i]+1;	
						movedBeads--;
					}
				}
				startingPosition=0;
			}
			checkState();
			if(getRunGame()==false) {
				return false;
			}
			if(goAgain)
				{
					return true;
				}
			return false;
			}
		//Player 2 Move Beads
		else
		{
			goAgain=false;
			movedBeads = player2Board[selectHut];
			player2Board[selectHut]=0;
			//If the beads will end EXACTLY on the players scoring hut, then repeat turn (COMP)
			if(movedBeads+selectHut==player2Board.length-1)
			{
				//Landed in Hut -> Go Again
				//System.out.println("Player2: Go again!");
				goAgain=true;
			}
			/*this can be put in a while loop to make it handle moving more than 2 times the huts 
			also need to exclude placing in opponent score hut*/
			while(movedBeads>0)
			{
				for(int i = 1 + selectHut; i < player2Board.length; i++)
				{
					//Empty Capture
					if(movedBeads == 1 && player2Board[i]==0 && i != player2Board.length-1)
					{
						player2Board[i]=player2Board[i]+1;	
						movedBeads--;
						player2Board[player2Board.length-1]+=player1Board[player1Board.length-2-i];
						player1Board[player1Board.length-2-i]=0;
					}
					//Normal Move
					else if(movedBeads > 0)
					{
						player2Board[i]=player2Board[i]+1;	
						movedBeads--;
					}
				}
				for(int i = 0; i < player1Board.length-1; i++)
				{
					if(movedBeads > 0)
					{
						player1Board[i]=player1Board[i]+1;	
						movedBeads--;
					}
				}
			}
			checkState();
			if(getRunGame()==false) {
				return false;
			}
			if(goAgain)
			{
				return true;
			}
		}
		return false;
		}
	}

	/*=================================================================
    AI Seed Movement
    =================================================================*/
	//AI Move Beads function to test min max functions create temp boards to pass in.
	public ArrayList<int[]> AiMoveBeads(int selectHut, int[] yourBoard, int[] otherBoard)
	{
		int movedBeads = 0;
		boolean goAgain = false;
		//AI Move Beads
		movedBeads = yourBoard[selectHut];
		yourBoard[selectHut]=0;
		
		//If the beads will end EXACTLY on the players scoring hut, then repeat turn
		if(movedBeads+selectHut==yourBoard.length-1)
		{
			//Landed in Hut -> Go Again
			System.out.println("AI: Go again!");
			goAgain=true;
		}
		/*this can be put in a while loop to make it handle moving more than 2 times the huts 
		also need to exclude placing in opponent score hut*/
		int startingPosition = selectHut +1;
		while(movedBeads>0)
		{
			//Moving Around Player1's Board (Player 1 Turn)
			for(int i = startingPosition; i < yourBoard.length; i++)
			{
				//Empty Capture
				if(movedBeads == 1 && yourBoard[i]==0 && i != yourBoard.length-1)
				{
					yourBoard[i]=yourBoard[i]+1;	
					movedBeads--;
					yourBoard[yourBoard.length-1]+=otherBoard[otherBoard.length-2-i];
					otherBoard[otherBoard.length-2-i]=0;
					
				}
				//Normal Move
				else if(movedBeads > 0)
				{
					yourBoard[i]=yourBoard[i]+1;	
					movedBeads--;
				}
			}
			//Moving Around Player2's Board (Player 1 Turn)
			for(int i = 0; i < otherBoard.length-1; i++)
			{
				if(movedBeads > 0)
				{
					otherBoard[i]=otherBoard[i]+1;	
					movedBeads--;
				}
			}
			startingPosition=0;
		}
		if(goAgain)
			{
				//CALL YOUR AI FUNCTION HERE
			}
		
		ArrayList<int[]> aiBoard = new ArrayList<int[]>();
		aiBoard.add(yourBoard);
		aiBoard.add(otherBoard);
		return aiBoard;
	}

	/*=================================================================
    Function to check if AI's move is valid
    =================================================================*/
	//Valid move more AI 
	public boolean AiValidMove(int selectPosition, int[] p1Board)
	{
		if((selectPosition > p1Board.length-1)|| (selectPosition < 0)) {
			return false;
		}
		
		if(p1Board[selectPosition]!=0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/*=================================================================
    Print the board to terminal. Kept for posterity
    =================================================================*/
	/*
	public void printBoard()
	{
		//Console Board Printing System
		for(int i = player2Board.length-2; i >= 0; i--) 
		{
			System.out.print(" " + player2Board[i]);
		}
		System.out.print("\n");
		System.out.print(player2Board[player2Board.length-1] + "           " + player1Board[player1Board.length-1]);
		System.out.print("\n");
		for(int i = 0; i < (player1Board.length-1); i++) 
		{
			System.out.print(" " + player1Board[i]);
		}
		System.out.print("\n");
		
	}
	*/

	/*=================================================================
    Function to get the AI's Move.
    =================================================================*/

	int getAIMove(int[] boardA, int[] boardB, boolean isPlayer2)
	{
		//double maxValuePossibleMove;
		//maxValuePossibleMove=(MAX_Value(0, boardA, boardB));
		//System.out.print(maxValuePossibleMove);

		Random rand = new Random();
		int aiMoveFinal;
		aiMoveFinal=rand.nextInt(player1Board.length-1);
		if(AiValidMove(aiMoveFinal, boardA))
		{
			moveBeads(aiMoveFinal, isPlayer2, false);
		}
		else
		{
			getAIMove(boardA, boardB, isPlayer2);
		}
		return 0;
	}

	/*=================================================================
    Minimax Tree MAX Function
    =================================================================*/
	double MAX_Value (int depth, int[] boardA, int[] boardB)
	{
		if(terminal_Test(depth, boardA, boardB))
		{
			return utility(boardA, boardB);
		}
		double v = -1e6;
		ArrayList<int[]> newSuc = new ArrayList<int[]>();
		newSuc=successors(boardA, boardB);
		for (int i=0; i<(newSuc.size()-1)/2; i+=2)
		{
			double temp=MIN_Value(depth+1, newSuc.get(i+1), newSuc.get(i));
			if(temp>v)
			{
				v = temp;
			}
		}
		return v;
	}

	/*=================================================================
    Minimax Tree MIN Function
    =================================================================*/
	double MIN_Value (int depth, int[] boardA, int[] boardB)
	{
		if(terminal_Test(depth, boardA, boardB))
		{
			return utility(boardA, boardB);
		}
		double v = 1e6;
		ArrayList<int[]> newSuc = new ArrayList<int[]>();
		newSuc=successors(boardA, boardB);
		for (int i=0; i<(newSuc.size()-1)/2; i+=2)
		{
			double temp=MAX_Value(depth+1, newSuc.get(i+1), newSuc.get(i));
			if(temp<v)
			{
				v = temp;
			}
		}
		return v;
	}

	/*=================================================================
    Check if the board is empty
    =================================================================*/
	public boolean boardEmpty(int []boardA, int []boardB)
	{
		int boardCount = 0;
		for(int i = 0; i < (boardA.length-1); i++) 
		{
			boardCount+=boardA[i]+boardB[i];
		}
		if(boardCount>0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	/*=================================================================
    Something
    =================================================================*/
	public boolean terminal_Test(int depth, int []boardA, int []boardB)
	{
		//checks to see if game is over and selected player has one
		if (depth == 4)
		{
			return true;
		}
		if(boardEmpty(boardA, boardB))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}

	/*=================================================================
    Successors returns a list of boards that can be obtained from this given board by
	making an allowed move
    =================================================================*/
	public ArrayList<int[]> successors(int []boardA, int []boardB)
	{
		ArrayList<int[]> sucsrs = new ArrayList<int[]>();
		ArrayList<int[]> sfinal = new ArrayList<int[]>();
		//Gets Possible Moves from this Board
		for(int i=0; i<boardA.length-2; i++)
		{
			if(boardA[i]>0)
			{
				sucsrs=AiMoveBeads(i, boardA, boardB);
				for (int j=0; j<sucsrs.size()-1; j++)
				{
					sfinal.add(sucsrs.get(i));
				}
				sucsrs.clear();
			}
			
		}
		return sfinal;
	}

	/*=================================================================
    Score to base move score by = UTILITY (nodes values of our tree)
    =================================================================*/

	public double utility (int []boardA, int []boardB)
	{
		return boardA[boardA.length-1] - boardB[boardA.length-1];
	}
}