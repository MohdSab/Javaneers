package GuiDarkMode;

import boggle.*;
import boggle.Dictionary;
import difficulty_adaptor.DifficultyAdaptor;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.util.*;


public class GuiController {

    private Stage stage;
    private Scene scene;

    static int difficultyLevel;
    ArrayList<String> foundStrings = new ArrayList<>();
    String userWord;

    @FXML
    TextArea textFoundArea, textWordArea, finalFoundHuman, finalFoundComputer, computerFoundArea;
    @FXML
    Text gameScore, finalScoreHuman, finalScoreComputer, computerScore;

    @FXML
    Button endGameButton, hintButton, startButton, button4x4, button5x5, checkButton, finalEndButton;
    @FXML
    Button lev1, lev2, lev3, lev4;
    @FXML
    Button tile00, tile01, tile02, tile03, tile10, tile11, tile12, tile13, tile20, tile21, tile22, tile23, tile30, tile31, tile32, tile33;


    BoggleGame boggleGame = new BoggleGame();
    Dictionary boggleDict;
    BoggleGrid boggleGrid4x4 = new BoggleGrid(4);
    BoggleGrid boggleGrid5x5 = new BoggleGrid(5);

    String string4x4 = boggleGame.randomizeLetters(4);
    String string5x5 = boggleGame.randomizeLetters(5);


    Map<String, ArrayList<Position>> allWords = new HashMap<>();



    public void switchToMainScene(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("mainScene.fxml"))));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Mightier Boggle");
        stage.setResizable(false);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


        Button sourceButton = (Button) event.getSource();

        if(sourceButton == lev1){
            difficultyLevel = 1;
        }

        else if(sourceButton == lev2){
            difficultyLevel = 2;
        }

        else if(sourceButton == lev3){
            difficultyLevel = 3;}

        else if(sourceButton == lev4){
            difficultyLevel = 4;
        }
//        System.out.println(difficultyLevel);
//        System.out.println(boardSize);


    }

    public void switchTo4x4Scene(ActionEvent event) throws IOException{


        Parent root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("4x4Scene.fxml"))));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Mightier Boggle");
        stage.setResizable(false);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        Button sourceButton = (Button) event.getSource();

        if(sourceButton == lev1){
            difficultyLevel = 1;
        }

        else if(sourceButton == lev2){
            difficultyLevel = 2;
        }

        else if(sourceButton == lev3){
            difficultyLevel = 3;}

        else if(sourceButton == lev4){
            difficultyLevel = 4;
        }
    }

    public void switchTo5x5Scene(ActionEvent event) throws IOException{

        Parent root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("5x5Scene.fxml"))));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Mightier Boggle");
        stage.setResizable(false);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setup4x4Board(ActionEvent event) throws IOException{

        boggleDict = new Dictionary("wordlist.txt");
        boggleGrid4x4.initalizeBoard(string4x4);
        boggleGame.findAllWords(allWords, boggleDict, boggleGrid4x4);


        tile00.setText(String.valueOf(string4x4.charAt(0)));
        tile01.setText(String.valueOf(string4x4.charAt(1)));
        tile02.setText(String.valueOf(string4x4.charAt(2)));
        tile03.setText(String.valueOf(string4x4.charAt(3)));
        tile10.setText(String.valueOf(string4x4.charAt(4)));
        tile11.setText(String.valueOf(string4x4.charAt(5)));
        tile12.setText(String.valueOf(string4x4.charAt(6)));
        tile13.setText(String.valueOf(string4x4.charAt(7)));
        tile20.setText(String.valueOf(string4x4.charAt(8)));
        tile21.setText(String.valueOf(string4x4.charAt(9)));
        tile22.setText(String.valueOf(string4x4.charAt(10)));
        tile23.setText(String.valueOf(string4x4.charAt(11)));
        tile30.setText(String.valueOf(string4x4.charAt(12)));
        tile31.setText(String.valueOf(string4x4.charAt(13)));
        tile32.setText(String.valueOf(string4x4.charAt(14)));
        tile33.setText(String.valueOf(string4x4.charAt(15)));

    }

    public void endGame(ActionEvent event) throws IOException{
        DifficultyAdaptor temp = new DifficultyAdaptor(difficultyLevel, allWords);
        boggleGame.computerMove(temp.getAdaptedAllWords());

        for (String word : boggleGame.gameStats.computerWords){
            computerFoundArea.appendText(word + "\n");
        }
        computerScore.setText(Integer.toString(boggleGame.gameStats.cScore));
        endGameButton.setDisable(true);

//        Platform.exit();

    }

    public void setCheckButton(ActionEvent event) throws IOException{



        String foundWord = textWordArea.getText().strip();
        if (allWords.containsKey(foundWord.toUpperCase())){
            boggleGame.gameStats.addWord(foundWord, BoggleStats.Player.Human);
            gameScore.setText(Integer.toString(boggleGame.gameStats.getScore()));
            textFoundArea.appendText(foundWord + "\n");
            foundStrings.add(foundWord);

        }
    }

//    public void setFinalEndButton(ActionEvent event) throws IOException{
//        System.out.println(difficultyLevel);
//
//        for (String s : foundStrings){
//            System.out.println(s);
//        }
//        Platform.exit();
//        System.out.println(boggleGame.toString());
//        System.out.println(boggleGame.gameStats.getPlayerWords());
//    }


}
