/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerHandler;

import Database.DataAccessLayer;
import GraphHandler.GraphHandler;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Abdel
 */
public class ServerHandler extends Thread {

    ServerSocket serverSocket;

    public void startServer() {
        try {
            serverSocket = new ServerSocket(5005);
            start();
            GraphHandler.offlineUsers = DataAccessLayer.getNumberOfUsers();
            GraphHandler.updateGraph(GraphHandler.onlineUsers, GraphHandler.offlineUsers);
        } catch (IOException ex) {
            Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void stopServer() {
        try {
            serverSocket.close();
            UserHandler.closeAllConnections();
            try {
                stop();
                join();
            } catch (InterruptedException ex) {
                Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isConnected() {
        return !serverSocket.isClosed();
    }

    @Override
    public void run() {
        Socket clientSocket;
        try {
            while (true) {
                clientSocket = serverSocket.accept();
                new UserHandler(clientSocket);
            }

        } catch (IOException ex) {
            Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
