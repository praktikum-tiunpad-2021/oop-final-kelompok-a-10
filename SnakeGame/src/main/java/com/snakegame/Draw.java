package com.snakegame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Draw {
    public void drawCell(GraphicsContext gc, Engine engine, Frame frame, Snake snake, Fruit fruit) {
        if (engine.getIsGameOver()) {
            gc.setFill(Color.RED);
            gc.setFont(new Font("", 50));
            gc.fillText("GAME OVER", (frame.getWidth() / 4) * frame.getCellSize(), (frame.getHeight() / 2) * frame.getCellSize());
            return;
        }

        for (int i = snake.getBodySize() - 1; i >= 1; i--) {
            snake.bodyCells.get(i).x = snake.bodyCells.get(i - 1).x;
            snake.bodyCells.get(i).y = snake.bodyCells.get(i - 1).y;
        }

        switch (snake.getCurDirection()) {
            case UP:
                snake.bodyCells.get(0).y--;
                if (snake.bodyCells.get(0).y < 0) {
                    engine.setGameOver(true);
                }
                break;
            case DOWN:
                snake.bodyCells.get(0).y++;
                if (snake.bodyCells.get(0).y > frame.getHeight()) {
                    engine.setGameOver(true);
                }
                break;
            case LEFT:
                snake.bodyCells.get(0).x--;
                if (snake.bodyCells.get(0).x < 0) {
                    engine.setGameOver(true);
                }
                break;
            case RIGHT:
                snake.bodyCells.get(0).x++;
                if (snake.bodyCells.get(0).x > frame.getWidth()) {
                    engine.setGameOver(true);
                }
                break;

        }

        // Bertambah panjang saat memakan buah
        if (fruit.getX() == snake.bodyCells.get(0).x && fruit.getY() == snake.bodyCells.get(0).y) {
            snake.bodyCells.add(new Cell(-1, -1));
            fruit.newFruit(frame, snake);
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


        // gc.setFill(Color.BLACK);
        // gc.fillRect(0, 0, frame.getWidth() * frame.getCellSize(), frame.getHeight() * frame.getCellSize());

        // Menampilkan score
        gc.setFill(Color.CYAN);
        gc.setFont(new Font("", 30));
        gc.fillText("Score: " + snake.getScore(), 10, 30);

        // Randomisasi warna fruit
        Color cc = Color.WHITE;

        switch (fruit.getColor()) {
            case 0:
                cc = Color.PURPLE;
                break;
            case 1:
                cc = Color.LIGHTBLUE;
                break;
            case 2:
                cc = Color.YELLOW;
                break;
            case 3:
                cc = Color.PINK;
                break;
            case 4:
                cc = Color.ORANGE;
                break;
        }
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
