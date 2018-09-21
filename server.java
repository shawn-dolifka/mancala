package Mancala;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class server {

    private int houses;
    private int seeds;
    private boolean random;

    BufferedReader iReader;
    PrintWriter oWriter;

    public server(int houses, int seeds, boolean random) {
        this.houses = houses;
        this.seeds = seeds;
        this.random = random;
    }

    private class Player extends Thread {
        Socket socket;


        public Player(Socket socket, int playerBoard) {
            this.socket = socket;
            //this.playerBoard = playerBoard;
            try {
                iReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                oWriter = new PrintWriter(socket.getOutputStream(), true);
                oWriter.println("WELCOME");
            } catch (IOException e) {
                System.out.println("Player error: " + e);
            }
        }

        public void runGame() {
            try {
                System.out.println("Connection Success");
            } finally {

            }

        }

    }


    public static void main(String args[]) throws UnknownHostException, IOException {
        ServerSocket s1Listener = new ServerSocket(20187);
        System.out.println("Mancala Server Running!");

        try {
            server mancalaServer = new server(6, 4, false);
            server.Player p1 = mancalaServer.new Player(s1Listener.accept(), 1);
            //server.Player p2 = mancalaServer.new Player(s1Listener.accept(),2);

            p1.runGame();
            //p2.runGame();

        } finally {
            s1Listener.close();
        }
    }
}


