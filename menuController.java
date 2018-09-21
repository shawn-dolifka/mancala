package Mancala;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

import java.io.IOException;

public class menuController
{
    /*=================================================================
    Button Objects
    =================================================================*/

    //Buttons for the number of houses
    public Button house4, house5, house6, house7, house8, house9;

    //Buttons for the number of seeds
    public Button seeds1, seeds2, seeds3, seeds4, seeds5, seeds6,
            seeds7, seeds8, seeds9, seeds10;

    //Buttons for player selecting
    public Button playerVplayer, playerVAI, aiVai;

    //Button objects for fixed and random seeds
    public Button randomSeeds, fixedSeeds;

    //Button to get the time from time text field
    public  Button setTime;

    //Text box for players
    public TextField playersText;

    //Text box for the distribution
    public TextField distributionText;

    public TextField timeText;

    /*=================================================================
    Menu Variables
    =================================================================*/

    //Integers for the houses and seeds count
    private int houses, seeds;

    //Integer to store the time
    private int time;

    //Booleans to pick who is human and who is AI
    private boolean p1Human = true, p2Human = true;

    //Boolean to determine if seeds start fixed or randomized in houses
    private boolean randomizeSeed = false;

    //String to keep track of which board size is picked
    private String pickedBoard = "";

    //This is the text field with the "Mancala" display at the top
    public TextField textField;

    public Label timeLabel;

    /*=================================================================
    Helper Functions (Non-Button Functions)
    =================================================================*/

    //This function is to allow the buttons to print house/seed count
    public void printTextField()
    {
        textField.setText("Game will have " + houses + " houses and " + seeds + " seeds.");
    }

    /*=================================================================
    Button Function - Time
    =================================================================*/

    public void getTime()
    {
        String textTime = timeText.getText();
        try {
            Integer.parseInt(textTime);
        } catch(NumberFormatException e) {
            timeLabel.setText("Invalid Input");
            return;
        } catch(NullPointerException e) {
            timeLabel.setText("Invalid Input");
            return;
        }
        int temp = Integer.parseInt(textTime);
        time = temp;
        timeLabel.setText("Time limit is " + time + " seconds");
    }

    /*=================================================================
    Button Function - Human/AI Buttons
    =================================================================*/

    //Button control for fixed seed button
    public void setFixedSeed()
    {
        randomizeSeed = false;
        distributionText.setText("Fixed");
    }

    //Button control for random seed button
    public void setRandomSeed()
    {
        randomizeSeed = true;
        distributionText.setText("Random");
    }

    /*=================================================================
    Button Function - Human/AI Buttons
    =================================================================*/

    //Button to select a Human vs a Human
    public void setPVP()
    {
        p1Human = true;
        p2Human = true;
        playersText.setText("Player V Player");
    }

    //Button to select a Human vs AI
    public void setPVAI()
    {
        p1Human = true;
        p2Human = false;
        playersText.setText("Player V AI");
    }
    //Button to select an AI vs AI
    public void setAIVAI()
    {
        p1Human = false;
        p2Human = false;
        playersText.setText("AI V AI");
    }

    /*=================================================================
    Button Funtion - Start Button
    =================================================================*/

    //Button operation for clicking "Start With These Options" button
    @FXML
    public void startGame(ActionEvent event) throws IOException
    {
        if (pickedBoard.equals(""))
        {
            textField.setText("You must select a number of houses!");
            return;
        }

        if (seeds == 0)
        {
            textField.setText("You must select a number of seeds!");
            return;
        }

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();;
        FXMLLoader loader = new FXMLLoader(getClass().getResource(pickedBoard));
        Parent gameScene = loader.load();
        GameController controller = loader.<GameController>getController();
        controller.setPlayers(p1Human,p2Human);
        controller.createBoard(houses,seeds,randomizeSeed);
        controller.allToggle();
        controller.pieRule.setDisable(true);
        Scene scene = new Scene(gameScene);
        window.setScene(scene);
        window.show();
        
    }

    /*=================================================================
    Button Functions - House Buttons
    =================================================================*/

    //Button operation for four house size
    public void fourHouse()
    {
    	houses = 4;
    	pickedBoard = "FourHouse.fxml";
    	printTextField();
    }

    //Button operation for five house size
    public void fiveHouse()
    {
    	houses = 5;
        pickedBoard = "FiveHouse.fxml";
        printTextField();
    }

    //Button operation for six house size
    public void sixHouse()
    {
    	houses = 6;
        pickedBoard = "SixHouse.fxml";
        printTextField();
    }

    //Button operation for seven house size
    public void sevenHouse()
    {
    	houses = 7;
        pickedBoard = "SevenHouse.fxml";
        printTextField();
    }

    //Button operation for eight house size
    public void eightHouse()
    {
    	houses = 8;
        pickedBoard = "EightHouse.fxml";
        printTextField();
    }

    //Button operation for nine house size
    public void nineHouse()
    {
        pickedBoard = "NineHouse.fxml";
    	houses = 9;
        printTextField();
    }

    /*=================================================================
    Button Functions - Seed Buttons
    =================================================================*/

    //Button operation for one seed per house
    public void oneSeed()
    {
    	seeds = 1;
        printTextField();
    }

    //Button operation for two seeds per house
    public void twoSeed()
    {
    	seeds = 2;
        printTextField();
    }

    //Button operation for three seeds per house
    public void threeSeed()
    {
    	seeds = 3;
        printTextField();
    }

    //Button operation for four seeds per house
    public void fourSeed()
    {
    	seeds = 4;
        printTextField();
    }

    //Button operation for five seeds per house
    public void fiveSeed()
    {
    	seeds = 5;
        printTextField();
    }

    //Button operation for six seeds per house
    public void sixSeed()
    {
    	seeds = 6;
        printTextField();
    }

    //Button operation for seven seeds per house
    public void sevenSeed()
    {
    	seeds = 7;
        printTextField();
    }

    //Button operation for eight seeds per house
    public void eightSeed()
    {
    	seeds = 8;
        printTextField();
    }

    //Button operation for nine seeds per house
    public void nineSeed()
    {
    	seeds = 9;
        printTextField();
    }

    //Button operation for ten seeds per house
    public void tenSeed()
    {
    	seeds = 10;
        printTextField();
    }
}
