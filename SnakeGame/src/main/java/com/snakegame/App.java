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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class App extends Application {
    Frame frame = new Frame();
    Engine engine = new Engine();
    KeyboardListener keyboardListener = new KeyboardListener();
    Draw draw = new Draw();
    Snake snake = new Snake();
    Fruit fruit = new Fruit();

    private Scene game;
    private Scene menu;
    private Scene level;
    private Scene leaderboard;

    private Stage mainStage;
    private Stage scoreStage = new Stage();

    private String scoreName;

    private Button btn1, btn2, btn3, btn4, backButton, okBtn;

//    private Button playBtn, menuBtn, levelBtn, leaderboardBtn;

    private TextField scoreNameField;

//    private Button easyBtn, mediumBtn, hardBtn;

    private String css;

    private ObservableList<Player> player = FXCollections.observableArrayList();

    public void ButtonClicked(ActionEvent e) throws IOException {

        if (e.getSource() == btn1) {
            try {
                startGame();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }

        else if (e.getSource() == btn2) {
            try {
                leaderboard = new Scene(showLeaderboard());
                leaderboard.getStylesheets().add(css);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            mainStage.setScene(leaderboard);
        }

        else if (e.getSource() == okBtn) {
            scoreName = scoreNameField.getText();

            player.add(new Player(scoreName, snake.getScore()));

            scoreStage.close();
        }

        else if (e.getSource() == backButton) {
            mainStage.setScene(menu);

        }

        else if(e.getSource() == btn3){
            writeLeaderboard(player);
            System.exit(0);
        }

    }

    private Pane createMenu() throws IOException {
        // menu
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml"));

        Pane root1 = (Pane) root.lookup("#menu");
        root1.getStyleClass().add("back");

        btn1 = (Button) root.lookup("#btn1");
        btn1.getStyleClass().add("button-menu");

        btn2 = (Button) root.lookup("#btn2");
        btn2.getStyleClass().add("button-menu");

        btn3 = (Button) root.lookup("#btn3");
        btn3.getStyleClass().add("button-menu");


        btn1.setOnAction(e -> {
            try {
                ButtonClicked(e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        btn2.setOnAction(e -> {
            try {
                ButtonClicked(e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        btn3.setOnAction(e -> {
            try {
                ButtonClicked(e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        // btn4.setOnAction(e -> {
        //     try {
        //         ButtonClicked(e);
        //     } catch (IOException ex) {
        //         ex.printStackTrace();
        //     }
        // });

        readLeaderboard();

        return root1;
    }

    public void startGame() throws IOException{
        int speed = this.snake.getSpeed();
        this.engine.setIsInGame(true);

        fruit.newFruit(frame, snake);

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
                    draw.drawCell(gc, engine, frame, snake, fruit);
                }

                if (now - lastCell > 1000000000 / speed) {
                    lastCell = now;
                    draw.drawCell(gc, engine, frame, snake, fruit);
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

        keyboardListener.listenKey(aTimer, scene, snake);


        for(int i=0; i<5; ++i){
            snake.bodyCells.add(new Cell(frame.getWidth() / 2, frame.getHeight() / 2));
        }

        mainStage.setResizable(false);
        mainStage.setScene(scene);
        mainStage.show();

    }

    private void stopGame() throws IOException { // function stopping the
        // game
        mainStage.setScene(menu);
        scoreStage.setResizable(false);
        scoreStage.setTitle("GAME OVER !!!");

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scores.fxml"));

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


    private void readLeaderboard() {
        try {
            FileInputStream fis = new FileInputStream("PlayerSaveFile.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            List<Player> list = (List<Player>) ois.readObject();

            player = FXCollections.observableList(list);
            ois.close();
            fis.close();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void writeLeaderboard(ObservableList<Player> list) {
        try {
            FileOutputStream fos = new FileOutputStream("PlayerSaveFile.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(new ArrayList<Player>(list));
            oos.close();
            fos.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public Pane showLeaderboard() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LeaderBoard.fxml"));

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

        TableView<Player> table = new TableView();
        table.getStyleClass().add("table-view");

        table.setMaxHeight((frame.getHeight() - 5) * frame.getCellSize());
        table.setMaxWidth(frame.getWidth() * frame.getCellSize());

        TableColumn<Player, String> name = new TableColumn<>("Name");
        name.setMinWidth(frame.getWidth() * frame.getCellSize() / 2);

        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Player, Integer> score = new TableColumn<>("Score");

        score.setMinWidth(frame.getWidth() * frame.getCellSize() / 2);
        score.setCellValueFactory(new PropertyValueFactory<>("score"));


        Collections.sort(player, (c1, c2) -> {
            if (c1.getScore() > c2.getScore())
                return -1;
            if (c1.getScore() < c2.getScore())
                return 1;
            return 0;
        });

        table.setItems(player);
        table.getColumns().addAll(name, score);

        root1.getChildren().add(table);

        return root1;

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            mainStage = primaryStage;
            css = this.getClass().getResource("/css/style.bss").toExternalForm();

            menu = new Scene(createMenu());
            menu.getStylesheets().add(css);

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

