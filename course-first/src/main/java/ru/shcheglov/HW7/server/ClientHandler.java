package ru.shcheglov.HW7.server;

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
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String msg = in.readUTF();
                            if (msg.startsWith("/auth ")) {
                                String[] data = msg.split("\\s");
                                String newNick = server.getAuthServ().getNickByLoginAndPass(data[1], data[2]);
                                if (newNick != null) {
                                    nickname = newNick;
                                    ClientHandler.this.sendMsg("/authok " + nickname);
                                    server.subscribe(ClientHandler.this);
                                    break;
                                } else {
                                    ClientHandler.this.sendMsg("Wrong login/password");
                                }
                            }
                        }
                        while (true) {
                            String msg = in.readUTF();
                            System.out.println(nickname + ": " + msg);
                            if (msg.equals("/end")) break;
                            if (msg.startsWith("/private")) {
                                String[] array = msg.split("\\s");
                                String message = msg.substring(array[0].length() + array[1].length() + 1, msg.length());
                                server.privateMsg(nickname, array[1], message);
                            } else {
                                server.broadcastMsg(nickname + ": " + msg);
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Couldn't read the input stream");
                    } finally {
                        server.unsubscribe(ClientHandler.this);
                        try {
                            socket.close();
                        } catch (IOException e) {
                            System.out.println("Couldn't close client socket " + nickname);
                        }
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
