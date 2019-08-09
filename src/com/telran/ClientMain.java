package com.telran;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {

    public static void main(String[] args) throws IOException {

        InetAddress address = InetAddress.getByName("10.0.0.25");
        Socket socket = new Socket(address, 9000);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));


        while (true) {
            System.out.println("Enter your message to server: ");
            String message = new Scanner(System.in).nextLine();



            writer.write(message + "\n");
            writer.flush();

            if (message.equals("exit")) {
                break;
            }

            System.out.println("Server response: ");
            String response = reader.readLine();
            System.out.println(response);
        }

        System.out.println("Closing client-server connection");
        reader.close();
        writer.close();
        socket.close();
        System.out.println("Closing client application");

    }
}
