/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game2048.ui;

import game2048.domain.Board;
import Game2048.dao.Database;
import Game2048.dao.Score;
import Game2048.dao.ScoreDao;

import java.sql.*;
import com.sun.prism.paint.Color;
import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author jukka
 */
public class Game2048 extends Application {

    private Board board = new Board();
    private boolean hardcoreMode = false;
    private Random random = new Random();
    private String[] colors = new String[]{"-fx-base: #FFDF00", "-fx-base: #87CEFA", 
        "-fx-base: #9400D3", "-fx-base: #006400", "-fx-base: #ee2211", "-fx-base: #0000FF", 
        "-fx-base: #5F9EA0", "-fx-base: #800000", "-fx-base: #DB7093"};
    
    public static void main(String[] args) {
        launch(Game2048.class);
    }

    public void start(Stage window) {
        //first view
        BorderPane mainLay = new BorderPane();
        Label mainHeader = new Label("Your goal is to get tile with number 2048");
        mainLay.setTop(mainHeader);
        Button toHighscore = new Button("Highscores");
        toHighscore.setStyle("-fx-base: #FFDF00");
        mainLay.setRight(toHighscore);
        Button addResult = new Button("Add score");
        addResult.setStyle("-fx-base: #87CEFA");
        VBox addChangeModeButtons = new VBox();
        Button changeMode = new Button("Psychosis");
        changeMode.setStyle("-fx-base: #9400D3");
        addChangeModeButtons.getChildren().addAll(addResult, changeMode);
        mainLay.setLeft(addChangeModeButtons);
        

        //second view
        VBox addNameLay = new VBox();
        Label header2 = new Label("Write your name");
        TextField field = new TextField("");
        Button addPlayerResult = new Button("Add");
        addPlayerResult.setStyle("-fx-base: #006400");
        Button cancel = new Button("Cancel");
        cancel.setStyle("-fx-base: #ee2211");
        HBox buttonsLay2 = new HBox();
        buttonsLay2.getChildren().addAll(addPlayerResult, cancel);
        addNameLay.getChildren().addAll(header2, field, buttonsLay2);

        //third view
        BorderPane highscores = new BorderPane();
        Label header3 = new Label("Here is the top 10 players and their max score:");
        highscores.setStyle("-fx-base: #FFDF00");
        Button rePlay = new Button("Replay");
        rePlay.setStyle("-fx-base: #006400");
        highscores.setTop(header3);
        highscores.setRight(rePlay);

        //buttons for first view
        GridPane buttonLay = new GridPane();
        mainLay.setCenter(buttonLay);
        for (int x = 1; x <= 4; x++) {
            for (int y = 1; y <= 4; y++) {
                String helper = String.valueOf(board.getBoard()[x - 1][y - 1]);
                Button a = new Button(helper);
                a.setStyle("-fx-base: #ee2211;");
                a.setMinSize(80, 80);
                a.setMaxSize(80, 80);
                a.setPrefSize(80, 80);
                a.setFont(Font.font("Monospaced", 40));
                buttonLay.add(a, x, y);
            }
        }

        //view description
        Scene view1 = new Scene(mainLay);
        Scene view2 = new Scene(addNameLay);
        Scene view3 = new Scene(highscores);

        //actions
        //goes to add result view
        addResult.setOnAction((event) -> {
            window.setScene(view2);
        });

        //goes back to first view and doesnt add result
        cancel.setOnAction((event) -> {
            window.setScene(view1);
        });
        
        //sets hardcoremode on or off
        changeMode.setOnAction((event) -> {
            if (hardcoreMode){
                hardcoreMode=false;
            }else{
                hardcoreMode=true;
            }
        });
        
        //goes to highscore from first view
        toHighscore.setOnAction((event) -> {
            try {
                Label top10 = new Label(getTop10());
                highscores.setCenter(top10);
            } catch (Exception exception) {
                Logger.getLogger(Game2048.class.getName()).log(Level.SEVERE, null, exception);
            }
            window.setScene(view3);
        });

        //restarts the game
        rePlay.setOnAction((event) -> {
            hardcoreMode=false;
            board.restart();
            mainLay.setCenter(updateBoard());
            window.setScene(view1);
        });

        //adds result to database
        addPlayerResult.setOnAction((event) -> {
            try {
                addToDatabase(field.getText(), board.getMax());
            } catch (Exception ex) {
                Logger.getLogger(Game2048.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                Label top10 = new Label(getTop10());
                highscores.setCenter(top10);
            } catch (Exception exception) {
                Logger.getLogger(Game2048.class.getName()).log(Level.SEVERE, null, exception);
            }
            window.setScene(view3);
        });

        //move actions
        view1.setOnKeyPressed((event) -> {
            if (!board.end()){
                if (event.getCode().equals(KeyCode.UP)) {
                    board.moveUp();
                    mainLay.setCenter(updateBoard());

                } else if (event.getCode().equals(KeyCode.DOWN)) {
                    board.moveDown();
                    mainLay.setCenter(updateBoard());

                } else if (event.getCode().equals(KeyCode.LEFT)) {
                    board.moveLeft();
                    mainLay.setCenter(updateBoard());

                } else if (event.getCode().equals(KeyCode.RIGHT)) {
                    board.moveRight();
                    mainLay.setCenter(updateBoard());
                }
            }else if (board.getMax()==2048){
                mainHeader.setText("You won!");
            }else{
                mainHeader.setText("Game over!");
            }
        });

        //sets starting view when started
        window.setScene(view1);
        window.show();
    }

    /**
     * Makes sure that board is updated.
     */
    public GridPane updateBoard() {
        GridPane buttonLay = new GridPane();
        for (int x = 1; x <= 4; x++) {
            for (int y = 1; y <= 4; y++) {
                String helper = String.valueOf(board.getBoard()[x - 1][y - 1]);
                Button a = new Button(helper);
                if (hardcoreMode){
                    a.setStyle(colors[random.nextInt(8)]);
                }else{
                a.setStyle("-fx-base: #ee2211;");
                }
                a.setMinSize(80, 80);
                a.setMaxSize(80, 80);
                a.setPrefSize(80, 80);
                if (helper.length() == 1) {
                    a.setFont(Font.font("Monospaced", 40));
                } else if (helper.length() == 2) {
                    a.setFont(Font.font("Monospaced", 31));
                } else if (helper.length() == 3) {
                    a.setFont(Font.font("Monospaced", 22));
                } else {
                    a.setFont(Font.font("Monospaced", 11));
                }
                buttonLay.add(a, y, x);
            }
        }
        return buttonLay;
    }

    /**
     * Adds given result to database.
     * @param playerName name of the player
     * @param maxScore player's score
     */
    public void addToDatabase(String playerName, int maxScore) throws Exception {
        File file = new File("db", "scores.db");
        Database database = new Database("jdbc:sqlite:" + file.getAbsolutePath());
        ScoreDao scoreDao = new ScoreDao(database);
        scoreDao.saveOrUpdate(new Score(null, playerName, maxScore));
    }

    /**
     * Gets top10 best scores and the corresponding players.
     */
    public String getTop10() throws Exception {
        File file = new File("db", "scores.db");
        Database database = new Database("jdbc:sqlite:" + file.getAbsolutePath());
        ScoreDao scoreDao = new ScoreDao(database);
        List<Score> top10 = scoreDao.findTop10();

        String best = "";
        for (int i = 0; i < Math.min(top10.size(), 10); i++) {
            best += String.valueOf(i + 1) + ". " + top10.get(i).getName() + " "
                    + top10.get(i).getMaxScore() + "\n";
        }
        return best;
    }
}
