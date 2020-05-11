package com.game.ua;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//    Decoding.createLevel();
        menuLoad();
    }

    private static void menuLoad(){
        System.out.println("rideFileMezeLevel = 1");
        System.out.println("Decoding          = 2");
        System.out.println("                  = 3");
        System.out.println("Exit              = 4");

        switch (textInput()){
            case 1: RunLengthEncoding.encoding() ;break;
            case 2: Decoding.createLevel()       ;break;
            case 3:                               break;
            case 4: test()                       ;break;
        }
    }

    private static int textInput(){
        Scanner s = new Scanner(System.in);
        System.out.print("textInput:");
        return s.nextInt();
    }

    private static void test(){
        System.out.println("Exit");
    }

}