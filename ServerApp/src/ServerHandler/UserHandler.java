/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerHandler;

import DataModels.ServerRequestInterface;

/**
 *
 * @author Abdel
 */
public class UserHandler implements ServerRequestInterface {

    @Override
    public void signUp() {}

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
    
    
    Thread th = new Thread(()->{
        while(true)
        {}
    });
}
