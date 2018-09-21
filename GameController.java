package Mancala;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController
{
    /*=================================================================
    Misc Game Variables
    =================================================================*/

    //Boolean for the turn order
    private boolean isPlayer1Turn = true;

    //Boolean for player or AI. True if human, False if AI
    private boolean p1Human = true, p2Human = true;

    //Variable to select whether pie rule is allowed
    private boolean pieBool = true, pieSkip = false;

    //Count of number of houses
    private int House = 4;

    /*=================================================================
    Game Objects
    =================================================================*/

    //GameState objects holds the game board and functionality
    private GameState running = new GameState();

    /*This is the button for the Pie Rule. It only appears after the first move is made*/
    public Button pieRule;

    /*This is the label that says "Mancala". It will change to declare a winner when
    * the game reaches the end.*/
    public Label titleText;

    //Text field that says "Time" or "T" on bigger boards
    public TextField p1timerText, p2timerText;

    //Timer text fields on the board. Shows time countdown
    public TextField p1timer, p2timer;

    /*=================================================================
    Game Functions
    =================================================================*/

    //Creates a new board with # of houses and seeds passed in
        public void createBoard(int houses, int seeds, boolean random) {
        running.createHouses(houses, seeds, random);
        this.House = houses;
        updateHouses();
    }

    //Change booleans to pick who is human or AI
    public void setPlayers (boolean p1, boolean p2)
    {
        p1Human = p1;
        p2Human = p2;
    }

    public void showWinner()
    {
        if (!running.getRunGame())
        {
            buttonsOff();
            if (running.getP1Score() > running.getP2Score())
            {
                titleText.setText("P1 Wins!");
            }
            else if (running.getP1Score() < running.getP2Score())
            {
                titleText.setText("P2 Wins!");
            }
            else
            {
                titleText.setText("Tie!");
            }

        }
    }

    //Toggles whether the Pie Rule button is visible or not
    private void pieVisible()
    {
        //Make the Pie Rule button visible if Pie Rule is enabled
        if (pieBool && !pieSkip)
        {
            pieRule.setDisable(false);
        }
        else
        {
            pieRule.setDisable(true);
            pieRule.setText("Disabled");
        }
        pieSkip = true;
    }

    //Put the functionality of the Pie Rule here.
    public void doPieRule()
    {
        //Define the actions Pie Rule should do here
        //=========================================//
    	running.moveBeads(-1, true, false);
    	allToggle();
    	updateHouses();
        //=========================================//

        //Leave this here at the end so the button will disappear
        pieSkip = true;
        pieRule.setDisable(true);
        pieRule.setText("Disabled");
    }

    //Function to update the display of the beads in the GUI
    private void updateHouses()
    {
        p1score.setText(Integer.toString(running.player1Board[running.player1Board.length-1]));
        p2score.setText(Integer.toString(running.player2Board[running.player2Board.length-1]));

        p1text1.setText(Integer.toString(running.player1Board[0]));
        p2text1.setText(Integer.toString(running.player2Board[0]));

        p1text2.setText(Integer.toString(running.player1Board[1]));
        p2text2.setText(Integer.toString(running.player2Board[1]));

        p1text3.setText(Integer.toString(running.player1Board[2]));
        p2text3.setText(Integer.toString(running.player2Board[2]));

        p1text4.setText(Integer.toString(running.player1Board[3]));
        p2text4.setText(Integer.toString(running.player2Board[3]));
        if(this.House < 5) {
            return;
        }
        p1text5.setText(Integer.toString(running.player1Board[4]));
        p2text5.setText(Integer.toString(running.player2Board[4]));
        if(this.House < 6) {
            return;
        }
        p1text6.setText(Integer.toString(running.player1Board[5]));
        p2text6.setText(Integer.toString(running.player2Board[5]));
        if(this.House < 7) {
            return;
        }
        p1text7.setText(Integer.toString(running.player1Board[6]));
        p2text7.setText(Integer.toString(running.player2Board[6]));
        if(this.House < 8) {
            return;
        }
        p1text8.setText(Integer.toString(running.player1Board[7]));
        p2text8.setText(Integer.toString(running.player2Board[7]));
        if(this.House < 9) {
            return;
        }
        p1text9.setText(Integer.toString(running.player1Board[8]));
        p2text9.setText(Integer.toString(running.player2Board[8]));
    }

    //Turn off all players' buttons to declare a winner
    public void buttonsOff()
    {
        //Disable all of Player 1's Buttons
        p1house1.setDisable(true);
        p1house2.setDisable(true);
        p1house3.setDisable(true);
        p1house4.setDisable(true);
        if (House < 5){return;}
        p1house5.setDisable(true);
        if (House < 6){return;}
        p1house6.setDisable(true);
        if (House < 7){return;}
        p1house7.setDisable(true);
        if (House < 8){return;}
        p1house8.setDisable(true);
        if (House < 9){return;}
        p1house9.setDisable(true);

        //Disable all of player 2's buttons
        p2house1.setDisable(true);
        p2house2.setDisable(true);
        p2house3.setDisable(true);
        p2house4.setDisable(true);
        if (House < 5){return;}
        p2house5.setDisable(true);
        if (House < 6){return;}
        p2house6.setDisable(true);
        if (House < 7){return;}
        p2house7.setDisable(true);
        if (House < 8){return;}
        p2house8.setDisable(true);
        if (House < 9){return;}
        p2house9.setDisable(true);

    }

    /*Use this function to flip the buttons. Only all this function to flip turns
    * in the button functions. Do not call the individual buttonToggleP1/2 functions
    * within a button function*/
    public void allToggle()
    {
        buttonToggleP2(isPlayer1Turn);
        buttonToggleP1(isPlayer1Turn);
    }

    //This is only for using allToggle() function. Don't call it elsewhere
    private void buttonToggleP1(boolean turn)
    {
        //Turn == true means that it's Player 1's turn
        if (turn)
        {
            //Enable all of Player 1's Buttons
            p1house1.setDisable(false);
            p1house2.setDisable(false);
            p1house3.setDisable(false);
            p1house4.setDisable(false);
            if (House < 5){isPlayer1Turn = !isPlayer1Turn; return;}
            p1house5.setDisable(false);
            if (House < 6){isPlayer1Turn = !isPlayer1Turn; return;}
            p1house6.setDisable(false);
            if (House < 7){isPlayer1Turn = !isPlayer1Turn; return;}
            p1house7.setDisable(false);
            if (House < 8){isPlayer1Turn = !isPlayer1Turn; return;}
            p1house8.setDisable(false);
            if (House < 9){isPlayer1Turn = !isPlayer1Turn; return;}
            p1house9.setDisable(false);

            //Flip the value of isPlayer1Turn
            isPlayer1Turn = !isPlayer1Turn;
        }
        //Else means that it's Player 2's turn
        else
        {
            //Disable all of Player 1's Buttons
            p1house1.setDisable(true);
            p1house2.setDisable(true);
            p1house3.setDisable(true);
            p1house4.setDisable(true);
            if (House < 5){isPlayer1Turn = !isPlayer1Turn; return;}
            p1house5.setDisable(true);
            if (House < 6){isPlayer1Turn = !isPlayer1Turn; return;}
            p1house6.setDisable(true);
            if (House < 7){isPlayer1Turn = !isPlayer1Turn; return;}
            p1house7.setDisable(true);
            if (House < 8){isPlayer1Turn = !isPlayer1Turn; return;}
            p1house8.setDisable(true);
            if (House < 9){isPlayer1Turn = !isPlayer1Turn; return;}
            p1house9.setDisable(true);

            //Flip the value of isPlayer1Turn
            isPlayer1Turn = !isPlayer1Turn;
        }
    }

    //This is only for using allToggle() function. Don't call it elsewhere
    private void buttonToggleP2(boolean turn)
    {
        //Turn == true means it's Player 1's turn
        if (turn)
        {
            //Disable all of player 2's buttons
            p2house1.setDisable(true);
            p2house2.setDisable(true);
            p2house3.setDisable(true);
            p2house4.setDisable(true);
            if (House < 5){return;}
            p2house5.setDisable(true);
            if (House < 6){return;}
            p2house6.setDisable(true);
            if (House < 7){return;}
            p2house7.setDisable(true);
            if (House < 8){return;}
            p2house8.setDisable(true);
            if (House < 9){return;}
            p2house9.setDisable(true);
        }
        //Else means it's Player 2's turn. Enable all their buttons
        else
        {
            //Enable all of player 2's buttons
            p2house1.setDisable(false);
            p2house2.setDisable(false);
            p2house3.setDisable(false);
            p2house4.setDisable(false);
            if (House < 5){return;}
            p2house5.setDisable(false);
            if (House < 6){return;}
            p2house6.setDisable(false);
            if (House < 7){return;}
            p2house7.setDisable(false);
            if (House < 8){return;}
            p2house8.setDisable(false);
            if (House < 9){return;}
            p2house9.setDisable(false);
        }
    }


    
    /*=================================================================
    Player 1 GUI Objects
    =================================================================*/


    //Player 1 Buttons.
    @FXML
    public Button p1house1, p1house2, p1house3, p1house4, p1house5, p1house6, p1house7, p1house8, p1house9;

    //Player 1 score. If you want to update a player's score, change this text field.
    public TextField p1score;

    /*Text fields to display Player 1 board. If you want to update Player 1 houses,
    change this text field. The text box number corresponds to the house number.
    To update these text fields, using the following function format inside the
    corresponding button function:

    p2text1.setText("3.14")

    Now house 1 for Player 1 will show "3.14"
    */
    public TextField p1text1, p1text2, p1text3, p1text4, p1text5, p1text6, p1text7, p1text8, p1text9;

    /*=================================================================
    Player 2 GUI Objects
    =================================================================*/

    //Player 2 Buttons.
    @FXML
    public Button p2house1, p2house2, p2house3, p2house4, p2house5, p2house6, p2house7, p2house8, p2house9;

    //Player 2 score. If you want to update a player's score, change this text field.
    public TextField p2score;

    /*Text fields to display Player 2 board. If you want to update Player 2 houses,
    change this text field. The text box number corresponds to the house number.
    To update these text fields, using the following function format inside the
    corresponding button function:

    p2text1.setText("Baboon butt")

    Now house 1 for Player 2 will show "Baboon butt"
    */
    public TextField p2text1, p2text2, p2text3, p2text4, p2text5, p2text6, p2text7, p2text8, p2text9;

    /*
    =================================================================
    Player 1 Button Functions
    =================================================================
    */

    @FXML
    public void p1house1Move()
    {
    	if (!running.moveBeads(0, false, !p1Human))
        {
        	allToggle();
            pieVisible();
            updateHouses();
            showWinner();
            if(!p2Human)
            {
            	p2house1Move();
            }
        }else {
        	updateHouses();
            showWinner();
        }
    }

    public void p1house2Move()
    {
    	if (!running.moveBeads(1, false, !p1Human))
        {
        	allToggle();
            pieVisible();
            updateHouses();
            showWinner();
            if(!p2Human)
            {
            	p2house1Move();
            }
        }else {
        	updateHouses();
            showWinner();
        }
    }

    @FXML
    public void p1house3Move()
    {
    	if (!running.moveBeads(2, false, !p1Human))
        {
        	allToggle();
            pieVisible();
            updateHouses();
            showWinner();
            if(!p2Human)
            {
            	p2house1Move();
            }
        }else {
        	updateHouses();
            showWinner();
        }
    }

    @FXML
    public void p1house4Move()
    {
    	if (!running.moveBeads(3, false, !p1Human))
        {
        	allToggle();
            pieVisible();
            updateHouses();
            showWinner();
            if(!p2Human)
            {
            	p2house1Move();
            }
        }else {
        	updateHouses();
            showWinner();
        }
    }

    @FXML
    public void p1house5Move()
    {
    	if (!running.moveBeads(4, false, !p1Human))
        {
        	allToggle();
            pieVisible();
            updateHouses();
            showWinner();
            if(!p2Human)
            {
            	p2house1Move();
            }
        }else {
        	updateHouses();
            showWinner();
        }
    }

    @FXML
    public void p1house6Move()
    {
    	if (!running.moveBeads(5, false, !p1Human))
        {
        	allToggle();
            pieVisible();
            updateHouses();
            showWinner();
            if(!p2Human)
            {
            	p2house1Move();
            }
        }else {
        	updateHouses();
            showWinner();
        }
    }

    @FXML
    public void p1house7Move()
    {
    	if (!running.moveBeads(6, false, !p1Human))
        {
        	allToggle();
            pieVisible();
            updateHouses();
            showWinner();
            if(!p2Human)
            {
            	p2house1Move();
            }
        }else {
        	updateHouses();
            showWinner();
        }
    }

    @FXML
    public void p1house8Move()
    {
    	if (!running.moveBeads(7, false, !p1Human))
        {
        	allToggle();
            pieVisible();
            updateHouses();
            showWinner();
            if(!p2Human)
            {
            	p2house1Move();
            }
        }else {
        	updateHouses();
            showWinner();
        }
    }

    @FXML
    public void p1house9Move()
    {
    	if (!running.moveBeads(8, false, !p1Human))
        {
        	allToggle();
            pieVisible();
            updateHouses();
            showWinner();
            if(!p2Human)
            {
            	p2house1Move();
            }
        }else {
        	updateHouses();
            showWinner();
        }
    }

    /*
    =================================================================
    Player 2 Button Functions
    =================================================================
    */

    @FXML
    public void p2house1Move()
    {
    	if (!running.moveBeads(0, true, !p2Human))
        {
        	allToggle();
            pieVisible();
            updateHouses();
            showWinner();
            if(!p1Human)
            {
            	p1house1Move();
            }
        }else {
        	updateHouses();
            showWinner();
        }
    }

    @FXML
    public void p2house2Move()
    {
    	if (!running.moveBeads(1, true, !p2Human))
        {
        	allToggle();
            pieVisible();
            updateHouses();
            showWinner();
            if(!p1Human)
            {
            	p1house1Move();
            }
        }else {
        	updateHouses();
            showWinner();
        }
    }

    @FXML
    public void p2house3Move()
    {
    	if (!running.moveBeads(2, true, !p2Human))
        {
        	allToggle();
            pieVisible();
            updateHouses();
            showWinner();
            if(!p1Human)
            {
            	p1house1Move();
            }
        }else {
        	updateHouses();
            showWinner();
        }
    }

    @FXML
    public void p2house4Move()
    {
    	if (!running.moveBeads(3, true, !p2Human))
        {
        	allToggle();
            pieVisible();
            updateHouses();
            showWinner();
            if(!p1Human)
            {
            	p1house1Move();
            }
        }else {
        	updateHouses();
            showWinner();
        }
    }

    @FXML
    public void p2house5Move()
    {
    	if (!running.moveBeads(4, true, !p2Human))
        {
        	allToggle();
            pieVisible();
            updateHouses();
            showWinner();
            if(!p1Human)
            {
            	p1house1Move();
            }
        }else {
        	updateHouses();
            showWinner();
        }
    }

    @FXML
    public void p2house6Move()
    {
    	if (!running.moveBeads(5, true, !p2Human))
        {
        	allToggle();
            pieVisible();
            updateHouses();
            showWinner();
            if(!p1Human)
            {
            	p1house1Move();
            }
        }else {
        	updateHouses();
            showWinner();
        }
    }

    @FXML
    public void p2house7Move()
    {
    	if (!running.moveBeads(6, true, !p2Human))
        {
        	allToggle();
            pieVisible();
            updateHouses();
            showWinner();
            if(!p1Human)
            {
            	p1house1Move();
            }
        }else {
        	updateHouses();
            showWinner();
        }
    }

    @FXML
    public void p2house8Move()
    {
    	if (!running.moveBeads(7, true, !p2Human))
        {
        	allToggle();
            pieVisible();
            updateHouses();
            showWinner();
            if(!p1Human)
            {
            	p1house1Move();
            }
        }else {
        	updateHouses();
            showWinner();
        }
    }

    @FXML
    public void p2house9Move()
    {
    	if (!running.moveBeads(8, true, !p2Human))
        {
        	allToggle();
            pieVisible();
            updateHouses();
            showWinner();
            if(!p1Human)
            {
            	p1house1Move();
            }
        }else {
        	updateHouses();
            showWinner();
        }
    }
}
