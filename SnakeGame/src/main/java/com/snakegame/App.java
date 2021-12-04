package com.snakegame;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class App extends Application {
    Frame frame = new Frame();
    Engine engine = new Engine();
    KeyboardListener keyboardListener = new KeyboardListener();
    Draw draw = new Draw();
    Snake snake = new Snake();
    Fruit fruit = new Fruit();
    Player playerGame;

    private Scene menu;
    private Scene level;
    private Scene leaderboard;

    private Stage mainStage;
    private Stage scoreStage;

    private Button startBtn, leaderboardBtn, exitBtn, levelBtn, backButton, okBtn;
    private Button easyBtn, mediumBtn, hardBtn;

    private TextField scoreNameField;

    private String css;

    private ObservableList<Player> playerList = FXCollections.observableArrayList();

    // Main menu
    private Pane mainMenu() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/Menu.fxml")));

        Pane menuPane = (Pane) root.lookup("#menu");
        menuPane.getStyleClass().add("back");

        startBtn = (Button) root.lookup("#startBtn");
        startBtn.getStyleClass().add("button-menu");

        levelBtn = (Button) root.lookup("#levelBtn");
        levelBtn.getStyleClass().add("button-menu");

        leaderboardBtn = (Button) root.lookup("#leaderboardBtn");
        leaderboardBtn.getStyleClass().add("button-menu");

        exitBtn = (Button) root.lookup("#exitBtn");
        exitBtn.getStyleClass().add("button-menu");

        // Action performed depends on user's click
        startBtn.setOnAction(e -> {
            try {
                ButtonClicked(e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        levelBtn.setOnAction(e -> {
            try {
                ButtonClicked(e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        leaderboardBtn.setOnAction(e -> {
            try {
                ButtonClicked(e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        exitBtn.setOnAction(e -> {
            try {
                ButtonClicked(e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        // Read existing leaderboard from PlayerSaveFile.ser
        readLeaderboard();

        return menuPane;
    }

    // Level menu
    private Pane level() throws IOException{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/Level.fxml")));
        Pane levelPane = (Pane) root.lookup("#menu");
        levelPane.getStyleClass().add("back");

        easyBtn = (Button) root.lookup("#easy");
        easyBtn.getStyleClass().add("button-menu");

        mediumBtn = (Button) root.lookup("#medium");
        mediumBtn.getStyleClass().add("button-menu");

        hardBtn = (Button) root.lookup("#hard");
        hardBtn.getStyleClass().add("button-menu");

        backButton = (Button) root.lookup("#backButton");
        backButton.getStyleClass().add("button-menu");

        // Action performed depends on user's click
        easyBtn.setOnAction(e -> {
            try {
                ButtonClicked(e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        mediumBtn.setOnAction(e -> {
            try {
                ButtonClicked(e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        hardBtn.setOnAction(e -> {
            try {
                ButtonClicked(e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        backButton.setOnAction(e -> {
            try {
                ButtonClicked(e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        return levelPane;
    }

    // Read existing leaderboard from external file
    private void readLeaderboard() {
        try {
            FileInputStream fis = new FileInputStream("PlayerSaveFile.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);

            @SuppressWarnings("unchecked")
            List<Player> list = (List<Player>) ois.readObject();

            playerList = FXCollections.observableList(list);
            ois.close();
            fis.close();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    // Save player data upon exiting program to external file
    public void writeLeaderboard(ObservableList<Player> playerList) {
        try {
            FileOutputStream fos = new FileOutputStream("PlayerSaveFile.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(new ArrayList<>(playerList));
            oos.close();
            fos.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Leaderboard menu
    public Pane showLeaderboard() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/LeaderBoard.fxml")));
        Pane leaderboardPane = (Pane) root.lookup("#pane");
        leaderboardPane.getStyleClass().add("back");

        backButton = (Button) root.lookup("#backButton");
        backButton.getStyleClass().add("button-menu");
        backButton.setOnAction(e -> {
            try {
                ButtonClicked(e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        // Table for showing player name and score
        TableView<Player> table = new TableView<>();
        table.getStyleClass().add("table-view");
        table.setMaxHeight((frame.getHeight() - 3) * frame.getCellSize());
        table.setMaxWidth(frame.getWidth() * frame.getCellSize());

        TableColumn<Player, String> name = new TableColumn<>("Name");
        name.setMinWidth(frame.getWidth() * frame.getCellSize() / 2.0);
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Player, Integer> score = new TableColumn<>("Score");
        score.setMinWidth(frame.getWidth() * frame.getCellSize() / 2.0);
        score.setCellValueFactory(new PropertyValueFactory<>("score"));

        // Sort player on the table based on player's score (Descending)
        playerList.sort((c1, c2) -> Integer.compare(c2.getScore(), c1.getScore()));

        table.setItems(playerList);
        table.getColumns().add(name);
        table.getColumns().add(score);

        leaderboardPane.getChildren().add(table);

        return leaderboardPane;
    }

    // Start the game
    public void startGame() throws IOException{
        this.playerGame = new Player();
        this.snake.bodyCells.clear();   // If exist, clear all snake's body

        int snakeSpeed = this.snake.getSpeed();
        fruit.newFruit(frame, snake, playerGame);

        Group root = new Group();
        Canvas c = new Canvas(frame.getWidth() * frame.getCellSize(), frame.getHeight() * frame.getCellSize());
        GraphicsContext gc = c.getGraphicsContext2D();
        root.getChildren().add(c);

        // Gameplay animation depends on snake's speed as Frame Rate
        AnimationTimer aTimer = new AnimationTimer(){
            long lastCell = 0;
            @Override
            public void handle(long now) {

                // When the game starts, this block executed
                if (lastCell == 0) {
                    lastCell = now;
                    draw.drawAllComponents(gc, engine, frame, playerGame, snake, fruit);
                }

                // When the snake's body already exist, this block executed
                if (now - lastCell > 1000000000 / snakeSpeed) {
                    lastCell = now;
                    draw.drawAllComponents(gc, engine, frame, playerGame, snake, fruit);
                    try {
                        if(engine.getIsGameOver()){
                            stop();     // Stop the animation timer
                            stopGame();
                        }
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        aTimer.start(); // Starts the animation

        Scene gameScene = new Scene(root, frame.getWidth() * frame.getCellSize(), frame.getHeight() * frame.getCellSize());

        keyboardListener.listenKey(gameScene, snake);

        // snake's length (5) initialization
        for(int i=0; i<5; ++i){
            snake.bodyCells.add(new Cell(frame.getWidth() / 2, frame.getHeight() / 2));
        }

        mainStage.setResizable(false);
        mainStage.setScene(gameScene);
        mainStage.show();

    }

    // Stop the game if snake's move is invalid
    private void stopGame() throws IOException {
        if(!engine.getIsInGame()){
            try{
                engine.setIsInMenu(true);
                mainStage.setScene(menu);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        // New pop-up window for inserting player's name
        scoreStage = new Stage();
        scoreStage.setResizable(false);
        scoreStage.getIcons().add(new Image(new File("img/snake-icon.jpg").toURI().toString()));
        scoreStage.setTitle("GAME OVER !!!");

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/Scores.fxml")));
        Pane scorePane = (Pane) root.lookup("#scores");
        okBtn = (Button) root.lookup("#enterButton");
        scoreNameField = (TextField) root.lookup("#inputField");

        okBtn.setOnAction(e -> {
            try {
                ButtonClicked(e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        Scene scoreScene = new Scene(scorePane);
        scoreScene.getStylesheets().add(css);
        scoreStage.setScene(scoreScene);
        scoreStage.show();


    }

    // Action performed when specific button is clicked
    public void ButtonClicked(ActionEvent e) throws IOException {

        // Start the game
        if (e.getSource() == startBtn) {
            try {
                engine.setIsInMenu(false);
                engine.setIsInGame(true);
                engine.setGameOver(false);
                startGame();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        // Change level
        else if(e.getSource() == levelBtn){
            try{
                level = new Scene(level());
                level.getStylesheets().add(css);
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
            mainStage.setScene(level);
        }

        // Set level = easy
        else if(e.getSource() == easyBtn){
            try{
                this.snake.setSpeed(5);
                mainStage.setScene(menu);
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
        // Set level = medium
        else if(e.getSource() == mediumBtn){
            try{
                this.snake.setSpeed(8);
                mainStage.setScene(menu);
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }

        // Set level = hard
        else if(e.getSource() == hardBtn){
            try{
                this.snake.setSpeed(12);
                mainStage.setScene(menu);
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }

        // Show leaderboard
        else if (e.getSource() == leaderboardBtn) {
            try {
                leaderboard = new Scene(showLeaderboard());
                leaderboard.getStylesheets().add(css);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            mainStage.setScene(leaderboard);
        }

        // Confirm button for pop-up score window
        else if (e.getSource() == okBtn) {
            try{
                playerGame.setName(scoreNameField.getText());
                playerList.add(new Player(playerGame.getName(), playerGame.getScore()));
                scoreStage.close();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }

        // Back to previous scene
        else if (e.getSource() == backButton) {
            try{
                mainStage.setScene(menu);
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }

        // Exit the program
        else if(e.getSource() == exitBtn){
            try{
                // Saving player's data to external files
                writeLeaderboard(playerList);
                System.exit(0);
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            mainStage = primaryStage;

            css = Objects.requireNonNull(this.getClass().getResource("/css/style.bss")).toExternalForm();

            menu = new Scene(mainMenu());
            menu.getStylesheets().add(css);

            primaryStage.getIcons().add(new Image(new File("img/snake-icon.jpg").toURI().toString()));

            primaryStage.setScene(menu);
            primaryStage.setResizable(false);
            primaryStage.setTitle("SNAKE GAME");
            primaryStage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}