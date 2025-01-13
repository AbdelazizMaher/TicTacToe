/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerHandler;

import DataModels.ServerRequestInterface;
import DataModels.UserDataModel;
import Database.DataAccessLayer;
import GraphHandler.GraphHandler;
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

    public StringTokenizer requestMsgTokens;
    UserDataModel user;

    UserHandler(Socket socket) {
        user = new UserDataModel();
        this.socket = socket;

        try {
            reader = new DataInputStream(socket.getInputStream());
            talker = new PrintStream(socket.getOutputStream());

            addUser(this);
            start();
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                String requestMsg = reader.readLine();
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

                    case "sendInvitaion":
                        sendInvitation();
                        break;

                    case "invitationResponse":
                        getInvitationResponse();
                        break;

                    case "logout":
                        logout();
                        break;
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
        
        if (user != null && password.equals(user.getPassword())) {
            talker.println("Signed In");
        } else if (user != null && !password.equals(user.getPassword())) {
            talker.println("Invalid password!");
            closeConnection();
        } else {
            talker.println("Invalid username!");
            closeConnection();
        }
    }

    @Override
    public void sendAvailablePlayers() {
        Vector<String> online = new Vector<String>();
        for (UserHandler player : userVector) {
            if (!player.isPlaying && player.user != null) {
                online.add(player.user.getUsername() + "*" + player.user.getScore() + "*");
            }
        }
        sendListToAll(online);
    }

    void sendListToAll(Vector<String> online) {
        for (UserHandler client : userVector) {
            Vector<String> list = new Vector<>(online);
            list.remove(client.user.getUsername() + "*" + client.user.getScore() + "*");
            client.talker.println("sendAvailablePlayers#@$" + list);
        }
    }

    @Override
    public void sendInvitation() {
        String opponentName = requestMsgTokens.nextToken();
        UserHandler opponent = getOpponentHandler(opponentName);
        if (opponent != null) {
            opponent.talker.println("invitation" + "#@$" + user.getUsername());
        } else {
            talker.println("Error" + "#@$" + "failed");
        }
    }

    @Override
    public void getInvitationResponse() {
        String response = requestMsgTokens.nextToken();

        opponentName = requestMsgTokens.nextToken();
        if (response.equals("accept")) {
            isPlaying = true;

            setOpponent(opponentName, user.getUsername());
            UserHandler opponent = getOpponentHandler(opponentName);
            opponent.isPlaying = true;

            getOpponentOutputStream(opponentName).println("accepted" + "#@$" + user.getUsername());
            sendAvailablePlayers();
        } else {
            getOpponentOutputStream(opponentName).println("declined" + "#@$" + user.getUsername());
        }
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
        closeConnection();
    }

    @Override
    public void connectionEnded() {
    }

    public void closeConnection() {
        try {
            socket.close();
            talker.close();
            reader.close();
            removeUser(this);
            stop();
            join();
        } catch (IOException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
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

    private void setOpponent(String name, String opponent) {
        for (UserHandler userHandler : userVector) {
            if (userHandler.user.getUsername().equals(name)) {
                userHandler.opponentName = opponent;
                break;
            }
        }
    }

    private PrintStream getOpponentOutputStream(String username) {
        PrintStream ps = null;
        for (UserHandler userHandler : userVector) {
            if (userHandler.user.getUsername().equals(username)) {
                ps = userHandler.talker;
                break;
            }
        }
        return ps;
    }

    private UserHandler getOpponentHandler(String username) {
        for (UserHandler userHandler : userVector) {
            if (userHandler.user.getUsername().equals(username)) {
                return userHandler;
            }
        }
        return null;
    }

    private void addUser(UserHandler userHandler) {
        userVector.add(userHandler);
        GraphHandler.updateGraph(++GraphHandler.onlineUsers, --GraphHandler.offlineUsers);
    }

    private void removeUser(UserHandler userHandler) {
        userVector.remove(userHandler);
        sendAvailablePlayers();
        GraphHandler.updateGraph(--GraphHandler.onlineUsers, ++GraphHandler.offlineUsers);
    }
    
    private boolean isSignedIn(UserHandler user){
        int count = 0;
        for (UserHandler userHandler : userVector) {
            if (userHandler.user.getUsername().equals(user.user.getUsername()) && count<1) {
                count++;
                break;
            }     
        }
        if(count==1){return true;}
        else {return false;}
    }
}
