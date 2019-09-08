package ru.shcheglov.HW8.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Server server;
    private String nickname;

    public String getNickname() {
        return nickname;
    }

    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            new Thread(() -> {
                try {
                    while (true) {
                        String msg = in.readUTF();
                        if (msg.startsWith("/auth ")) {
                            String[] data = msg.split("\\s");
                            String newNick = server.getAuthService().getNickByLoginAndPass(data[1], data[2]);
                            if (newNick != null) {
                                if (!server.isNickBusy(newNick)) {
                                    nickname = newNick;
                                    ClientHandler.this.sendMsg("/authok " + nickname);
                                    server.subscribe(ClientHandler.this);
                                    server.broadcastMsg(nickname + " joined the chat!");
                                    break;
                                } else {
                                    ClientHandler.this.sendMsg("Your account is busy. Try another one.");
                                }
                            } else {
                                ClientHandler.this.sendMsg("Wrong login/password");
                            }
                        }
                    }
                    while (true) {
                        String msg = in.readUTF();
                        System.out.println(nickname + ": " + msg);
                        if (msg.startsWith("/")) {
                            if (msg.equals("/end")) break;
                            if (msg.startsWith("/private")) {
                                String[] array = msg.split("\\s", 3);
                                server.privateMsg(ClientHandler.this, array[1], array[2]);
                            }
                        } else {
                            server.broadcastMsg(nickname + ": " + msg);
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Couldn't read the input stream");
                } finally {
                    server.broadcastMsg(nickname + " left the chat!");
                    server.unsubscribe(ClientHandler.this);
                    nickname = null;
                    try {
                        socket.close();
                    } catch (IOException e) {
                        System.out.println("Couldn't close client socket " + nickname);
                    }
                }
            }).start();
        } catch (IOException e) {
            System.out.println("Couldn't create input/output stream");
        }
    }

    public void sendMsg(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            System.out.println("Couldn't send the message '" + message + "'");
        }
    }
}
