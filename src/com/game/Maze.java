package com.game;

public class Maze {

    private int width;//ширина
    private int height;//высота
    private String levelCreate;
    private char[][] mazeLeberint;

    public Maze(String levelCreate,int width, int height) {
        this.levelCreate = levelCreate;
        this.width = width;
        this.height = height;
    }

    public void createMaze(){
        mazeLeberint = new char[height][width];
        characterPreparationToWriteToTheMatrix(levelCreate);
//        test();
    }

    public Coordinates characterWriteToTheMatrix(Character character){
        for (int i = 0; i < mazeLeberint.length; i++) {
            for (int j = 0; j < mazeLeberint[i].length; j++) {
                if(mazeLeberint[i][j] == 0) {
                    if (character != '|') {
                        mazeLeberint[i][j] = character;
                        return new Coordinates(i, j);
                    }
                }
            }
        }
        return null;
    }

    public void characterPreparationToWriteToTheMatrix(String levelCreate){
        for (int k = 0; k < levelCreate.length(); k++) {
            characterWriteToTheMatrix(levelCreate.charAt(k));
        }
    }

    public void test(){
        for (int i = 0; i < mazeLeberint.length; i++) {
            for (int j = 0; j < mazeLeberint[i].length; j++) {
                System.out.print(i+""+j+":"+mazeLeberint[i][j]+" ");
                System.out.print(mazeLeberint[i][j]);
            }
            System.out.println("");
        }
    }

}