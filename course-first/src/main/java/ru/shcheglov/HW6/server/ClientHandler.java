package ru.shcheglov.HW6.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Scanner scan;

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.scan = new Scanner(System.in);

            //thread for client messages
            new Thread(() -> {
                try {
                    String clientmessage;
                    while (true) {
                        clientmessage = in.readUTF();
                        System.out.println("Client: " + clientmessage);
                        sendMsg("You: " + clientmessage);
                        if (clientmessage.equals("/end")) break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            //thread for server messages
            new Thread(() -> {
                String servermessage;
                while (true) {
                    servermessage = scan.nextLine();
                    System.out.println("You: " + servermessage);
                    sendMsg("Server: " + servermessage);
                    if (servermessage.equals("/end")) break;
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
