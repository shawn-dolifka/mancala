package Mancala;


import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class client{
	
	BufferedReader readIn;
	PrintWriter printOut;
	
	public client(int portNum) throws Exception {
		//Opens Socket with Set IP and passed Port number
		Socket socket = new Socket("127.0.0.1",portNum);
		readIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		printOut = new PrintWriter(socket.getOutputStream(), true);	
	}



	void playGame() throws Exception
	{
		String message;
		boolean gameRunning = true;
		
		try {
			//Continually checking for response from server (WHILE game is running)
			while(gameRunning)
			{
				//Message from server
				message = readIn.readLine();
				if(message.equals("WELCOME"))
				{
					System.out.print(message);
					String[] args = {};
					Main.main(args);
					
					gameRunning=false;
				}
				if(message.startsWith("INFO"))
				{
					System.out.print("INFO HERE");
					gameRunning=false;
				}
	
			}
			
		}
		finally{
			
		}
	}
	
	public static void main (String args[]) throws Exception
	{
		//Creates new Client called Player and passes designated port number
		client player = new client(20187);
		//Starts playGame function for our client "player"
		player.playGame();
		
	}
}
