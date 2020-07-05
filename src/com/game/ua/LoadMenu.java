package com.game.ua;

import java.util.Scanner;

public class LoadMenu {

    public static void loadMenuGame() {
//        RunLengthEncoding.encoding();
        Decoding.createLevel();
//        menuLoad();
    }

    private static void menuLoad(){
        System.out.println("RideFileMezeLevel = 1");
        System.out.println("Decoding          = 2");
        System.out.println("MenuGame          = 3");
        System.out.println("Exit              = 4");

        switch (textInput()){
            case 1: RunLengthEncoding.encoding() ;break;
            case 2: Decoding.createLevel()       ;break;
            case 3: MenuGame.menu()              ;break;
            case 4: test()                       ;break;
        }
    }

    public static int textInput(){
        Scanner s = new Scanner(System.in);
        System.out.print("textInput:");
        return s.nextInt();
    }

    private static void test(){
        System.out.println("Exit");
    }
}
