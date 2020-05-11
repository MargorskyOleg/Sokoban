package com.game.ua;

import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReaderLevelMaps {

    public static String fileReader(String s) {//чтения файла
        int numChar;
        String string = "";
            try {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(s)));
                while ((numChar = bufferedInputStream.read()) != -1){
                    string += (char)numChar;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
        }
        return string;
    }

}