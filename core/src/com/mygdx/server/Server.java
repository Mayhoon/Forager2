package com.mygdx.server;

import com.mygdx.config.Color;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server implements Runnable {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private boolean running;

    public Server() {
        //Print adress
        String inet;
        try {
            inet = InetAddress.getLocalHost().getHostAddress();
            System.out.println("Adress is: " + inet);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("HEYHO IM RUNNING");
        running = true;
        try {
            start();
            while (running) {
                sendMessage();
                receiveMessage();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException {

        //Waiting for connections
        System.out.println("Waiting for connections...");
        serverSocket = new ServerSocket(6666);
        clientSocket = serverSocket.accept();

        System.out.println(clientSocket.getInetAddress().getHostName() + " has connected");

        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        System.out.println("Streams setup");

    }

    private void receiveMessage() throws IOException {
        System.out.println("Receiving message from the client...");
        String receiveMessage = in.readLine();
        System.out.println(receiveMessage);
        System.out.println("RECEIVED");

        if (receiveMessage.equals("end")) {
            stopConnections();
        }
    }

    private void sendMessage() {
        System.out.println("Sending message to the client...");
        out.println("Nice to meet you!");
        out.flush();
    }

    private void stopConnections() throws IOException {
        System.out.println(Color.ANSI_PURPLE + "Closing connections..." + Color.ANSI_RESET);
        running = false;
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
        System.out.print("Connections closed");
    }
}