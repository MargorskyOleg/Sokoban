package com.game;

/**
 * ASCII text      = #          @          $          .          ;          1          |                     _
 * Hexacima(bytes) = 0x23       0x40       0x24       0x2E       0x3B       0x31       0x7C       0x20       0x5F
 * Binary (bytes)  = 0b00100011 0b01000000 0b00100100 0b00101110 0b00111011 0b00110001 0b01111100 0b00100000 0b01011111
 * Decimal (bytes) = 35         64         36         46         59         49         124        32         95
 *
 * # = wall
 * $ = container
 * @ = player
 * . = place for container
 * - = space road for the player
 * + = !
 * * = !
 *
 * */

public class Decoding{

    private static final String string = "src/com/game/Level/MapsEncoding.txt";
    private static final String stringNum = "0123456789";
    private static final String stringChar = "#$@.-+*";
    private static final char Semicolon = 0x3B;//';';
    private static final char VerticalLine = 0x7C;//'|';
    private static final String stringLevel = ReaderLevelMaps.fileReader(string);
    private static final int[] sizeIndexStartLevel = new int[SizeLevel(stringLevel,Semicolon)];//IndexStartLevel
    private static final int[] sizeIndexStopLevel  = new int[SizeLevel(stringLevel,Semicolon)];//IndexStopLevel
    private static String CodingLevel = "";//1
    private static String LevelCreate = "";//1
    private static int WIDTH;//5
    private static int HEIGHT;//8
//    public static Maze maze = new Maze(LevelCreate,WIDTH,HEIGHT);

    public static void createStringLevel(){
        levelSokoban();//++ index start and stop

        levelIndexSokoban(stringLevel,sizeIndexStartLevel,sizeIndexStopLevel);
        heightWidthLevelCreate(CodingLevel);

        Maze maze = new Maze(LevelCreate,WIDTH,HEIGHT);
        maze.createMaze();
    }

    private static void heightWidthLevelCreate(String s){//ширина доработать если два числа будут а не один
        int indexCharNext = 0,heightFor = 0,widthFor = 0;
        String tempInt = "";
        String ch = "";
        for (int i = 0; i < s.length(); i++) {//читать кодированою строку лаберинта > 5#|#@$.#|5#|
            if (s.charAt(i) != VerticalLine) {
                for (int j = 0; j < stringNum.length(); j++) {//++
                    if (s.charAt(i) == stringNum.charAt(j)) {//09
//                        System.out.printf("char %s Index %-2d\n", s.charAt(i), i);
                        tempInt += Character.toString(s.charAt(indexCharNext = i));//521 3 5
                    }
                }
                for (int j = 0; j < stringChar.length(); j++) {//++
                    if(s.charAt(i) == stringChar.charAt(j)) {
                        if(i == indexCharNext+1) {//##
                            ch = Character.toString(s.charAt(i));
//                            System.out.printf("char %s Index %-2d\n", s.charAt(i), i);
                            for (int k = 0; k < Integer.parseInt(tempInt); k++) {
                                LevelCreate += ch.replace("-"," ");
                                widthFor++;//ширина лаберинта доработать
                            }
                            tempInt = "";
                        }
                        else if(i != indexCharNext+1){//#@$.#
                            ch = Character.toString(s.charAt(i));
//                            System.out.printf("char %s Index %-2d\n", s.charAt(i), i);
                            LevelCreate += ch.replace("-"," ");
                            widthFor++;//ширина лаберинта доработать
                            tempInt = "";
                        }
                    }
                }
            }
            if (s.charAt(i) == VerticalLine) {
//                System.out.printf("char %s Index %-2d\n", s.charAt(i), i);
                HEIGHT = heightFor+=1;//высота лаберинта доработать
                widthFor = 0;
//                System.out.println(WIDTH+" "+ip);
                LevelCreate += "|";//"\n"
//                System.out.printf("charOu %s charIn %-2d\n", WIDTH, widthFor);
            }
            if(WIDTH <= widthFor){
                WIDTH = widthFor;
//                System.out.printf("charOu %s charIn %-2d\n", WIDTH, widthFor);
            }
        }
    }

    private static void levelIndexSokoban(String stringLevel, int[] sizeIndexStartLevel, int[] sizeIndexStopLevel) {//читать закодированный уровень
        int size = 0;//sizeLevelEncoding
        int L = 0;//next level
        for (int i = sizeIndexStartLevel[L]; i <= sizeIndexStopLevel[L]; i++) {
            if(stringLevel.charAt(i) != sizeIndexStopLevel.length){
                size+=1;
                CodingLevel += stringLevel.charAt(i);
            }
        }
//        System.out.printf("Size Chars Encoding %d\n",size);//sizeLevelEncoding
//        System.out.printf("Start %d> %s <%d Stop\n",sizeIndexStartLevel[L],CodingLevel,sizeIndexStopLevel[L]);//sizeLevelEncodingLength
    }

    private static int SizeLevel(String s, char c) {//подсчет во всех уровнях > ;
        int o = 0, index = s.indexOf(c);
        while (index != -1){
            o += 1;
//            System.out.println(o);
//            System.out.println(index+" "+s.charAt(index)+" "+s.indexOf(index));
            index = s.indexOf(c,index+1);
        }
        return o;
    }

    private static void levelSokoban() {
        int k = 0,w = 0;
        for (int i = 0; i < stringLevel.length(); i++) {
            if(0 == i){
//                System.out.print(stringLevel.charAt(i));//start 0 index
//                System.out.print(i+" ");
                sizeIndexStartLevel[k] = i;
            }
            if(stringLevel.charAt(i) == VerticalLine && stringLevel.charAt(i+1) == VerticalLine){
//                System.out.print(stringLevel.charAt(i+2));//start level
//                System.out.print(i+2+" ");
                k++;
                sizeIndexStartLevel[k] = i+2;
            }
            if(stringLevel.charAt(i) == Semicolon){//stop level
//                System.out.println(stringLevel.charAt(i-2));
//                System.out.println(i-2);
                sizeIndexStopLevel[w] = i-1;
                w++;
            }
        }
    }
}

// Level 0  //5#|#@$.#|5#|
// Level 1  //5#|#@$.#|#-$.#|5#|
// Level 2  //8#2-#|#2-#3-#|#3-$2-#|#-$2#-5#|2#-#.-2#2-#|-#-#.3-$-#|-#-#.-2#2-#|-#-#.3#-2#|-#@3-2#2-#|-2#-$5-#|2-6#2-#|#6-4#|
// Level 3  //13#|#3-2#6.#|#-$7-$-#|2#-#-#$3#2-#|2#$6-2#-#|2#2-3#2-2#-#|7#-3#-#|#-$-$5-#-#|#@3-#2-#3-#|13#|
// Level 4  //4#|#-.#|#-.3#|#-.$-2#|#-.$2-#|#-.$2-#|2#-2$-#|-3#@-#|3-4#|
// Level 5  //5#|#2-.#|#2-.3#|2#-$.-3#|-#-$.-$-#|-#-$.-$@#|-#2-5#|-4#|
// Level 6  //7#|4#.2#|3#2.-#|#-$3-#|#-$#.-#|#2-@.2#|#-$2-2#|#-2$-2#|3#2-2#|7#|
// Level 7  //9#|5#3-#|3#4-$#|#4-2$-#|#2-2$-$-#|#-$-$-$-#|#-$-$-$-#|#-$-$-$-#|#-$-$-$-#|2#$-$-$-#|2#-3#-@#|#7.#|#7.#|#7.#|9#|
// Level 8  //13#|#2-.-.3-.2-#|#-#-#-#-#-#-#|#-#-#-#-#-#-#|#.$-#-$-#-$.#|#-#-#-#.#-#-#|#-#3$#3$#-#|#-#-#-#-#-#-#|#.$-#-$-#-$-#|#-#-#-#-#-#.#|#-#-#-#-#-#-#|#2-.-.@.-.2-#|13#|
// Level 9  //9#|3#2-4#|3#2-4#|#2-*-$2-#|#2-2*-.-#|3#2-#-2#|4#-#-2#|4#-@-2#|9#|
// Level 10 //7#|#-@2-.#|#2-2*$#|#-$.$.#|3#-*-#|3#-*-#|3#3-#|3#3-#|7#|
// Level 11 //-17#|2#15-2#|#2-6#-6#2-#|#-2#11-2#-#|#-#2-9$2-#-#|#-#-$9.$-#-#|#-#-$.3-.3-.$-#-#|#-#-$.-$-+3-.$-#-#|#3-$2.$.#.$2.$3-#|#-#-$.3-.-$-.$-#-#|#-#-$.3-.3-.$-#-#|#-#-$9.$-#-#|#-#2-9$2-#-#|#-2#11-2#-#|#2-6#-6#2-#|2#15-2#|-17#|
// Level 12 //12#|8#-@2#|5#4-3#|4#-2$-4#|4#-#-2#2-#|4#-#-*3-#|4#-$-.#.-#|2#4-.-#-2#|#-2$-2$.#$2#|#-#2-#2-.-2#|#-#-.3-4#|#-*-.-.-4#|#-#.-#-5#|#-2$-#$5#|#-#2-.-5#|#-2#.7#|#4-7#|12#|
// Level 13 //15#|#13-#|#-*-*-*-*-*-*-#|#2-*-*-*-*-*2-#|#-*-*-*-*-*-*-#|#2-*-*-*-*-*2-#|#-*-*-*-*-*-*-#|#2-*-*$@.*-*2-#|#-*-*-*-*-*-*-#|#2-*-*-*-*-*2-#|#-*-*-*-*-*-*-#|#2-*-*-*-*-*2-#|#-*-*-*-*-*-*-#|#13-#|15#|
// Level 14 //-29#|2#27-2#|#-*-*-*-*-*-*-*-*-*-*-*-*-*-*-#|#2-*-*-*-*-*-*-*-*-*-*-*-*-*2-#|#-*-*-*-*-*-*-*-*-*-*-*-*-*-*-#|#2-*-*-*-*-*-*-*-*-*-*-*-*-*2-#|#-*-*-*-*-*-*-*-*-*-*-*-*-*-*-#|#2-*-*-*-*-*-*-*-*-*-*-*-*-*2-#|#-*-*-*-*-*-*-*-*-*-*-*-*-*-*-#|#2-*-*-*-*-*-*-*-*-*-*-*-*-*2-#|#-*-*-*-*-*-*-*$*-*-*-*-*-*-*-#|#2-*-*-*-*-*-*.@.*-*-*-*-*-*2-#|#-*-*-*-*-*-*-*$*-*-*-*-*-*-*-#|#2-*-*-*-*-*-*-*-*-*-*-*-*-*2-#|#-*-*-*-*-*-*-*-*-*-*-*-*-*-*-#|#2-*-*-*-*-*-*-*-*-*-*-*-*-*2-#|#-*-*-*-*-*-*-*-*-*-*-*-*-*-*-#|#2-*-*-*-*-*-*-*-*-*-*-*-*-*2-#|#-*-*-*-*-*-*-*-*-*-*-*-*-*-*-#|#2-*-*-*-*-*-*-*-*-*-*-*-*-*2-#|#-*-*-*-*-*-*-*-*-*-*-*-*-*-*-#|2#28-#|-30#|
// Level 15 //16#|#*#3*2#4.2-2#|3#3-#3*-#$-.#|#*#$-#3-*#-$-$#|2#2-2.-#2-#-2$-#|#3-2*#-#6-#|#2-$2.4-#.2$-#|2#-3#-$2-#$@.-#|#*4-2#$#.3$.#|#2*2#4-2#2.-2#|16#|
// Level 16 //13#|2#*#*#6-#|#*#*2#-4#-#|6#-$2-#-#|2#-*-$*$#-#-#|2#2-3.3-#-#|3#-2#2-3#-#|2#2-*2-2#-#-#|3#-#-$-$3-#|2#2-*-3.2-2#|3#*#2*$#-3#|#-$-$-#-*3-#|#-.-.3-2.$-#|3#@2#-#-$3#|2#5-#3-2#|#-*#-*#-#-3#|2#6-#*3#|2#-.$2*.-2#*#|#*-2$.$*2-3#|4#-3.2-3#|3#-$-$2-2#*#|3#*3-2#*3#|13#|
// Level 17 //25#|#23-#|#23-#|#2-$#$-$-$-$-$-$-$-$-2$-21#|#3-$-$-$-$-$-$-$-$-$3-#19-#|#2-$-$-$-$-$-$-$-$-$-$2-#-#-#-#-#-#-#-#-#-3#|#3-$-$-$-$-$-$-$-$-$22-2#|#2-$-$-$-$-$-$-$-$-$-$2-#.-.-.-.-.-.-.-.-3.#|#3-$-$-$-$-$-$-$-$-$2-2#19.#|#2-$-$-$-$-$-$-$-$-$-$3#19.#|#3-$-$-$-$-$-$-$-$-$3-#19.#|#2-$-$-$-$-$-$-$-$-$-$#-#19.#|#3-$-$-$-$-@-$-$-$-$3-#19.#|#2-$-$-$-$-$-$-$-$-$-$2-#19.#|#3-$-$-$-$-$-$-$-$-$-#-#19.#|#2-$-$-$-$-$-$-$-$-$-$2-#19.#|#3-$-$-$-$-$-$-$-$-$3-#19.#|#2-$-$-$-$-$-$-$-$-$-$#-21#|#3-$-$-$-$-$-$-$-$-$2-2#|#2-$-$-$-$-$-$-$-$-$-$3#|#2-#$-$-$-$-$-$-$-$-$3-#|#2-$-$-$-$-$-$-$-$-$-2$-#|#23-#|#23-#|25#|
// Level 18 //9#|#.-.-.2-#|#.$.2-$-#|2#-3#@-#|-#2-$2-2#|-#-2$-2#|-#-.$-#|-#2-3#|-4#|
// Level 19 //35#|#5.-$-$-$-$-$3-$-$-$-$-$-5.#|#5.2-$-$-$-$-$-$-$-$-$-$2-5.#|#5.-$-$-$-$-$3-$-$-$-$-$-5.#|#5.2-$-$-$-$-$-$-$-$-$-$2-5.#|#5.-$-$-$-$-$3-$-$-$-$-$-5.#|#5.2-$-$-$-$-$-$-$-$-$-$2-5.#|#5.-$-$-$-$-$3-$-$-$-$-$-5.#|#5.2-$-$-$-$-$-$-$-$-$-$2-5.#|#5.-$-$-$-$-$3-$-$-$-$-$-5.#|#5.2-$-$-$-$-$@$-$-$-$-$2-5.#|#5.-$-$-$-$-$3-$-$-$-$-$-5.#|#5.2-$-$-$-$-$-$-$-$-$-$2-5.#|#5.-$-$-$-$-$3-$-$-$-$-$-5.#|#5.2-$-$-$-$-$-$-$-$-$-$2-5.#|#5.-$-$-$-$-$3-$-$-$-$-$-5.#|#5.2-$-$-$-$-$-$-$-$-$-$2-5.#|#5.-$-$-$-$-$3-$-$-$-$-$-5.#|#5.2-$-$-$-$-$-$-$-$-$-$2-5.#|#5.-$-$-$-$-$3-$-$-$-$-$-5.#|#5.2-$-$-$-$-$-$-$-$-$-$2-5.#|35#|
// Level 20 //32-12#|32-#10-#|32-#10-#|32-#-6#-3#|32-#8-9#|40#.9#|#37-3.9#|#@9$#17$#$9#.8-#|#30-2#7.-7#-#|#-10$#15$#2$2#7.-6.#-#|#30-2#7.-6.#-#|#-11$#13$#3$#8.-6.#-#|#30-#8.-6.#-#|#-12$#11$#4$#8.-6.#-#|#30-#8.-6.#-#|#-13$#9$#5$#8.-6.#-#|#30-#15.#-#|#-14$#7$#6$#15.#-#|#30-#15.#-#|#-15$#5$#7$#15.#-#|#30-#15.#-#|#-16$#3$#8$#15.#-#|#30-#15.#-#|#-17$#$#9$#15.#-#|#19-.10-#15.#-#|#-18$#10$#15.#-#|#30-#15.#-#|#-17$#11$#15.#-#|#30-#15.#-#|#-16$#11$2#15.#-#|#30-#15.#-#|#-15$#12$2#15.#-#|#30-#15.#-#|#-14$#13$3#14.#-#|#30-2#6.-.-5.#-#|#-2#$11#11$6#6.-.-6#-#|#2-#-#-#-#-#-#-#-$-$-$-$-$-#-#-17#-#|#48-#|50#|
// Level 21 //4-61#|4-#7-*3-*3-*5-*3-*3-*11-*3-*3-*9-#|2-3#-.*.*.*.2-*.*2-.*.2-*.-.*.*2-.*.*.*.*.2-*.-.*.*2-.*.*.*.*.-3#|2-#3-$-$-$-$-*-$-*-$-$3-$-$-$-*-$-$-$-$-$3-$-$-$-*-$-$-$-$-$3-#|3#-2#-18#-17#-18#-2#-3#|#3-#-$-.-*-*-*-*-*-$-*3-*-*-*-*-*-*-*3-*3-*-*-*-*-*-*-*3-.-#3-#|#4-.#-#$4-#4-.#-2#-2#-#5-#4-*#-2#-2#-#5-#4-*#-#$-$.-#|#-.$#5-9#11-9#11-9#2-@2-#-*-#|#-*-#.#-#5-#5-#-#-#-#-#.4-#4-$#-#-#-#-#*4-#4-$#-#*#$.*#|#*.$#-$-*-*-*-*-*-*-*-*-*-*-$-*-*-*-*-*-.-*-*-*3-*-*-*-*-*-.3-#3-#|#3-#*-#-*4#$4#*-5#-#-*4#$4#*-3#-#-#-*4#-4#*-#-*#$.-#|#2-*#2-#2-#.5-.#2-#-2.#-#2-#.5-.#2-#-#-#-#2-#.5-.#2-#2-#-*-#|#-*-#*-#-*#-#$3#.#*-#-2.#-#-*#.3#$#.#*-#-#-#-#-*#.3#$#.#*-#-*#$.*#|#*.$#2-#2-#-#3-$-#2-#2-2#-#2-#-#3-$-#2-#-3$-#2-#-$3-#-#2-#2-#-*-#|#-*-#*3#*$-#-#-#2-*2#2-$2-2#*2-#-#-#-$*2#2-$2-2#*$-#-#-#-$*3#*#*2-#|#2-*#2-#2-#-$3-#.#2-#-3$-#2-#-$3-#-#2-#-2#2-#2-#-#3-$-#2-#2-#3-#|#3-#*-#-*#-3#$#.#*-#-#-#-#-*#.#$3#.#*-#-#2.-#-*#-#$3#.#*-#-*#$.*#|#*.$#2-#2-#.5-.#2-#-#-#-#2-#6-.#2-#-#2.-#2-#.5-.#2-#2-#-*-#|#-*-#*-#-*4#$4#*-#-#-3#-*4#$4#*-#-5#-*4#$4#*-#-*#$.-#|#-.$#-.-$-*-*-*-*-*-.-*-*-*-.-*-*-*-*-*-$-*-*-*-.-*-*-*-*-*-$-$-#-*-#|#-*-#$#-#.4-#4-$#-#-#-#-#$4-#4-.#-#-#-#-#$4-#4-.#-#.#$.-#|#-.$#5-9#11-9#11-9#5-#-*-#|#-*2-$#-#*4-#5-#-2#-2#-#*4-#5-#-2#-2#-#.4-#5-#-#2-$.-#|#3-#-.3-*-*-*-*-*-*-*3-*3-*-*-*-*-*-*-*3-*-$-*-*-*-*-*-*3-#3-#|3#-2#-18#-19#-16#-2#-3#|2-#4-$-$-$-$-*-$-*-$4-$-*-$-*-$-$-$-$-$2-$-$-*-$-*-$-$-$-$4-#|2-3#2-.*.*.*.2-*.*2-.*2-*.2-*.*2-.*.*.*.*.2-.*.2-*.*2-.*.*.*.*-3#|4-#8-*3-*3-*4-*3-*3-*12-*3-*3-*8-#|4-61#|
// Level 22 //4-55#|4-#5-*3-*3-*5-*3-*3-*9-*3-*3-*7-#|2-3#2-*.*.2-*.*2-.*.2-*.2-*.*2-.*.*.*.2-*.2-*.*2-.*.*.*.-3#|2-#5-$-$-*-$-*-$-$3-$-*-$-*-$-$-$-$3-$-*-$-*-$-$-$-$3-#|3#-2#-14#-17#-16#-2#-3#|#3-#3-.-*-*-*-*3-*3-*3-*-*-*-*3-*3-*3-*-*-*-*3-$-#3-#|#-.$-*#-#$4-#2-*#-2#-2#-#*2-#4-*#-2#-2#-#*2-#4-*#-#.-$.-#|#-*-#5-7#11-7#11-7#2-@2-#-*-#|#-.$#-#-#*2-#5-#-#-#-#-#$4-#2-.#-#-#-#-#5-#2-.#-#$#$.-#|#-*-#-*3-*-*-*-*-*-*-*-*-.-*-*-*-*-$-*-*-*-*-*-*-*-*-$-.-#-*-#|#-.$#*-#-*2#$4#*-#$5#-*4#-2#*-5#$#-*4#$2#*-#-*#$.-#|#-*-#2-#2-#.3-.#2-#2-.2-#2-#.3-.#2-#2-.2-#2-#2.2-.#2-#2-#-*-#|#*.$#*2#-*#-#$#-#*-#-.-$-2#*$-#$#-$*2#-$-.-#-*#-#.#-$*2#-*#$.*#|#3-#2-#2-#-$-$-#2-#.#-#.#2-#-.-$-#2-#.#-#.#2-#-$-$-#2-#2-#3-#|#2-*#*-2#*$-#.#2-*2#-$-$-#-*#-#$#.#*-#-$-$-2#*2-#$#-#*-2#*#*2-#|#-*-#2-#2-#.2-2.#2-#2-.2-#2-#.3-.#2-#2-.2-#2-#.3-.#2-#2-#-*-#|#*.$#*-#-*2#$4#*-5#$#-*4#$2#*-#$5#-*2#$4#*-#-*#$.*#|#-*-#3-$-*-*-*-*-.-*-*-*-*-*-*-*-*-.-*-*-*-$-*-*-*-*-.-$-#-*-#|#2-*#*#-#.2-#4-$#-#-#-#-#5-#2-$#-#-#-#-#.2-#4-$#-#.#*2-#|#3-#5-7#3-$-$-$3-7#3-$-$-$3-7#5-#3-#|#*.$#*#-#*#6-*#.#-$2-.#-.5-#*#.2-$-#.#3-#3-#*#-#*#$.*#|#-*-#-#3-#-#-3#4-.*.2-#-#-#$#-#4-.*.2-#-#-2$#-#3-#-#-*-#|#3-#3-#*#-2$#.#*#.$*#*$.#*#-$-$-#*#.$*#*$.#*#.$3-#*#3-#$.-#|#5-#3-2#$-2.#4-.*.#3-#-#-#.2-#-#.*.4-#.-#-$.-#-#5-#|#-.$#*#-#*6-#*#.2-$2-.#*#-.3-#-#.2-$2-.#*#.4-#-#-#*#3-#|#-*-#5-7#3-$-$-$3-7#3-$-$-$3-7#5-#-*-#|#*.$#$#-#5-#2-.#-#-#-#-#*2-#5-#-#-#-#-#$4-#3-#-#.#$.*#|#3-#-.-*-*-*-*-*-$-*-*-*3-*-*-*-*-*-*-*-*-.-*-*-*-*-*-$-#3-#|#2-*#*-#-*4#$2#*-5#-#-*2#$4#*-#-#-3#-*4#$2#*-#-*#*2-#|#-*-#2-#2-#.3-.#2-#-2.#-#2-#.3-.#2-#-#-#-#2-#.3-.#2-#2-#-*-#|#*.$#*-2#*$-#$#-#*-#-.2#-2#*$-#.#-#*-#-3$-2#*2-#$#-$*2#-*#$.*#|#-*-#2-#2-#-$-.-#2-#-.$2-#2-#-$-$-#2-#-.$2-#2-#-.-$-#2-#2-#-*-#|#2-*#*2#-*#-#$#-$*2#-3$-#-*#-#$#2-*2#-.2#-#-*#-#$#-#*-2#*#*2-#|#3-#2-#2-#.2-2.#2-#-#-#-#2-#2.2-.#2-#-2.#-#2-#.2-2.#2-#2-#3-#|#*.$#*-#-*2#-4#*-#-#-3#-*4#$2#*-5#-#-*2#$4#*-#-*#$.*#|#-*-#3-$-*-*-*-*-.-*-*-*-$-*-*-*-*-*-*-*-*-*-*-*-*-*3-.-#-*-#|#-.$#*#-#.2-#4-$#-#-#-#-#.4-#3-#-#-#-#-#3-#4-*#-#$#$.-#|#-*-#5-7#11-7#11-7#5-#-*-#|#4-.#-#$4-#2-.#-2#-2#-#*2-#4-*#-2#-2#-#*4-#3-#-#$-$.-#|#3-#5-*-*-*-*-$-*3-*3-*-*-*-*3-*3-*3-*-*-*-*-*-.-#3-#|3#-2#-16#-17#-14#-2#-3#|2-#3-$-$-$-$-*-$-*-$3-$-$-*-$-*-$-$-$3-$-$-*-$-*-$-$5-#|2-3#-.*.*.*.2-*.*2-.*2-.*.2-*.*2-.*.*.*2-.*.2-*.*2-.*.*2-3#|4-#7-*3-*3-*5-*3-*3-*9-*3-*3-*5-#|4-55#|

// 1 | 2  3  4  5  | 6  7  8  9 |
//-------------------------------
// 2 | 4  6  8  10 | 12 14 16 18|
// 3 | 6  9  12 15 | 18 21 24 27|
// 4 | 8  12 16 20 | 24 28 32 36|
// 5 | 10 15 20 25 | 30 35 40 45|
//-------------------------------
// 6 | 12 18 24 30 | 36 42 48 54|
// 7 | 14 21 28 35 | 42 49 56 63|
// 8 | 16 24 32 40 | 48 56 64 72|
// 9 | 18 27 36 45 | 54 63 72 81|
//-------------------------------

























































// 1 | 2  3  4  5  | 6  7  8  9 |
//-------------------------------
// 2 | 4  6  8  10 |            |
// 3 |    9  12 15 |            |
// 4 |       18 20 |            |
// 5 |          25 |            |
//-------------------------------
// 6 |             |            |
// 7 |             |            |
// 8 |             |            |
// 9 |             |            |
//-------------------------------




