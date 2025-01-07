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
public class UserHandler extends Thread implements ServerRequestInterface 
{
    public static boolean isOnline;
    ServerSocket serverSocket;
    DataInputStream reader;
    PrintStream talker;
    boolean isPlaying;
    String opponentName;
    static Vector <UserHandler> userVector = new Vector<UserHandler>();
    
   
    String requestMsg;
    public StringTokenizer requestMsgTokens;
    UserDataModel user;
    boolean isSignedUp;
    
    UserHandler(Socket s)
        {
            try 
            {
                reader = new DataInputStream(s.getInputStream());
                talker = new PrintStream(s.getOutputStream());
                UserHandler.userVector.add(this);
                start();
            } 
            catch (IOException ex) 
            {
                ex.printStackTrace();
            }

        }
    
        @Override
        public void run()
        {
            while(true)
            {
                try
                {
                    requestMsg = reader.readLine();
                    
                    requestMsgTokens = new StringTokenizer(requestMsg,"#@$");
                    String clientRequest = requestMsgTokens.nextToken();
                    switch(clientRequest)
                    {
                        case "signUp":
                            signUp();
                            break;
                    }
                    
                } 
                catch (IOException ex) 
                {
                    ex.printStackTrace();
                }
            }
        }

    @Override
   public void signUp()
    {
            user.setUsername(requestMsgTokens.nextToken());
            user.setPassword(requestMsgTokens.nextToken());
            try{
                isSignedUp = DataAccessLayer.addUser(user);
                if(isSignedUp)
            {
                talker.println("Signed Up");
            }
            } catch (SQLException ex) {
                talker.println("The username exists");
            }
    }

    @Override
    public void signIn() {}

    @Override
    public void sendAvailablePlayers() {}

    @Override
    public void sendInvitation() {}

    @Override
    public void getInvetation() {}

    @Override
    public void sendMove() {}

    @Override
    public void gameWinner() {}

    @Override
    public void gameDraw() {}

    @Override
    public void withdraw() {}

    @Override
    public void logout() {}

    @Override
    public void connectionEnded() {}
    

}
