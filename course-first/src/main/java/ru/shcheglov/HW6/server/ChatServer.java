package ru.shcheglov.HW6.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            System.out.println("Server launched...waiting for clients...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected! Start your talk =)");
            System.out.println("***(type '/end' for exit)");
            new ClientHandler(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
