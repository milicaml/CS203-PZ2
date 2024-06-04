package com.cs203pz2;

import javafx.animation.FillTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.StrokeTransition;
import javafx.scene.DepthTest;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class BST {
    private Node root;

    public BST() {
        this.root = null;
    }

    public void insert(int data) {
        this.root = insertRec(this.root, data);
    }

    private Node insertRec(Node root, int data) {
        if (root == null) {
            root = new Node(data);
            return root;
        }

        if (data < root.data) {
            root.left = insertRec(root.left, data);
        } else {
            root.right = insertRec(root.right, data);
        }

        return root;
    }

    public void drawTree(final Pane content) {
        content.getChildren().clear();
        drawTreeRec(content, this.root, HelloApplication.WINDOW_WIDTH * 0.5, 100, 100);
    }

    private void drawTreeRec(final Pane content, Node root, double x, double y, double hGap) {
        if (root != null) {
            if (root.left != null) {
                Line line = new Line(x, y, x - hGap, y + 50);
                content.getChildren().add(line);
            }
            if (root.right != null) {
                Line line = new Line(x, y, x + hGap, y + 50);
                content.getChildren().add(line);
            }

            Circle circle = new Circle(x, y, 20);
            circle.setFill(Color.LIGHTBLUE);
            content.getChildren().add(circle);

            Text text = new Text(x - 5, y + 5, String.valueOf(root.data));
            content.getChildren().add(text);

            drawTreeRec(content, root.left, x - hGap, y + 50, hGap / 2);
            drawTreeRec(content, root.right, x + hGap, y + 50, hGap / 2);
        }
    }

    public void animatedSearch(final Pane content, int key) {
        drawTree(content);
        SequentialTransition sequentialTransition = new SequentialTransition();

        animatedSearchRec(content, this.root, HelloApplication.WINDOW_WIDTH * 0.5, 100, 100, key, sequentialTransition);

        sequentialTransition.play();
    }

    private void animatedSearchRec(final Pane content, Node root, double x, double y, double hGap, int key, final SequentialTransition seqTransition) {
        if (root != null) {
            Circle circle = new Circle(x, y, 20);
            circle.setFill(Color.LIGHTBLUE);
            content.getChildren().add(circle);

            Text text = new Text(x - 5, y + 5, String.valueOf(root.data));
            content.getChildren().add(text);

            FillTransition ft = new FillTransition(Duration.millis(1000), circle);
            if (root.data == key) {
                ft.setToValue(Color.GREEN);
            } else {
                ft.setToValue(Color.RED);
            }
            seqTransition.getChildren().add(ft);

            if (root.data > key) {
                animatedSearchRec(content, root.left, x - hGap, y + 50, hGap / 2, key, seqTransition);
            } else if (root.data < key) {
                animatedSearchRec(content, root.right, x + hGap, y + 50, hGap / 2, key, seqTransition);
            }
        }
    }

}