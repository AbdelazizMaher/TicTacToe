# Tic-Tac-Toe

## Overview
The **`Tic-Tac-Toe`** game is a network-based application that allows players to enjoy the classic game in various modes. It supports `single-player mode` against computer, `local multiplayer` on the same machine, and `online multiplayer` against other players. The game also features an elegant user interface, game recording for replay, videos for winners and losers. The server application manages connections and data exchange among users.

## Contributors
- Abdelaziz Maher
- Adham Mohamed
- Eman Mahmoud
- Nermeen Mohamed

## Features
- **`Single-player mode`**: Play against a computer opponent.
- **`Local multiplayer`**: Two players can play on the same machine.
- **`Online multiplayer`**: Play against other players online.
- **`List of online/available users`**: Display a list of users who are online and available to play.
- **`Request and accept/refuse to play`**: Send and respond to game invitations.
- **`Elegant user interface`**: A visually appealing and user-friendly interface.
- **`Game recording and replay`**: Record games and replay them later.
- **`Bonus videos for winning players`**: Play bonus videos for winners.
- **`Player score storage`**: Keep track of player scores.
- **`User registration and login`**: Allow users to register and log in to the server.
- **`Server with simple GUI`**: A server application with start/stop service and user activity graphs.

## Requirements
- Java Development Kit (JDK)
- JavaFX library
- Network connectivity for online multiplayer

## Installation
1. Clone the repository:
    ```bash
    git clone https://github.com/AbdelazizMaher/TicTacToe.git
    ```
2. Navigate to the project directory:
    ```bash
    cd TicTacToe
    ```

## Setup
1. Install Java Development Kit (JDK) from [Oracle JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
2. Install JavaFX library from [Gluon JavaFX](https://gluonhq.com/products/javafx/)

## Usage
1. To run the server, execute:
    ```bash
    java -jar TicTacToeServer.jar
    ```
2. To run the client, execute:
    ```bash
    java -jar TicTacToeClient.jar
    ```
3. Register or login using the provided interface.
4. Choose to play in single-player, local multiplayer, or online multiplayer mode.
5. Follow on-screen instructions to play and record games.

## Server Application Structure

### Overview
The **`ServerApp`** is responsible for managing connections, streams, and data exchange among users. It provides a `simple graphical user interface (GUI)` with start/stop buttons to control the service and graphs that show the number of active users, both online and offline.

### Packages and Files

#### DataModels Package
- **`ServerRequestInterface.java`**: Interface for server requests such as sign-up, sign-in, sending moves, and ending connections.
- **`UserDataModel.java`**: Data model for user information, including username, password, and score.

#### Database Package
- **`DataAccessLayer.java`**: Provides methods for interacting with the database, including adding users, retrieving user information, counting the number of users, and updating user scores.

#### GraphHandler Package
- **`GraphHandler.java`**: Manages the bar chart for displaying the number of online and offline users, including methods for setting the chart and updating the graph.

#### ServerControllers Package
- **`GUIController.java`**: Manages the server's graphical user interface, including start/stop buttons and user statistics charts.

#### ServerGameApp Package
- **`ServerApp.java`**: Main class for the server application, responsible for initializing and starting the server.
- **`ServerGUI.java`**: Defines the graphical user interface layout and elements for the server application.

#### ServerHandler Package
- **`ServerHandler.java`**: Manages the server operations, including starting and stopping the server, accepting client connections, and updating user statistics.
- **`UserHandler.java`**: Handles individual client connections, managing communication, processing client requests, and updating user data.

#### media Package
- Contains image files used by the server application.

#### styles Package
- **`Stylesheet.css`**: Defines the CSS styles for the server application's user interface.

## Client Application Structure

### Overview
The **`ClientApp`** is responsible for the user interface and game logic for the Tic-Tac-Toe game. It allows players to play in single-player mode, local multiplayer mode, and online multiplayer mode. The client application manages the game's state, user interactions, and communication with the server.

### Packages and Files

#### ClientHandler Package
- **`ClientHandler.java`**: Handles the client-side logic for connecting to the server, sending requests, receiving responses, and managing communication.

### FXML Package
- Purpose: Generates the UI components for the `XOGame` package.

### MainApp Package
- **`ClientApp.java`**: The entry point of the client application, responsible for initializing and starting the client.

### XOControllers Package
- **`AvailableUserPageController.java`**: Handles the logic for the available users page.
- **`DifficultyLevelController.java`**: Manages the selection of difficulty levels.
- **`HistoryPageController.java`**: Manages the history page.
- **`HomePageController.java`**: Handles the logic for the home page.
- **`IncomingInvitationController.java`**: Manages incoming game invitations.
- **`LoginPageController.java`**: Manages the login page.
- **`LoseVideoPageController.java`**: Handles the logic for the lose video page.
- **`OfflinePageController.java`**: Manages the offline game mode.
- **`OnlinePageController.java`**: Manages the online game mode.
- **`PopUpLogOutController.java`**: Manages the pop-up for logging out.
- **`PopUpPageController.java`**: Handles various pop-up dialogs.
- **`RecordController.java`**: Manages game recording.
- **`RecordPageController.java`**: Handles the logic for the record page.
- **`SignupPageController.java`**: Manages the signup page.
- **`VsCompPageController.java`**: Manages the versus computer game mode.
- **`WinVideoPageController.java`**: Handles the logic for the win video page.

### XOGame Package
- **`AvailableUsersPage.java`**: Handles the available users functionality.
- **`DifficultyLevel.java`**: Manages the difficulty level selection.
- **`FXMLDocumentBase.java`**: General document handling.
- **`HistoryPage.java`**: Manages the history page.
- **`HomePage.java`**: Handles the home page functionality.
- **`LoginPage.java`**: Manages the login page.
- **`LoseVideoPage.java`**: Handles the lose video functionality.
- **`OfflinePage.java`**: Manages the offline game mode.
- **`OnlinePage.java`**: Manages the online game mode.
- **`PopUpLogOut.java`**: Manages the logout pop-up.
- **`PopUpPage.java`**: Handles various pop-up dialogs.
- **`RecordPage.java`**: Manages the record page.
- **`SignupPage.java`**: Manages the signup page.
- **`VsCompPage.java`**: Manages the versus computer game mode.
- **`WinVideoPage.java`**: Handles the win video functionality.

### XOGameBoard Package
- **`TicTacToe.java`**: Encapsulates the core game logic for Tic-Tac-Toe.

### media Package
- Contains image, audio,video files used by the client application.

### styles Package
- **`Stylesheet.css`**: Defines the CSS styles for the client application's user interface.
