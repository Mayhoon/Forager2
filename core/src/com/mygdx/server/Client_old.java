package com.mygdx.server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client_old extends JFrame {
    public static void main(String[] args) throws IOException {
        Client_old c = new Client_old("localhost");
        c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c.start();
    }

    private JTextField userText;
    private JTextArea chatWindow;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    String message;
    String serverIp;
    private Socket socket;

    public Client_old(String host) {
        super("Client mofo");
        serverIp = host;
        userText = new JTextField();
        userText.setEditable(false);
        userText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage(e.getActionCommand());
                userText.setText("");
            }
        });
        add(userText, BorderLayout.NORTH);
        chatWindow = new JTextArea();
        add(new JScrollPane(chatWindow), BorderLayout.CENTER);
        setSize(300, 150);
        setVisible(true);
    }

    public void start() throws IOException {
        try {
            connectToServer();
            setupStreams();
            whileChatting();
        } catch (EOFException e) {
            showMessage("Client terminated connection");
        } catch (IOException ioExcepetion) {
            ioExcepetion.printStackTrace();
        } finally {
            closeStuff();
        }
    }

    private void connectToServer() throws IOException {
        showMessage("Attempting to connect...");
        socket = new Socket(InetAddress.getByName(serverIp), 6789);
        showMessage("Connected to: " + socket.getInetAddress().getHostName());
    }

    //setup streams to send and receive messages
    private void setupStreams() throws IOException {
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.flush();

        inputStream = new ObjectInputStream(socket.getInputStream());
        showMessage("Streams are setup correctly");
    }

    //while chatting with server
    private void whileChatting() throws IOException {
        ableToType(true);
        do {
            try {
                message = (String) inputStream.readObject();
                showMessage(message);
            } catch (ClassNotFoundException e) {
                showMessage("I dont know that type of object");
            }
        } while (!message.equals("SERVER - END"));
    }

    private void closeStuff() throws IOException {
        showMessage("Closing streams...");
        ableToType(false);
        try {
            outputStream.close();
            outputStream.flush();
            inputStream.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        showMessage("Streams closed");
    }

    //send message to server
    private void sendMessage(String message) {
        try {
            outputStream.writeObject("CLIENT: " + message);
            outputStream.flush();
            showMessage("CLIENT: " + message);
        } catch (IOException ioException) {
            chatWindow.append("something went wrong messaging the server");
        }
    }

    //update gui/ chat window
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

    private void ableToType(final boolean tof) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                userText.setEditable(tof);
            }
        });
    }
}

