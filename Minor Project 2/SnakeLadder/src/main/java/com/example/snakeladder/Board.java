package com.example.snakeladder;

import javafx.util.Pair;

import java.util.ArrayList;

public class Board {
    ArrayList<Pair<Integer, Integer>> positionCoordinate;

    ArrayList<Integer> snackAndLadderPosition;

    public Board(){
        positionCoordinate = new ArrayList<Pair<Integer, Integer>>();
        
        popularPostionCoordinate();

        populateSnackAndLadder();
    }

    private void popularPostionCoordinate(){
        positionCoordinate.add(new Pair<>(0,0));
        for (int i = 0; i < SnakeandLadder.heihgt; i++) {
            for (int j = 0; j < SnakeandLadder.width; j++) {
                int xCord = 0;
                if(i%2 == 0){
                   xCord = j*SnakeandLadder.tileSize * SnakeandLadder.tileSize/2;
                } else {
                   xCord = SnakeandLadder.heihgt * SnakeandLadder.tileSize - (j *SnakeandLadder.tileSize) - SnakeandLadder.tileSize/2;
                }
                int yCord = SnakeandLadder.heihgt * SnakeandLadder.tileSize - (i *SnakeandLadder.tileSize) - SnakeandLadder.tileSize/2;
                positionCoordinate.add(new Pair<>(xCord,yCord));
            }

        }
    }

    private void populateSnackAndLadder(){
        snackAndLadderPosition = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            snackAndLadderPosition.add(i);
        }
        snackAndLadderPosition.set(4,25);
        snackAndLadderPosition.set(27,5);
        snackAndLadderPosition.set(33,49);
        snackAndLadderPosition.set(40,3);
        snackAndLadderPosition.set(42,63);
        snackAndLadderPosition.set(43,18);
        snackAndLadderPosition.set(50,69);
        snackAndLadderPosition.set(54,31);
        snackAndLadderPosition.set(62,81);
        snackAndLadderPosition.set(66,45);
        snackAndLadderPosition.set(76,58);
        snackAndLadderPosition.set(74,92);
        snackAndLadderPosition.set(89,53);
        snackAndLadderPosition.set(99,41);

    }
    public  int getNewPositione(int currentPosition){
        if(currentPosition > 0 && currentPosition<= 100)
            return snackAndLadderPosition.get(currentPosition);
        return -1;
    }


    int getXCoordinates(int position){
        if(position>=1 && position<=100)
           return positionCoordinate.get(position).getKey();
        return -1;
    }
    int getYCoordiantes(int position) {
        if(position >= 1 && position <= 100)
            return positionCoordinate.get(position).getValue();
        return -1;
    }
}
