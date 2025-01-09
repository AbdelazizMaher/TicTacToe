/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerHandler;

import DataModels.ServerRequestInterface;
import DataModels.UserDataModel;
import Database.DataAccessLayer;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.StringTokenizer;

/**
 *
 * @author Abdel
 */
public class UserHandler extends Thread implements ServerRequestInterface {

    Socket socket;
    DataInputStream reader;
    PrintStream talker;
    boolean isPlaying;
    String opponentName;
    static Vector<UserHandler> userVector = new Vector<UserHandler>();
    

    String requestMsg;
    public StringTokenizer requestMsgTokens;
    UserDataModel user;

    UserHandler(Socket socket) {
        user = new UserDataModel();
        this.socket = socket;
        

        try {
            reader = new DataInputStream(socket.getInputStream());
            talker = new PrintStream(socket.getOutputStream());
            UserHandler.userVector.add(this);
            start();
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                requestMsg = reader.readLine();
                if(!requestMsg.equals(null)){
                requestMsgTokens = new StringTokenizer(requestMsg, "#@$");
                String clientRequest = requestMsgTokens.nextToken();
                switch (clientRequest) {
                    case "signUp":
                        signUp();
                        break;
                        
                    case "signIn":
                        signIn();
                        break;
                    case "sendAvailablePlayers":
                        sendAvailablePlayers();
                        break;
                }
                }
            } catch (IOException ex) {
                System.out.println(ex.getLocalizedMessage());
                closeConnection();
            }
        }
    }

    @Override
    public void signUp() {
        user.setUsername(requestMsgTokens.nextToken());
        user.setPassword(requestMsgTokens.nextToken());        
        boolean isSignedUp = DataAccessLayer.addUser(user);
        if (isSignedUp) {
            talker.println("Signed Up");         
        } else {
            talker.println("The username exists");
            closeConnection();
        }
    }

    @Override
    public void signIn() {
        String username = requestMsgTokens.nextToken();
        String password = requestMsgTokens.nextToken(); 
        user = DataAccessLayer.getUser(username);
        if(user != null && password.equals(user.getPassword())){
            talker.println("Signed In");
        } else {
            talker.println("Invalid username or password");
            closeConnection();
        }
    }


    @Override
    public void sendAvailablePlayers() {  
        Vector<String> online=new Vector<String>();
        for(UserHandler player:userVector){
            if(!player.isPlaying && player.user != null){
                online.add(player.user.getUsername() + "#@$" + player.user.getScore() + "#@$");
            }
        }
        online.remove(this.user.getUsername() + "#@$" + this.user.getScore() + "#@$");
        System.out.println(online);
        talker.println(online);
    }

    @Override
    public void sendInvitation() {
    }

    @Override
    public void getInvetation() {
    }

    @Override
    public void sendMove() {
    }

    @Override
    public void gameWinner() {
    }

    @Override
    public void gameDraw() {
    }

    @Override
    public void withdraw() {
    }

    @Override
    public void logout() {
    }

    @Override
    public void connectionEnded() {
    }

    public void closeConnection() {
        try {
            socket.close();
            talker.close();
            reader.close();
            userVector.remove(this);
        } catch (IOException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void closeAllConnections() {
        for (UserHandler user : userVector) {
            if (user.socket.isConnected()) {
                try {
                    user.socket.close();
                    user.talker.close();
                    user.reader.close();
                } catch (IOException ex) {
                    Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }
}
