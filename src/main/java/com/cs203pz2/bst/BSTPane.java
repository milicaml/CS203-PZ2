package com.cs203pz2.bst;

import com.cs203pz2.HelloApplication;
import javafx.animation.FillTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;

@SuppressWarnings("unused")
public class BSTPane extends Pane {
    private BST bst;

    public BSTPane() {
        super();
    }

    public void setBst(BST bst) {
        this.bst = bst;
    }

    public void drawTree() {
        if (bst == null) return;
        super.getChildren().clear();
        drawTreeRec(this.bst.getRoot(), HelloApplication.WINDOW_WIDTH * 0.5, 100, 100);
    }

    private void drawTreeRec(Node root, double x, double y, double hGap) {
        if (root != null) {
            if (root.left != null) {
                Line line = new Line(x, y, x - hGap, y + 50);
                super.getChildren().add(line);
            }
            if (root.right != null) {
                Line line = new Line(x, y, x + hGap, y + 50);
                super.getChildren().add(line);
            }

            Circle circle = getCircle(x, y);
            super.getChildren().add(circle);

            Text text = new Text(x - 5, y + 5, String.valueOf(root.data));
            super.getChildren().add(text);

            drawTreeRec(root.left, x - hGap, y + 50, hGap / 2);
            drawTreeRec(root.right, x + hGap, y + 50, hGap / 2);
        }
    }

    public void animatedSearch(int key) {
        if (bst == null) return;
        drawTree();
        SequentialTransition sequentialTransition = new SequentialTransition();

        animatedSearchRec(this.bst.getRoot(), HelloApplication.WINDOW_WIDTH * 0.5, 100, 100, key, sequentialTransition);

        sequentialTransition.play();
    }

    private void animatedSearchRec(Node root, double x, double y, double hGap, int key, final SequentialTransition seqTransition) {
        if (root != null) {
            Circle circle = getCircle(x, y);
            super.getChildren().add(circle);

            Text text = new Text(x - 5, y + 5, String.valueOf(root.data));
            super.getChildren().add(text);

            FillTransition ft = new FillTransition(Duration.millis(1000), circle);
            if (root.data == key) {
                ft.setToValue(Color.GREEN);
            } else {
                ft.setToValue(Color.RED);
            }
            seqTransition.getChildren().add(ft);

            if (root.data > key) {
                animatedSearchRec(root.left, x - hGap, y + 50, hGap / 2, key, seqTransition);
            } else if (root.data < key) {
                animatedSearchRec(root.right, x + hGap, y + 50, hGap / 2, key, seqTransition);
            }
        }
    }

    private static Circle getCircle(double x, double y) {
        Circle circle = new Circle(x, y, 20);
        circle.setFill(Color.LIGHTBLUE);
        return circle;
    }
}