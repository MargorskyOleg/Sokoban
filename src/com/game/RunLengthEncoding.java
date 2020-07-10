package com.game;

/**
 *  Run-length encoding - https://en.wikipedia.org/wiki/Run-length_encoding
 * */

public class RunLengthEncoding {

    private static final String string = "src/com/game/Level/Maps.txt";
    private static String sr = "";

    public static void encoding(){//returned to redo the function!
        lengthEncoding(ReaderLevelMaps.fileReader(string));
        WriterLevelMaps.fileWriter(sr);
    }

    private static void lengthEncoding(String s) {
        int num = 0;
        for (int i = 0; i <= s.length() -2; i++) {

            if(s.charAt(i) == ';'){//;...
//                System.out.print(s.charAt(i) + " ! ");
                while (s.charAt(i) != (char)13){//;...|
//                    System.out.print(" ["+s.charAt(i++)+"] ");//...
                    convertToString(s.charAt(i++));
                }
            }

            if (s.charAt(i) == s.charAt(i+1)) {
                num = num + 1;
                if(s.charAt(i+1) != s.charAt(i+2)){
                    num += 1;
                        for (int j = 0; j < String.valueOf(num).length(); j++){//! тут нужна доработка в уравнях например ;11 > ;21 две единицы но алгоритм как не странно раборает правилино!
                            convertToString(String.valueOf(num).charAt(j));//сбор
                        }
                    if(num != -1){
                        num = 0;
                    }
                }
            }
            if (s.charAt(i) != s.charAt(i+1)) {
                if(s.charAt(i) != ' '){
                    if (s.charAt(i+1) == '\n') {
                        convertToString('|');//сбор '|'
                    }else if(s.charAt(i) != '\n'){
                        convertToString(s.charAt(i));//сбор
                    }
                }
                if (s.charAt(i) == ' ') {
                    convertToString('-');//сбор '-'
                }
            }
            if(i+2 == s.length()){
//                if(s.length()%2 != 1){
                    convertToString(s.charAt(i+1));//сбор 1234 ... '5'
//                }
            }
        }
    }

    private static String convertToString(char ch){
//        System.out.println(ch);
        return sr += Character.toString(ch);
    }

}