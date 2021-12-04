package com.snakegame;

import javafx.application.Application;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Draw {
    public void drawCell(GraphicsContext gc, Engine engine, Frame frame, Player playerGame, Snake snake, Fruit fruit) {

        for (int i = snake.getBodySize() - 1; i >= 1; i--) {
            snake.bodyCells.get(i).x = snake.bodyCells.get(i - 1).x;
            snake.bodyCells.get(i).y = snake.bodyCells.get(i - 1).y;
        }

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


        // Bertambah panjang saat memakan buah
        if (fruit.getX() == snake.bodyCells.get(0).x && fruit.getY() == snake.bodyCells.get(0).y) {
            snake.bodyCells.add(new Cell(-1, -1));
            fruit.newFruit(frame, snake, playerGame);
        }

        // Game over saat mengenai tubuh ular 
        for (int i = 1; i < snake.getBodySize(); i++) {
            if (snake.bodyCells.get(0).x == snake.bodyCells.get(i).x && snake.bodyCells.get(0).y == snake.bodyCells.get(i).y) {
                engine.setGameOver(true);
            }
        }

        // Background color dari area permainan
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

        // Menampilkan score
        gc.setFill(Color.CYAN);
        gc.setFont(new Font("", 30));
        gc.fillText("Score: " + playerGame.getScore(), 10, 30);

        // Randomisasi warna fruit
        Color cc = switch (fruit.getColor()) {
            case 0 -> Color.PURPLE;
            case 1 -> Color.LIGHTBLUE;
            case 2 -> Color.YELLOW;
            case 3 -> Color.PINK;
            case 4 -> Color.ORANGE;
            default -> Color.WHITE;
        };

        gc.setFill(cc);
        gc.fillOval(fruit.getX() * frame.getCellSize(), fruit.getY() * frame.getCellSize(), frame.getCellSize(), frame.getCellSize());

        // Warna ular
        for (Cell c : snake.bodyCells) {
            gc.setFill(Color.YELLOW);
            gc.fillRect(c.x * frame.getCellSize(), c.y * frame.getCellSize(), frame.getCellSize() - 3, frame.getCellSize() - 3);
            gc.setFill(Color.ORANGE);
            gc.fillRect(c.x * frame.getCellSize(), c.y * frame.getCellSize(), frame.getCellSize() - 5, frame.getCellSize() - 5);

        }


    }

}
