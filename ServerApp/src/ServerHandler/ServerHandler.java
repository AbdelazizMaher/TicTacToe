/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerHandler;

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
        } catch (IOException ex) {
            Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void stopServer() {

    }

    @Override
    public void run() {
        Socket clientSocket;
        try {
            clientSocket = serverSocket.accept();
            new UserHandler(clientSocket);
        } catch (IOException ex) {
            Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
