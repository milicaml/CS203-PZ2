package com.cs203pz2;

import com.cs203pz2.bst.BST;
import com.cs203pz2.bst.BSTPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

//The preceding exercise simply highlights a search path.
//Write a Java applet that animates how a search is performed. First you see that the root is
//searched, and then a subtree is searched recursively. When a node is searched, the node
//is highlighted. The search stops when a key is found in the tree, or the applet displays a
//message that a key is not in the tree
public class HelloApplication extends Application {
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 400;


    @Override
    public void start(Stage stage) throws IOException {
        BorderPane root = new BorderPane();

        BSTPane content = new BSTPane();
        root.setCenter(content);

        BST tree = new BST();

        tree.insert(25);
        tree.insert(20);
        tree.insert(36);
        tree.insert(10);
        tree.insert(22);
        tree.insert(30);
        tree.insert(40);
        tree.insert(5);
        tree.insert(12);
        tree.insert(28);
        tree.insert(38);
        tree.insert(48);

        content.setBst(tree);
        content.drawTree();

        Button addButton = new Button("Add");
        Label addLabel = new Label("Enter a node");
        TextField valueField = new TextField();
        Button searchButton = new Button("Search");
        Label messageLabel = new Label("Enter a key");
        TextField keyField = new TextField();

        HBox hBox = new HBox();
        hBox.getChildren().addAll(addLabel, valueField, addButton, messageLabel, keyField, searchButton);
        hBox.setAlignment(javafx.geometry.Pos.CENTER);
        hBox.setSpacing(15);
        root.setBottom(hBox);

        addButton.setOnAction(e -> {
            tree.insert(Integer.parseInt(valueField.getText()));
            content.drawTree();
        });
        searchButton.setOnAction(e -> {
            if (tree.search(Integer.parseInt(keyField.getText()))) {
                content.animatedSearch(Integer.parseInt(keyField.getText()));
                return;
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Value not found");
            alert.showAndWait();
        });

        tree.order();

        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}