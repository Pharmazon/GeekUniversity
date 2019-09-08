package ru.shcheglov.HW7.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;

public class Server {

    private Vector<ClientHandler> clients;
    private AuthService authServ;

    public AuthService getAuthServ() {
        return authServ;
    }

    public Server() {
        AuthService authService = null;
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            clients = new Vector<>();
            authServ = new AuthService();
            authServ.connect();
            System.out.println("Server started...waiting for clients...");
            while (true) {
                Socket socket = serverSocket.accept();
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
    }

    public void unsubscribe(ClientHandler ch) {
        clients.remove(ch);
    }

    public void broadcastMsg(String msg) {
        for (ClientHandler ch : clients) {
            ch.sendMsg(msg);
        }
    }

    public void privateMsg(String sender, String recipient, String msg) {
        for (ClientHandler o: clients){
            if (o.getNickname().equals(recipient)) {
                o.sendMsg("(Private) " + sender + ": " + msg);
                break;
            }
        }
    }
}
