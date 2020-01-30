package com.mygdx.server;

import com.mygdx.config.Color;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private boolean running;

    public void start(int port) throws IOException {
        System.out.println("Waiting for connections...");
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        System.out.println(clientSocket.getInetAddress().getHostName() + " has connected");

        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        System.out.println("Streams setup");

        //Reading from the socket
        running = true;
        while (running) {
            System.out.println(in.readLine());
            if (in.readLine().equals("end")) {
                stopConnections();
            }
        }
    }

    public static void main(String[] args) {
        Server server;
        try {
            server = new Server();
            server.start(6666);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopConnections() throws IOException {
        System.out.println(Color.ANSI_PURPLE + "Closing connections..." + Color.ANSI_RESET);
        running = false;
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
        System.out.print("Connections closed");
    }
}