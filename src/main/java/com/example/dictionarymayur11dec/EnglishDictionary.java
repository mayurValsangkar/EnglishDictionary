package com.example.dictionarymayur11dec;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class EnglishDictionary extends Application {

    // Label is used to display a short text
    Label meaningLabel;

    // Search button to search result
    Button searchButton;

    // Allows user to enter a single line of text
    TextField wordTextField;

    // To give user some suggestions.
    ListView<String> suggestionList;

    // Creating new object of class DictionaryUsingHashMap
    DictionaryUsingHashMap dictionaryUsingHashMap = new DictionaryUsingHashMap();

    Pane createContent(){
        //Frame for Dictionary
        Pane root = new Pane();

        // Setting frame size of UI.
        root.setPrefSize(400,600);

        // Initialize Text field
         wordTextField = new TextField();

        // Placing Text field in frame at correct position
        wordTextField.setTranslateX(20);
        wordTextField.setTranslateY(30);

        //  Initialize button
        searchButton = new Button("Search");

        // Placing button in frame at correct position
        searchButton.setTranslateX(175);
        searchButton.setTranslateY(30);



        // Perform action on search button...
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent actionEvent) {
//                meaningLabel.setText("Button is clicked.");
//                wordTextField.getText();

                // Getting word entered in text field
                String word = wordTextField.getText();

               // If clicked on button without giving input, ask user to enter word
               // else search meaning of word in hashmap
                if(word.isBlank() || word.isEmpty()){
                    meaningLabel.setText("Please enter a word !");
                    meaningLabel.setTextFill(Color.RED);
                }else{
                    String meaning = dictionaryUsingHashMap.getMeaning(word);
                    meaningLabel.setText(meaning);
                    meaningLabel.setTextFill(Color.BLACK);
                }
            }
        });

        // Initialize Label
        meaningLabel = new Label("Meaning will show here !");

        // Placing Label in frame at correct position
        meaningLabel.setTranslateX(20);
        meaningLabel.setTranslateY(60);

        // Initialize List view
        suggestionList = new ListView<>();

        // Placing List view in frame at correct position
        suggestionList.setTranslateX(20);
        suggestionList.setTranslateY(100);

        // Dimension of suggestion list
        suggestionList.setMinSize(330, 50);
        suggestionList.setMaxSize(300, 50);

        // Some suggestions to search
        String[] wordList = {"Messi","Ronaldo", "Neymar","Mbappe", "Luka", "Ziyech"};

        // Adding suggestions in suggestion list
        suggestionList.getItems().addAll(wordList);
        // Setting Orientation as vertical or horizontal of suggestion list
        suggestionList.setOrientation(Orientation.VERTICAL);

        suggestionList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String selectWord = suggestionList.getSelectionModel().getSelectedItem();
                // After selecting any word in suggestion list, it will appear in text field
                wordTextField.setText(selectWord);
            }
        });

        // Adding all the tabs that we have created, in a frame. i.e (Pane(root is reference to Pane)).
        root.getChildren().addAll(wordTextField, searchButton, meaningLabel, suggestionList);

        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = new Scene(createContent());
        stage.setTitle("Dictionary!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}