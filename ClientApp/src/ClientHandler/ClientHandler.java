/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientHandler;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eman_
 */
public class ClientHandler {

    protected static Socket server;
    protected static DataInputStream ear;
    protected static PrintStream mouth;
    private static String receivedText = null;
    private static boolean connected = false;

    public ClientHandler() {
    }

    public static boolean startConnection(String info) {
        try {
            server = new Socket("127.0.0.1", 5005);
            ear = new DataInputStream(server.getInputStream());
            mouth = new PrintStream(server.getOutputStream());
            sendRequest(info);
            connected = true;
        } catch (IOException ex) {
            connected = false;
        }
        return connected;
    }
    
     public static void sendRequest(String text) {
        mouth.println(text);
    }
   
    public static String getResponse() {
        try {
            receivedText = ear.readLine();
        } catch (IOException e) {
        }
        return receivedText;
    }

    
    public static void closeConnection() {
    if (server != null && !server.isClosed()) {
        try {
                server.close();
                mouth.close();
                ear.close();
                connected = false;
            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }        
    }
    }
    
    public static boolean isConnected() {
        return connected;
    }
    
    public static void setConnected(boolean connected) {
        ClientHandler.connected = connected;
    }
}
