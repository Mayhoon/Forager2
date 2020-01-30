package com.mygdx.server;

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

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Waiting for connections...");
        clientSocket = serverSocket.accept();
        System.out.println(clientSocket.getInetAddress().getHostName() + " has connected");

        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        //Reading from the socket
        do {
            String clientMessage = in.readLine();
            System.out.println("CLIENT SAYS: " + clientMessage);

            if (clientMessage.equals("end")) {
                stop();
            }
        } while (true);
    }

    public void stop() throws IOException {
        System.out.println("Closing connections...");
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
        System.out.println("Connections closed!");
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
}