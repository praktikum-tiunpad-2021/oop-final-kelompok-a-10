package com.snakegame;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.io.*;
import java.util.*;


public class App extends Application {
    Frame frame = new Frame();
    Engine engine = new Engine();
    KeyboardListener keyboardListener = new KeyboardListener();
    Draw draw = new Draw();
    Player playerGame;
    Snake snake = new Snake();
//    int snakeSpeed = 5; // initial snake's speed that can be changed through level menu
    Fruit fruit = new Fruit();

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

    public void ButtonClicked(ActionEvent e) throws IOException {

        if (e.getSource() == startBtn) {
            try {
                engine.setGameOver(false);
                startGame();

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        else if(e.getSource() == levelBtn){
            try{
                changeLevel();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }

        else if(e.getSource() == easyBtn){
            this.snake.setSpeed(5);
            mainStage.setScene(menu);
        }

        else if(e.getSource() == mediumBtn){
            this.snake.setSpeed(8);
            mainStage.setScene(menu);
        }

        else if(e.getSource() == hardBtn){
            mainStage.setScene(menu);
            this.snake.setSpeed(12);
        }

        else if (e.getSource() == leaderboardBtn) {
            try {
                leaderboard = new Scene(showLeaderboard());
                leaderboard.getStylesheets().add(css);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            mainStage.setScene(leaderboard);
        }

        else if (e.getSource() == okBtn) {
            playerGame.setName(scoreNameField.getText());
            playerList.add(new Player(playerGame.getName(), playerGame.getScore()));
            scoreStage.close();
        }

        else if (e.getSource() == backButton) {
            mainStage.setScene(menu);
        }

        else if(e.getSource() == exitBtn){
            writeLeaderboard(playerList);
            System.exit(0);
        }

    }

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

        readLeaderboard();

        return menuPane;
    }

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

    public void startGame() throws IOException{
        this.playerGame = new Player();
        this.snake.bodyCells.clear();

        int snakeSpeed = this.snake.getSpeed();
        this.engine.setIsInGame(true);

        fruit.newFruit(frame, snake, playerGame);

        Group root = new Group();
        Canvas c = new Canvas(frame.getWidth() * frame.getCellSize(), frame.getHeight() * frame.getCellSize());
        GraphicsContext gc = c.getGraphicsContext2D();
        root.getChildren().add(c);

        AnimationTimer aTimer = new AnimationTimer(){
            long lastCell = 0;
            @Override
            public void handle(long now) {

                if (lastCell == 0) {
                    lastCell = now;
                    draw.drawCell(gc, engine, frame, playerGame, snake, fruit);
                }

                if (now - lastCell > 1000000000 / snakeSpeed) {
                    lastCell = now;
                    draw.drawCell(gc, engine, frame, playerGame, snake, fruit);
                    try {
                        if(engine.getIsGameOver()){
                            stop();
                            stopGame();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        };

        aTimer.start();

        Scene scene = new Scene(root, frame.getWidth() * frame.getCellSize(), frame.getHeight() * frame.getCellSize());

        keyboardListener.listenKey(scene, snake);

        // snake length (5) initialization
        for(int i=0; i<5; ++i){
            snake.bodyCells.add(new Cell(frame.getWidth() / 2, frame.getHeight() / 2));
        }

        mainStage.setResizable(false);
        mainStage.setScene(scene);
        mainStage.show();

    }

    private void stopGame() throws IOException {
        mainStage.setScene(menu);

        scoreStage = new Stage();
        scoreStage.setResizable(false);
        scoreStage.getIcons().add(new Image("img/snake-icon.jpg"));
        scoreStage.setTitle("GAME OVER !!!");

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/Scores.fxml")));
        Pane root1 = (Pane) root.lookup("#scores");
        okBtn = (Button) root.lookup("#enterButton");
        scoreNameField = (TextField) root.lookup("#inputField");

        okBtn.setOnAction(e -> {
            try {
                ButtonClicked(e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        Scene scene = new Scene(root1);
        scene.getStylesheets().add(css);
        scoreStage.setScene(scene);
        scoreStage.show();


    }

    public void changeLevel() throws IOException {
        level = new Scene(level());
        level.getStylesheets().add(css);
        mainStage.setScene(level);
    }


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

    public Pane showLeaderboard() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/LeaderBoard.fxml")));
        Pane root1 = (Pane) root.lookup("#pane");
        root1.getStyleClass().add("back");

        backButton = (Button) root.lookup("#backButton");
        backButton.getStyleClass().add("button-menu");
        backButton.setOnAction(e -> {
            try {
                ButtonClicked(e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        TableView<Player> table = new TableView<>();
        table.getStyleClass().add("table-view");
        table.setMaxHeight((frame.getHeight() - 5) * frame.getCellSize());
        table.setMaxWidth(frame.getWidth() * frame.getCellSize());

        TableColumn<Player, String> name = new TableColumn<>("Name");
        name.setMinWidth(frame.getWidth() * frame.getCellSize() / 2.0);
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Player, Integer> score = new TableColumn<>("Score");
        score.setMinWidth(frame.getWidth() * frame.getCellSize() / 2.0);
        score.setCellValueFactory(new PropertyValueFactory<>("score"));

        playerList.sort((c1, c2) -> Integer.compare(c2.getScore(), c1.getScore()));

        table.setItems(playerList);
        table.getColumns().add(name);
        table.getColumns().add(score);

        root1.getChildren().add(table);

        return root1;
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            mainStage = primaryStage;

            css = Objects.requireNonNull(this.getClass().getResource("/css/style.bss")).toExternalForm();

            menu = new Scene(mainMenu());
            menu.getStylesheets().add(css);

            primaryStage.setScene(menu);
            primaryStage.setResizable(false);
            primaryStage.getIcons().add(new Image("img/snake-icon.jpg"));
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

