package com.quizapp.adminjavafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AdminApp extends Application {

    private final String API_URL = "http://localhost:8081/admin/add";

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Admin Add Question");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        TextField categoryField = new TextField();
        categoryField.setPromptText("Category");
        TextField levelField = new TextField();
        levelField.setPromptText("Difficulty Level");
        TextField questionField = new TextField();
        questionField.setPromptText("Question");
        TextField opt1Field = new TextField();
        opt1Field.setPromptText("Option 1");
        TextField opt2Field = new TextField();
        opt2Field.setPromptText("Option 2");
        TextField opt3Field = new TextField();
        opt3Field.setPromptText("Option 3");
        TextField opt4Field = new TextField();
        opt4Field.setPromptText("Option 4");
        TextField rightAnswerField = new TextField();
        rightAnswerField.setPromptText("Right Answer");

        Button submitBtn = new Button("Add Question");

        grid.add(new Label("Category:"), 0, 0); grid.add(categoryField, 1, 0);
        grid.add(new Label("Difficulty Level:"), 0, 1); grid.add(levelField, 1, 1);
        grid.add(new Label("Question:"), 0, 2); grid.add(questionField, 1, 2);
        grid.add(new Label("Option 1:"), 0, 3); grid.add(opt1Field, 1, 3);
        grid.add(new Label("Option 2:"), 0, 4); grid.add(opt2Field, 1, 4);
        grid.add(new Label("Option 3:"), 0, 5); grid.add(opt3Field, 1, 5);
        grid.add(new Label("Option 4:"), 0, 6); grid.add(opt4Field, 1, 6);
        grid.add(new Label("Right Answer:"), 0, 7); grid.add(rightAnswerField, 1, 7);
        grid.add(submitBtn, 1, 8);

        submitBtn.setOnAction(e -> {
            try {
                String json = String.format("""
                    {
                        "category":"%s",
                        "difficultyLevel":"%s",
                        "question":"%s",
                        "option1":"%s",
                        "option2":"%s",
                        "option3":"%s",
                        "option4":"%s",
                        "rightAnswer":"%s"
                    }
                    """,
                        categoryField.getText(), levelField.getText(), questionField.getText(),
                        opt1Field.getText(), opt2Field.getText(), opt3Field.getText(),
                        opt4Field.getText(), rightAnswerField.getText()
                );

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(API_URL))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(json))
                        .build();

                HttpClient client = HttpClient.newHttpClient();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                if(response.statusCode() == 200 || response.statusCode() == 201){
                    alert.setContentText("Question added successfully!");
                } else {
                    alert.setContentText("Failed to add question! Status: " + response.statusCode());
                }
                alert.showAndWait();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Scene scene = new Scene(grid, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
