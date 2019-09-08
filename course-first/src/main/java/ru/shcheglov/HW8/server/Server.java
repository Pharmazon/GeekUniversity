package ru.shcheglov.HW8.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;

public class Server {

    private Vector<ClientHandler> clients;
    private AuthService authService;

    public AuthService getAuthService() {
        return authService;
    }

    public Server() {
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            clients = new Vector<>();
            authService = new AuthService();
            authService.connect();
            System.out.println("Server started...waiting for clients...");
            while (true) {
                Socket socket = serverSocket.accept();
                socket.setSoTimeout(1 * 1000);
                System.out.println("Client connected, ip: " + socket.getInetAddress() + ":" +
                        socket.getPort() + ", server ip: " + socket.getLocalAddress() + ":" + socket.getLocalPort());
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            System.out.println("Client socket declined");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Couldn't start the authorization service");
        } finally {
            authService.disconnect();
        }
    }

    public void subscribe(ClientHandler ch) {
        clients.add(ch);
        broadcastClientsList();
    }

    public void unsubscribe(ClientHandler ch) {
        clients.remove(ch);
        broadcastClientsList();
    }

    public boolean isNickBusy(String nick) {
        for (ClientHandler o: clients) {
            if (o.getNickname().equals(nick)) {
                return true;
            }
        }
        return false;
    }

    public void broadcastMsg(String msg) {
        for (ClientHandler ch : clients) {
            ch.sendMsg(msg);
        }
    }

    public void privateMsg(ClientHandler sender, String recipient, String msg) {
        for (ClientHandler o: clients){
            if (o.getNickname().equals(recipient)) {
                o.sendMsg("(Private) From " + sender.getNickname() + ": " + msg);
                sender.sendMsg("(Private) To " + recipient + ": " + msg);
                return;
            }
        }
        sender.sendMsg("Nickname " + recipient + " was not found.");
    }

    public void broadcastClientsList() {
        StringBuilder sb = new StringBuilder("/clientslist ");
        for (ClientHandler o : clients) {
            sb.append(o.getNickname() + " ");
        }
        String out = sb.toString();
        for (ClientHandler o : clients) {
            o.sendMsg(out);
        }
    }
}
