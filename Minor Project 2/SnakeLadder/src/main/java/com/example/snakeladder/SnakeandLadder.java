package com.example.snakeladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeandLadder extends Application {
    public static final int tileSize =40 , width = 10, heihgt = 10;
    public static final int buutonLine = heihgt*tileSize + 30 , infoLine = buutonLine - 30;

    private static Dice dice = new Dice();

    private Player playerone, playertwo;

    private boolean gameStart = false, playerOneTurn = false, playerTwoTurn = false;

    private Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(tileSize*width, tileSize*heihgt+100);


        for (int i = 0; i < heihgt; i++) {
            for (int j = 0; j < width; j++) {
                Tile tile = new Tile(tileSize);
                tile.setTranslateX(j * tileSize);
                tile.setTranslateY(i * tileSize);
                root.getChildren().add(tile);
            }
        }
        Image image = new Image("C:\\Users\\win 10\\Desktop\\Minor Project 2\\SnakeLadder\\src\\main\\istockphoto-531466314-1024x1024.jpg");
        ImageView board = new ImageView();
        board.setImage(image);
        board.setFitHeight(heihgt*tileSize);
        board.setFitWidth(width*tileSize);

        //Botton
        Button playerOneButton = new Button("PLAYER 1");
        Button playerTwoButton = new Button("PLAYER 2");
        Button startButtone = new Button("START");

        playerOneButton.setTranslateY(buutonLine);
        playerOneButton.setTranslateX(20);
        playerOneButton.setDisable(true);
        playerTwoButton.setTranslateY(buutonLine);
        playerTwoButton.setTranslateX(300);
        playerTwoButton.setDisable(true);
        startButtone.setTranslateY(buutonLine);
        startButtone.setTranslateX(160);

        //Info Display
        Label playerOneLable = new Label("");
        Label playerTwoLable = new Label("");
        Label diceLable = new Label("START GAME");

        playerOneLable.setTranslateY(infoLine);
        playerOneLable.setTranslateX(20);
        playerTwoLable.setTranslateY(infoLine);
        playerTwoLable.setTranslateX(300);
        diceLable.setTranslateY(infoLine);
        diceLable.setTranslateX(160);

        playerone = new Player(tileSize, Color.WHEAT, " Niket");
        playertwo = new Player(tileSize -5, Color.ORANGE, " Vansh");

        //Moving Player
        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStart){
                    if(playerOneTurn){
                        int diceValue = dice.getRolledDiceValue();
                        diceLable.setText("Dice Value : " + diceValue);
                        playerone.movePlayer(diceValue);
                        //Winner
                        if(playerone.isWinner()){
                            diceLable.setText("Winner is " + playerone.getName());
                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLable.setText("");
                            playerTwoTurn = true;
                            playerTwoButton.setDisable(true);
                            playerTwoLable.setText("");

                            startButtone.setDisable(false);
                            startButtone.setText("Restart Game");
                        }else {
                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLable.setText("");

                            playerTwoTurn = true;
                            playerTwoButton.setDisable(false);
                            playerTwoLable.setText("TOUR TURN" + playertwo.getName());
                        }
                    }
                }
            }
        });

        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStart){
                    if(playerTwoTurn){
                        int diceValue = dice.getRolledDiceValue();
                        diceLable.setText("Dice Value" + diceValue);
                        playertwo.movePlayer(diceValue);
                        //Winner
                        if(playertwo.isWinner()){
                            diceLable.setText("Winner is " + playertwo.getName());
                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLable.setText("");
                            playerTwoTurn = true;
                            playerTwoButton.setDisable(false);
                            playerTwoLable.setText("");

                            startButtone.setDisable(false);
                            startButtone.setText("Restart Game");

                        } else {
                            playerOneTurn = true;
                            playerOneButton.setDisable(false);
                            playerOneLable.setText("TOUR TURN" + playerone.getName());

                            playerTwoTurn =false;
                            playerTwoButton.setDisable(false);
                            playerTwoLable.setText("");
                        }
                    }
                }
            }
        });

        startButtone.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStart = true;
                diceLable.setText("GAME STARTED");
                startButtone.setDisable(true);
                playerOneTurn = true;
                playerOneLable.setText("YOUR TURN" + playerone.getName());
                playerOneButton.setDisable(false);
                playerone.startingPositione();

                playerTwoTurn = false;
                playerTwoLable.setText("");
                playerTwoButton.setDisable(true);
                playertwo.startingPositione();
            }
        });


        root.getChildren().addAll(board, playerOneButton,playerTwoButton,startButtone, playerOneLable,playerTwoLable,diceLable, playerone.getCoin(), playertwo.getCoin());

        return root;
    }


    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake Ladder Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}