package com.telran;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerMain {

    private static List<String> messages = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        int port = 9001;
        ServerSocket serverSocket = new ServerSocket(port);

        System.out.println("Server started at port " + port);
        System.out.println("Waiting for client to connect");
        Socket accept = serverSocket.accept();

        System.out.println("Client connected");
        //reader
        //writer

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
        while (true) {
            System.out.println("Waiting for client input...");
            System.out.println("Message from client: ");
            String messageFromClient = reader.readLine();

            System.out.println("Message received");
            messages.add(messageFromClient);

            System.out.println("\t" + messageFromClient);

            if (messageFromClient.equals("exit")) {
                break;
            }

            String response = String.join(", ", messages);
            System.out.printf("Response to client with %s messages\n", messages.size());

            writer.write(response + "\n");
            writer.flush();
        }

        System.out.println("Messages history: \n" + String.join("\n", messages));
        System.out.println("Closing server resources (socket and server socket)");
        writer.close();
        reader.close();
        accept.close();
        serverSocket.close();
        System.out.println("Closing server application");

    }
}
