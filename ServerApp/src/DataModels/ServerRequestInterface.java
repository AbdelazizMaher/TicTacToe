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
public interface ServerRequestInterface {

    void signUp();

    void signIn();

    void sendAvailablePlayers();

    void sendInvitation();

    void getInvitationResponse();

    void sendNormalMove();

    void gameWinnerMove();

    void gameDrawMove();

    void withdraw();

    void playAgain();

    void logout();

    void connectionEnded();
}
