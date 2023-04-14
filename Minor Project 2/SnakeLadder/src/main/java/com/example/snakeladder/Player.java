package com.example.snakeladder;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player {
   private Circle coin;

   private int  currentPositone;

   private String name;

   private static Board gameBoard = new Board();

    public Player(int tileSize, Color coinColour, String playerName){
        coin = new Circle(tileSize/2);
        coin.setFill(coinColour);
        currentPositone = 0; // you can set 0 out of your board;
        movePlayer(1);
        name = playerName;

    }

    public void movePlayer(int diceValue){
        if(currentPositone+diceValue <= 100) {
            currentPositone += diceValue;

            TranslateTransition secondMove = null,  firstMoves= translasteAnimatione(diceValue);

            int newPosition = gameBoard.getNewPositione(currentPositone);
            if(newPosition!=currentPositone && newPosition != -1){
                currentPositone = newPosition;
               secondMove  = translasteAnimatione(6);
            }

            if(secondMove == null){
                firstMoves.play();
            } else {
                SequentialTransition sequentialTransition = new SequentialTransition(firstMoves,
                        new PauseTransition(Duration.millis(1000)), secondMove);
                sequentialTransition.play();
            }
        }
//       int x = gameBoard.getXCoordinates(currentPositone);
//       int y = gameBoard.getYCoordiantes(currentPositone);
//       coin.setTranslateX(x);
//       coin.setTranslateY(y);

    }

    private TranslateTransition translasteAnimatione(int diceValue){
        TranslateTransition animate = new TranslateTransition(Duration.millis(200*diceValue),coin);
        animate.setToX(gameBoard.getXCoordinates(currentPositone));
        animate.setToY(gameBoard.getYCoordiantes(currentPositone));
        animate.setAutoReverse(false);
        return animate;
    }

    boolean isWinner(){
        if(currentPositone == 100){
            return true;
        } return false;
    }

    public void startingPositione(){
        currentPositone = 0;
        movePlayer(1);
    }
    public Circle getCoin() {
        return coin;
    }

    public int getCurrentPositone() {
        return currentPositone;
    }

    public String getName() {
        return name;
    }

}
