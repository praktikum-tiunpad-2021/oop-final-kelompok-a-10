package com.snakegame;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;



public class App extends Application {
    Frame frame;
    Engine engine;
    KeyboardListener keyboardListener;
    Draw draw;
    Snake snake;
    Fruit fruit;

    public void start(Stage primaryStage) {
        try {
            this.frame = new Frame();
            this.engine = new Engine();
            this.keyboardListener = new KeyboardListener();
            this.draw = new Draw();
            this.snake = new Snake();
            this.fruit = new Fruit();
            int speed = this.snake.getSpeed();
            fruit.newFruit(frame, snake);
            Group root = new Group();
            Canvas c = new Canvas(frame.getWidth() * frame.getCellSize(), frame.getHeight() * frame.getCellSize());
            GraphicsContext gc = c.getGraphicsContext2D();
            root.getChildren().add(c);

            Label spaceToStart = new Label("Press SPACEBAR to Start The Game");
            Font font = Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 25);
            spaceToStart.setFont(font);
            spaceToStart.setTextFill(Color.BLUE);
            spaceToStart.setTranslateX(frame.getWidth() / 6 * frame.getCellSize());
            spaceToStart.setTranslateY(frame.getHeight() / 2 * frame.getCellSize());
            root.getChildren().add(spaceToStart);
            

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
                    }
                }
            };

            Scene scene = new Scene(root, frame.getWidth() * frame.getCellSize(), frame.getHeight() * frame.getCellSize());

            keyboardListener.listenKey(spaceToStart, aTimer, scene, snake);
            

            for(int i=0; i<5; ++i){
                snake.bodyCells.add(new Cell(frame.getWidth() / 2, frame.getHeight() / 2));
            }

            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.setTitle("SNAKE GAME");

            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
