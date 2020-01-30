package com.mygdx.server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server_old extends JFrame {

    public static void main(String[] args) {
        Server_old sally = new Server_old();
        sally.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sally.start();
    }

    //Gui
    JTextField userText;
    JTextArea chatWindow;

    //Server
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private ServerSocket server;
    private Socket socket; //the connection between two computers

    public Server_old() {
        super("Messager");
        userText = new JTextField();
        userText.setEditable(false); //Disable input for the textField
        userText.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        sendMessage(e.getActionCommand());
                        userText.setText("");
                    }
                }
        );
        add(userText, BorderLayout.NORTH);
        chatWindow = new JTextArea();
        add(new JScrollPane(chatWindow));
        setSize(300, 150);
        setVisible(true);
    }

    public void start() {
        try {
            server = new ServerSocket(6789, 100);
            while (true) {
                try {
                    waitForConnection();
                    setupStreams();
                    whileChatting();

                } catch (EOFException eofException) {
                    sendMessage("\n Server ended the connection! ");
                } finally {
                    closeStuff();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void waitForConnection() throws IOException {
        showMessage("Waiting for someone to connect...");
        socket = server.accept();
        showMessage("Now connected to: " + socket.getInetAddress().getHostName());
    }

    private void setupStreams() throws IOException {
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.flush();

        inputStream = new ObjectInputStream(socket.getInputStream());
        showMessage("Streams are setup");
    }

    private void whileChatting() throws IOException {
        String message = "You are now connected!";
        sendMessage(message);
        ableToType(true);

        do {
            try {
                message = (String) inputStream.readObject();
                showMessage(message);
            } catch (ClassNotFoundException nof) {
                showMessage("what you said seems weird to me...");
            }
        } while (!message.equals("CLIENT - END"));
    }

    private void closeStuff() throws IOException {
        showMessage("Closing connection...");
        ableToType(false);
        outputStream.close();
        inputStream.close();
        socket.close();
    }

    //send message to client
    private void sendMessage(String message) {
        try {
            outputStream.writeObject("SERVER: " + message);
            outputStream.flush(); //Wipe the bad stuff away like a good boy
            showMessage("SERVERR: " + message);
        } catch (IOException e) {
            chatWindow.append("ERROR: you cant send that. Its rude, isn't it?");
        }
    }

    private void showMessage(final String message) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        chatWindow.append(message);
                    }
                }
        );
    }

    //lets user put text into the chat window
    private void ableToType(final boolean tof) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        userText.setEditable(tof);
                    }
                }
        );
    }
}
