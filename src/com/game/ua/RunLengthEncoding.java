package com.game.ua;

/**
 *  Run-length encoding - https://en.wikipedia.org/wiki/Run-length_encoding
 * */

public class RunLengthEncoding {

    private static final String string = "src/com/game/ua/Level/Maps.txt";
    private static String sr = "";

    public static void encoding(){
        lengthEncoding(ReaderLevelMaps.fileReader(string));
        WriterLevelMaps.fileWriter(sr);
    }

    private static void lengthEncoding(String s) {
        int num = 0;
        for (int i = 0; i < s.length() -1; i+=1) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                num = num + 1;
                if(s.charAt(i+1) != s.charAt(i+2)){
                    num += 1;
                        for (int j = 0; j < String.valueOf(num).length(); j++){
                            convertToString(String.valueOf(num).charAt(j));
                        }
                    if(num != -1){
                        num = 0;
                    }
                }
            }
            if (s.charAt(i) != s.charAt(i+1)) {
                if(s.charAt(i) != ' '){
                    if (s.charAt(i+1) == '\n') {
                        convertToString('|');
                    }else if(s.charAt(i) != '\n'){
                        convertToString(s.charAt(i));
                    }
                }
                if (s.charAt(i) == ' ') {
                    convertToString('-');
                }
            }
            if(i+2 == s.length()){
                if(s.length()%2 == 1){
                    convertToString(s.charAt(i+1));
                }
            }
        }
    }

    private static String convertToString(char ch){
        return sr += Character.toString(ch);
    }

}