package com.game.ua;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class WriterLevelMaps {

    private static final String string = "src/com/game/ua/Level/MapsEncoding.txt";

    public static void fileWriter(String s) {//запись файла
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(string))){
            for (int i = 0; i < s.length(); i++) {
                bufferedWriter.append(s.charAt(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}