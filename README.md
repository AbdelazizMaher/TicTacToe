# Tic-Tac-Toe

## Overview
The **Tic-Tac-Toe** game is a network-based application that allows players to enjoy the classic game in various modes. It supports single-player mode against an AI, local multiplayer on the same machine, and online multiplayer against other players. The game also features an elegant user interface, game recording for replay, bonus videos for winners, and score storage. The server application manages connections and data exchange among users, providing a seamless gaming experience.

## Contributors
- Abdelaziz Maher
- Adham Mohamed 
- Eman Mahmoud
- Nermeen Mohamed

## Features
- Single-player mode (play against computer)
- Local multiplayer (two players on the same machine)
- Online multiplayer (play against others online)
- List of online/available users
- Request and accept/refuse to play
- Elegant user interface
- Game recording and replay
- Bonus videos for winning players
- Player score storage
- User registration and login
- Server with simple GUI, start/stop service, and user activity graphs

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
The **ServerApp** is responsible for managing connections, streams, and data exchange among users. It provides a simple graphical user interface (GUI) with start/stop buttons to control the service and graphs that show the number of active users, both online and offline.

### Folders and Files

**1. `src` Folder:**
- Contains all source code files for the server application.

**2. `main` Folder:**
- **ServerMain.java**: The entry point of the server application, responsible for initializing and starting the server.
- **Server.java**: Core server logic, including listening for client connections and broadcasting messages.

**3. `gui` Folder:**
- **ServerGUI.java**: Handles the graphical user interface for the server, including start/stop buttons and user activity graphs.

**4. `handler` Folder:**
- **ClientHandler.java**: Manages individual client connections, handling communication and data exchange.

**5. `resources` Folder:**
- Contains resource files such as configuration files, images, and other assets used by the server application.

### Classes and Their Responsibilities
- **ServerMain**: Initializes the server application and sets up the main components.
- **ServerGUI**: Provides the user interface for server administrators, displaying active users and allowing control over the server.
- **ClientHandler**: Manages communication with connected clients, processing requests, and sending responses.
- **Server**: Handles the core server functionality, including accepting client connections and managing broadcasts.

## Client Application Structure

### Overview
The **ClientApp** is responsible for the user interface and game logic for the Tic-Tac-Toe game. It allows players to play in single-player mode, local multiplayer mode, and online multiplayer mode. The client application manages the game's state, user interactions, and communication with the server.

### Folders and Files

**1. `src` Folder:**
- Contains all source code files for the client application.

**2. `main` Folder:**
- **ClientMain.java**: The entry point of the client application, responsible for initializing and starting the client.
- **Client.java**: Manages the connection to the server and handles communication.

**3. `controller` Folder:**
- **OnlinePageController.java**: Manages the online game logic, including initializing components, handling server responses, and controlling game flow.
- **SinglePlayerController.java**: Manages the single-player game logic, including interaction with the AI.
- **LocalMultiPlayerController.java**: Manages the local multiplayer game logic.

**4. `model` Folder:**
- **TicTacToe.java**: Encapsulates the core game logic for Tic-Tac-Toe.
- **Player.java**: Represents a player in the game, including their attributes and actions.
- **Move.java**: Represents a move in the game, including its properties and methods.

**5. `view` Folder:**
- **MainView.java**: Handles the main user interface components and layout.
- **GameView.java**: Manages the game-specific UI elements and interactions.

**6. `resources` Folder:**
- Contains resource files such as images, configuration files, and other assets used by the client application.

### Classes and Their Responsibilities
- **ClientMain**: Initializes the client application and sets up the main components.
- **Client**: Manages the connection to the server and handles communication.
- **OnlinePageController**: Manages the online game logic, including initializing components, handling server responses, and controlling game flow.
- **SinglePlayerController**: Manages the single-player game logic, including interaction with the AI.
- **LocalMultiPlayerController**: Manages the local multiplayer game logic.
- **TicTacToe**: Encapsulates the core game logic for Tic-Tac-Toe.
- **Player**: Represents a player in the game, including their attributes and actions.
- **Move**: Represents a move in the game, including its properties and methods.
- **MainView**: Handles the main user interface components and layout.
- **GameView**: Manages the game-specific UI elements and interactions.
