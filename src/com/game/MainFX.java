package com.game;

import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.InputStream;

public class MainFX extends Application {
/**
 *                          Information about the status of done the project ENG - RUS
 *
 * ENG
 * process summary about creating the Sokoban game links to technology sources
 *|+    |= done
 *| $   |= on development stage
 *|  @  |= redo
 *|   . |= following development process
 *|    !|= difficult to develop
 *|-----|
 *|stage|№  | process                                                           |  coordinates                            |  source link
 *|-----|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
 *|+    |1  |=create a maze                                                     |= "src/com/game/ua/Level/Maps.txt"       |                                                   |
 *|+ @  |2  |=encode the length of the sequence maze and writing to text        |= RunLengthEncoding.java                 |= https://en.wikipedia.org/wiki/Run-length_encoding|
 *|+ @  |3  |=decoding the length of the maze sequence                          |= Decoding.java MapsEncoding.txt         |                                                   |
 *|+ @  |4  |=determine the height of the width and length of the maze          |= Decoding.java heightWidthLevelCreate() |                                                   |
 *|+ @  |5  |=create a matrix and fill the sequence of maze characters          |= Maze.java                              |                                                   |
 *| $ .!|6  |=graphical interface Java FX                                       |                                         |                                                   |
 *|   . |7  |=game control mechanics                                            |= GameMechanics.java                     |                                                   |
 *|     |8  |=game menu                                                         |                                         |                                                   |
 *|     |9  |=save game events in a maze to text or SQL                         |                                         |                                                   |
 *|     |10 |=save game level                                                   |                                         |                                                   |
 *|     |11 |=game loading                                                      |                                         |                                                   |
 *|     |12 |=loading levels                                                    |                                         |                                                   |
 *|     |13 |=level selection after completed                                   |                                         |                                                   |
 *|     |14 |=information statistics - game time                                |                                         |                                                   |
 *|    !|15 |=texturing the maze                                                |                                         |                                                   |
 *|    !|16 |=game sounds                                                       |                                         |                                                   |
 *|    !|17 |=3d graphical interface                                            |                                         |                                                   |
 *|    !|18 |=neural network                                                    |                                         |                                                   |
 *|    !|19 |=deep learning                                                     |                                         |                                                   |
 *|     |20 |=create exe                                                        |                                         |                                                   |
 *-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 *
 * RUS
 * краткая информация процессов о создании игры Сокобан ссылки на источники по технологий
 *|+    |= готово
 *| $   |= на стадии разработки
 *|  @  |= переделать
 *|   . |= следующие процессе разработки
 *|    !|= сложно в разработке
 *|-----|
 *|этап |№  | процес                                                            |  координаты                             |  ссылка на источник
 *|-----|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
 *|+    |1  |=создать лабиринт                                                  |= "src/com/game/ua/Level/Maps.txt"       |                                                   |
 *|+ @  |2  |=кодировать длину последовательности лабиринта и запись в текст    |= RunLengthEncoding.class                |= https://en.wikipedia.org/wiki/Run-length_encoding|
 *|+ @  |3  |=раскодирования длину последовательности лабиринта                 |= Decoding.class MapsEncoding.txt        |                                                   |
 *|+ @  |4  |=определить высоту ширину и длину лабиринта                        |= Decoding.class heightWidthLevelCreate()|                                                   |
 *|+ @  |5  |=создать матрицу и заполнения последовательности символов лабиринта|= Maze                                   |                                                   |
 *| $ .!|6  |=графический интерфейс Java FX                                     |                                         |                                                   |
 *|   . |7  |=механику игры управления                                          |                                         |                                                   |
 *|     |8  |=меню игры                                                         |                                         |                                                   |
 *|     |9  |=сохранения игры события в лабиринте в текст или SQL               |                                         |                                                   |
 *|     |10 |=сохранения уровня игры                                            |                                         |                                                   |
 *|     |11 |=загрузка игры                                                     |                                         |                                                   |
 *|     |12 |=загрузка уровней                                                  |                                         |                                                   |
 *|     |13 |=выбор уровня после пройденного                                    |                                         |                                                   |
 *|     |14 |=статистика информации - время прохождения игры                    |                                         |                                                   |
 *|    !|15 |=текстурирования лабиринта                                         |                                         |                                                   |
 *|    !|16 |=звуки игры                                                        |                                         |                                                   |
 *|    !|17 |=3d графический интерфейс                                          |                                         |                                                   |
 *|    !|18 |=нейросеть                                                         |                                         |                                                   |
 *|    !|19 |=глубинное обучения                                                |                                         |                                                   |
 *|     |20 |=создать exe                                                       |                                         |                                                   |
 *-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        InputStream inputStream = getClass().getResourceAsStream("/com/game/Texture/icon.png");//create icon In the panel window
        Image image = new Image(inputStream);
        primaryStage.getIcons().add(image);

//        Scene scene = new Scene();
//        scene.

//        Parent parent = new Parent();

//        BoxLayout

        primaryStage.setTitle("Sokoban");
        primaryStage.setWidth(1280);
        primaryStage.setHeight(720);

//        Label helloWorldLabel = new Label("Hello world!");
//        helloWorldLabel.setAlignment(Pos.CENTER);
//        Scene primaryScene = new Scene(helloWorldLabel);
//        primaryStage.setScene(primaryScene);

        Canvas canvas = new Canvas();
        canvas.getGraphicsContext2D();
        canvas.isVisible();

        primaryStage.show();


    }
}


/////////                                      Game Interface design JavaFX
//+    //= done
// $   //= on development stage
//  @  //= redo
//   . //= following development process
//    !//= difficult to develop
//     //= following development process Down 0.1.2.3.4.5.6.7.8.9

//     //Windows Java FX
// $ . //Stage logo Sokoban ->
//   . //  Scene
//     //-------------------------------Animations!
//     //|                             |
//     //|                             |
//     //|### ### # # ### ###  ##  #  #|
//     //|#   # # ##  # # # # #  # ## #|
//     //|### # # #   # # ##  #### ####|
//     //|  # # # ##  # # # # #  # # ##|
//     //|### ### # # ### ### #  # #  #|
//     //|                             |
//     //|                             |
//     //|           START             |Button
//     //|                             |
//     //|                             |
//     //-------------------------------

//   . //Stage menu
//     // Scene
//     //-------------------------------Back^logo Sokoban
//     //|                             |
//     //|  //LodingMaps               |Button Loding Maps Coding
//     //|  //Encoding                 |Button Encoding Level
//     //|  //Encoding Delete          |Button Loading Maze
//     //|      //OK                   |Button Clear Maze
//     //|      //Back                 |Button  Gama Menu
//     //|  //Back                     |Button Back^logo Sokoban
//     //|  //Exit                     |Button Exit
//     //|                             |
//     //|                             |
//     //|                             |
//     //|                             |
//     //-------------------------------Gama Menu
//     //Stage Game Menu
//     // Scene
//     //-------------------------------Back^menu
//     //|                             |
//     //| //Game                      |Title  Game
//     //|  -->//New Game              |Button New Game > Play
//     //|     //choice Level          |Button Choice Level > Play
//     //|     //                      |Button AI > speed Game
//     //|     //Reset Game            |Button Save Lavel Game > yes or no
//     //|     //Pause                 |Button Loading Lavel Game > Play > yes or no
//     //|     //Save Game             |Button Reset Record Game > yes or no
//     //|     //Loading Game          |Button Languages Русский English
//     //|     //Back                  |Button settings Sound off on
//     //| //Exit                      |Button statistics records top 5
//     //|                             |Button key board  /autor
//     //-------------------------------Button Back^menu
//     //                               Button Exit

//     //Choice Level
//     // 1 Size 5x3 sizeLine 15 char partles
//     // 2
//     // 3
//     // 4 autor oleg margorsky
//     // 5...
//     //Button Back^Game Menu

//     //Languages
//     // English
//     // Русский
//     // Czech
//     //Button Back^Game Menu

//     //settings
//     // 2D Primitives Teksture off on
//     // Sound off on
//     // Music off on
//     //Button Back^Game Menu

//     //statistics records top 5
//     // Name Time Level
//     //Button Back^Game Menu

//     //key board
//     // W ----- UP
//     // S ----- Down
//     // A ----- Left
//     // D ----- Right
//     // ESC --- Back
//     // Enter - next
//     // R ----- to return history
//     // P ----- Help key board
//     //Button Back^Game Menu







//     //Stage
//     // Scene game GraphicsFrame
//     //-------------------------------Play
//     //|       +-------------+       |-Canvas
//     //|       |#############|       |--Rectangle
//     //|       |#   ##......#|       |
//     //|       |# $       $ #|       |  continue game
//     //|       |## # #$###  #|       |  Pauza
//     //|>      |##$      ## #|       |  Stop
//     //|       |##  ###  ## #|       |  Back^Game Menu
//     //|       |####### ### #|       |
//     //|       |# $ $     # #|       |
//     //|       |#@   #  #   #|       |
//     //|       |#############|       |
//     //|       +-------------+       |
//     //-------------------------------



//     //;23.txt

//     //|-----------------------------|
//     //
//     //
//     //
//     //
//     //
//     //
//     //
//     //
//     //
//     //
//     //
//     //
//     //              Cast              -speed 10 sek
//     //
//     //           Developer
//     //         autor - Hiroyuki Imabayashi
//     //       Created - Hiroyuki Imabayashi
//     //     copyright - Thinking Rabbit
//     //
//     //          Programmer
//     //  wrote a game - Margorsky Oleg
//     //
//     //             Genre
//     //        Puzzle - genre
//     //
//     //             Musik
//     //
//     //             Sound
//     //
//     //              VFX
//     //       Teksture
//     //
//     //           Sokoban.txt
//     //
//     //       thank you for.txt
//     //        playing my game!.txt
//     //
//     //
//     //
//     //
//     //
//     //
//     //
//     //
//     //
//     //
//     //
//     //
//     //            THE END.txt         - stop 10 sek
//     //
//     //
//     //
//     //
//     //
//     //
//     //
//     //
//     //
//     //-                              -
//     //|                              |
//     //|                              |
//     //| ### ### # # ### ###  ##  #  #|  -Animations Go to the main screen!
//     //| #   # # ##  # # # # #  # ## #|
//     //| ### # # #   # # ##  #### ####|
//     //|   # # # ##  # # # # #  # # ##|
//     //| ### ### # # ### ### #  # #  #|
//     //|                              |
//     //|                              |
//     //|            #@$.#             |
//     //|                              |
//     //|                              |
//     //-                              -



