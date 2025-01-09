/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataModels;

/**
 *
 * @author Abdel
 */
public interface ServerRequestInterface 
{
    void signUp();
    void signIn();
    void sendAvailablePlayers();
    void sendInvitation();
    void getInvitationResponse();
    void sendMove();
    void gameWinner();
    void gameDraw();
    void withdraw();
    void logout();
    void connectionEnded();
}
