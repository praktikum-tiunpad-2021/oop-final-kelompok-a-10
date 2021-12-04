package com.snakegame;

import javafx.application.Application;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Draw {

    public void drawBackground(GraphicsContext gc, Frame frame){
        // Background color for playing area
        for (int i = 0; i < frame.getWidth(); i++) {
            for (int j = 0; j < frame.getHeight(); j++) {
                if ((i + j) % 2 == 0) {
                    gc.setFill(Color.web("AAD751"));
                } else {
                    gc.setFill(Color.web("A2D149"));
                }
                gc.fillRect(i * frame.getCellSize(), j * frame.getCellSize(), frame.getCellSize(), frame.getCellSize());
            }
        }
    }

    // Draw snake
    public void drawSnake(Snake snake){
        for (int i = snake.getBodySize() - 1; i >= 1; i--) {
            snake.bodyCells.get(i).x = snake.bodyCells.get(i - 1).x;
            snake.bodyCells.get(i).y = snake.bodyCells.get(i - 1).y;
        }
    }

    // Set snake's color
    public void setSnakeColor(GraphicsContext gc, Frame frame, Snake snake){
        for (Cell c : snake.bodyCells) {
            gc.setFill(Color.YELLOW);
            gc.fillRect(c.x * frame.getCellSize(), c.y * frame.getCellSize(), frame.getCellSize() - 1, frame.getCellSize() - 1);
            gc.setFill(Color.ORANGE);
            gc.fillRect(c.x * frame.getCellSize(), c.y * frame.getCellSize(), frame.getCellSize() - 3, frame.getCellSize() - 3);
        }
    }

    public void moveSnake(Frame frame, Engine engine, Snake snake){
        switch (snake.getCurDirection()) {
            case UP -> {
                snake.bodyCells.get(0).y--;
                if (snake.bodyCells.get(0).y < 0) {
                    engine.setGameOver(true);
                    engine.setIsInGame(false);
                }
            }
            case DOWN -> {
                snake.bodyCells.get(0).y++;
                if (snake.bodyCells.get(0).y > frame.getHeight()) {
                    engine.setGameOver(true);
                    engine.setIsInGame(false);

                }
            }
            case LEFT -> {
                snake.bodyCells.get(0).x--;
                if (snake.bodyCells.get(0).x < 0) {
                    engine.setGameOver(true);
                    engine.setIsInGame(false);

                }
            }
            case RIGHT -> {
                snake.bodyCells.get(0).x++;
                if (snake.bodyCells.get(0).x > frame.getWidth()) {
                    engine.setGameOver(true);
                    engine.setIsInGame(false);

                }
            }
        }
    }

    public void increaseSnakeLength(Frame frame, Player playerGame, Snake snake, Fruit fruit){
        // Snake's length increasing whenever ate a fruit
        if (fruit.getX() == snake.bodyCells.get(0).x && fruit.getY() == snake.bodyCells.get(0).y) {
            snake.bodyCells.add(new Cell(-1, -1));  // New head for snake
            fruit.newFruit(frame, snake, playerGame);
        }
    }

    public void invalidSnakeMove(Engine engine, Snake snake){
        // Game over when snake's head hitting its own body
        for (int i = 1; i < snake.getBodySize(); i++) {
            if (snake.bodyCells.get(0).x == snake.bodyCells.get(i).x && snake.bodyCells.get(0).y == snake.bodyCells.get(i).y) {
                engine.setGameOver(true);
            }
        }
    }

    public void drawFruit(GraphicsContext gc, Frame frame, Fruit fruit){
        // Randomize fruit color
        Color cc = switch (fruit.getColor()) {
            case 0 -> Color.PURPLE;
            case 1 -> Color.LIGHTBLUE;
            case 2 -> Color.YELLOW;
            case 3 -> Color.PINK;
            case 4 -> Color.RED;
            default -> Color.WHITE;
        };

        // Draw fruit with randomized color
        gc.setFill(cc);
        gc.fillOval(fruit.getX() * frame.getCellSize(), fruit.getY() * frame.getCellSize(), frame.getCellSize(), frame.getCellSize());
    }

    public void drawPlayerScore(GraphicsContext gc, Player playerGame){
        // Showing player's current score
        gc.setFill(Color.CYAN);
        gc.setFont(new Font("Comic sans ms", 30));
        gc.fillText("Score : " + playerGame.getScore(), 10, 30);
    }


    public void drawCell(GraphicsContext gc, Engine engine, Frame frame, Player playerGame, Snake snake, Fruit fruit) {

        drawBackground(gc, frame);

        setSnakeColor(gc, frame, snake);
        drawSnake(snake);
        moveSnake(frame, engine, snake);
        increaseSnakeLength(frame, playerGame, snake, fruit);
        invalidSnakeMove(engine, snake);

        drawFruit(gc, frame, fruit);

        drawPlayerScore(gc, playerGame);

    }
}
