package com.mygdx.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private Scanner scanner;

    public static void main(String[] args) {
        try {
            new Client();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Client() throws IOException {
        Scanner scanner = new Scanner(System.in);
        startConnection("localhost", 6666);
        do {
            sendMessage(scanner);
            receiveMessage();
        } while (true);
    }

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void sendMessage(Scanner scanner) {
        System.out.println("MESSAGE TO SERVER: ");
        String msg = scanner.next();
        out.println(msg);
        out.flush();
    }

    private void receiveMessage() throws IOException {
        String receivedMessage =  in.readLine();
        if( receivedMessage.equals("end") ){
            stopConnection();
        }

    }

    public void stopConnection() throws IOException {
        System.out.println("Closing connections...");
        in.close();
        out.close();
        clientSocket.close();
        System.out.println("Connections closed!");
    }
}