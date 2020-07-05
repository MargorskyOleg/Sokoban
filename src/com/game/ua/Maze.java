package com.game.ua;

public class Maze {

    private int width;//ширина
    private int height;//высота

    public Maze(int width, int height) {
        this.width=width;
        this.height=height;

//        System.out.println("ширина "+width);
//        System.out.println("высота "+height);
    }

    public Coordinates matrix(){
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(i+""+j+" ");
            }
            System.out.println("");
        }
        return null;
    }
}