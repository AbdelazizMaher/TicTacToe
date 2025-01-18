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
import java.io.DataOutputStream;
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
    DataOutputStream talker;
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
            talker = new DataOutputStream(socket.getOutputStream());

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
                String requestMsg = reader.readUTF();
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

                    case "winningMove":
                        gameWinnerMove();
                        break;

                    case "drawMove":
                        gameDrawMove();
                        break;

                    case "normalMove":
                        sendNormalMove();
                        break;

                    case "withdraw":
                        withdraw();
                        break;

                    case "playagain":
                        playAgain();
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
            try {
                talker.writeUTF("Signed Up");
            } catch (IOException ex) {
                Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                talker.writeUTF("The username exists");
            } catch (IOException ex) {
                Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConnection();
        }
    }

    @Override
    public void signIn() {
        String username = requestMsgTokens.nextToken();
        String password = requestMsgTokens.nextToken();

        user = DataAccessLayer.getUser(username);

        if (user != null && password.equals(user.getPassword())) {
            if (isUserAlreadySignedIn()) {
                try {
                    talker.writeUTF("Username Currently Signedin, try another one!");
                    closeConnection();
                } catch (IOException ex) {
                    Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    talker.writeUTF("Signed In");
                } catch (IOException ex) {
                    Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (user != null && !password.equals(user.getPassword())) {
            try {
                talker.writeUTF("Invalid password!");
            } catch (IOException ex) {
                Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConnection();
        } else {
            try {
                talker.writeUTF("Invalid username!");
            } catch (IOException ex) {
                Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
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
            try {
                client.talker.writeUTF("sendAvailablePlayers#@$" + list);
            } catch (IOException ex) {
                Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @Override
    public void sendInvitation() {
        String opponentName = requestMsgTokens.nextToken();
        System.out.println("opp" + opponentName);
        UserHandler opponent = getOpponentHandler(opponentName);
   
            user.setScore(DataAccessLayer.getUserScore(user.getUsername()));
            opponent.user.setScore(DataAccessLayer.getUserScore(opponent.user.getUsername()));
            Integer userScore = user.getScore();
            Integer opponentScore = opponent.user.getScore();
        if (opponent != null) {
            try {
                opponent.talker.writeUTF("invitation" + "#@$" + user.getUsername() + "#@$" + userScore.toString()+ "#@$" + opponentScore.toString());
            } catch (IOException ex) {
                Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                talker.writeUTF("Error" + "#@$" + "failed");
            } catch (IOException ex) {
                Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
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

            user.setScore(DataAccessLayer.getUserScore(user.getUsername()));
            opponent.user.setScore(DataAccessLayer.getUserScore(opponent.user.getUsername()));

            Integer userScore = user.getScore();
            Integer opponentScore = opponent.user.getScore();

            try {
                getOpponentOutputStream(opponentName).writeUTF("accepted" + "#@$" + user.getUsername() + "#@$" + opponentScore.toString() + "#@$" + userScore.toString());
            } catch (IOException ex) {
                Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            sendAvailablePlayers();
        } else {
            try {
                getOpponentOutputStream(opponentName).writeUTF("declined" + "#@$" + user.getUsername());
            } catch (IOException ex) {
                Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            sendAvailablePlayers();
        }
    }

    @Override
    public void sendNormalMove() {
        String row = requestMsgTokens.nextToken();
        String col = requestMsgTokens.nextToken();
        try {
            getOpponentOutputStream(opponentName).writeUTF("normalMove" + "#@$" + row + "#@$" + col + "#@$");
        } catch (IOException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void gameWinnerMove() {
        String row = requestMsgTokens.nextToken();
        String col = requestMsgTokens.nextToken();

        isPlaying = false;
        getOpponentHandler(opponentName).isPlaying = false;
        try {
            getOpponentOutputStream(opponentName).writeUTF("losingMove" + "#@$" + row + "#@$" + col + "#@$");
        } catch (IOException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        DataAccessLayer.updateUserScore(user.getUsername());
    }

    @Override
    public void gameDrawMove() {
        String row = requestMsgTokens.nextToken();
        String col = requestMsgTokens.nextToken();

        isPlaying = false;
        getOpponentHandler(opponentName).isPlaying = false;
        try {
            getOpponentOutputStream(opponentName).writeUTF("draw" + "#@$" + row + "#@$" + col + "#@$");
        } catch (IOException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void withdraw() {
        isPlaying = false;
        getOpponentHandler(opponentName).isPlaying = false;
        try {
            getOpponentOutputStream(opponentName).writeUTF("withdraw");
        } catch (IOException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void playAgain() {
        try {
            getOpponentOutputStream(opponentName).writeUTF("invitation" + "#@$" + user.getUsername());
        } catch (IOException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private DataOutputStream getOpponentOutputStream(String username) {
        DataOutputStream ps = null;
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

    private boolean isUserAlreadySignedIn() {
        int count = 0;
        for (UserHandler userHandler : userVector) {
            if (userHandler.user.getUsername().equals(user.getUsername())) {
                count++;
            }
        }
        if (count >= 2) {
            return true;
        } else {
            return false;
        }
    }
}
