package com.game.ua;

/**
 * ASCII text      = #          @          $          .          ;          1          |                     _
 * Hex (bytes)     = 0x23       0x40       0x24       0x2E       0x3B       0x31       0x7C       0x20       0x5F
 * Binary (bytes)  = 0b00100011 0b01000000 0b00100100 0b00101110 0b00111011 0b00110001 0b01111100 0b00100000 0b01011111
 * Decimal (bytes) = 35         64         36         46         59         49         124        32         95
 * */

public class Decoding {

    private static final String string = "src/com/game/ua/Level/MapsEncoding.txt";
    private static final String LEVEL = "Level:";

    public static void createLevel(){
        numAllLevelControl(ReaderLevelMaps.fileReader(string));
    }

//5#|#@$.#|5#|;1||5#|#@$.#|#-$.#|5#|;2||8#2-#|#2-#3-#|#3-$2-#|
//            !!                    !!

    private static int numAllLevelControl(String string){//all Level 3
        int stopLevel = 0;
        int st;
        for (int i = 0; i <= string.length() -1; i++) {
//            System.out.print(string.charAt(i));
            if(string.charAt(i) == ';'){//; 3
                stopLevel++;
                if (stopLevel == levelNum(string,string.indexOf(';', i)+1)) {
                    System.out.print("level: "+stopLevel);
                    System.out.print(" index "+i);
                    System.out.println(" num "+levelNum(string,string.indexOf(';', i)+1));
                }
                if (stopLevel != levelNum(string,string.indexOf(';', i)+1)) {
                    System.out.print("level: "+stopLevel);
                    System.out.print(" index "+i);
                    System.out.print(" num "+levelNum(string,string.indexOf(';', i)+1));
                    System.out.println(" not level !");
                    System.out.println(string.charAt(i));
                    st = levelNum(string,string.indexOf(';', i)+1);

                    System.out.println(" "+st+" ");

                }
            }
        }
        return 0;
    }

    private static void nums(String s, int i){
        while ('|' != string.charAt(i)){
            i++;
            System.out.print(string.charAt(i));
        }
        System.out.println(" ");
    }

    private static int levelNum(String string, int index){
        String s = "";
        while(string.charAt(index) != '|'){
            s += Character.toString(string.charAt(index));
            index++;
        }
        return Integer.parseInt(s);
    }

    private static void height(){//высота

    }

    private static void width(){//ширина

    }

//level: 1 index 12 num 31 not level !
//;
// 31
//level: 2 index 35 num 2
//level: 3 index 150 num 3
//level: 4 index 247 num 4
//level: 5 index 309 num 5
//level: 6 index 369 num 6
//level: 7 index 438 num 7
//level: 8 index 553 num 8
//level: 9 index 716 num 9
//level: 10 index 785 num 10
//level: 11 index 846 num 21 not level !
//;
// 21
//level: 12 index 1069 num 12
//level: 13 index 1239 num 13
//level: 14 index 1440 num 14
//level: 15 index 2078 num 15
//level: 16 index 2224 num 16
//level: 17 index 2489 num 17
//level: 18 index 3088 num 18
//level: 19 index 3163 num 19
//level: 20 index 3766 num 20
//level: 21 index 4356 num 21
//level: 22 index 5853 num 22
//level: 23 index 8164 num 23


}
