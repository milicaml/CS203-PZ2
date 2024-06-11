package com.cs203pz2.bst;

public class BST {
    protected Node root;

    public BST() {
        this(null);
    }

    public BST(Node root){
        this.root = root;
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

    public boolean search(int key) {
        return searchRec(this.root, key);
    }

    private boolean searchRec(Node root, int key) {
        if (root == null) {
            return false;
        } else if (root.data == key) {
            return true;
        } else if (root.data > key) {
            return searchRec(root.left, key);
        } else {
            return searchRec(root.right, key);
        }
    }

    public Node getRoot() {
        return this.root;
    }
    public void setRoot(Node root) {
        this.root = root;
    }

    public void preorder() {
        preorderRec(this.root);
    }

    private void preorderRec(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preorderRec(root.left);
            preorderRec(root.right);
        }
    }

    public void postorder() {
        postorderRec(this.root);
    }

    private void postorderRec(Node root) {
        if (root != null) {
            postorderRec(root.left);
            postorderRec(root.right);
            System.out.print(root.data + " ");
        }
    }

    public void inorder() {
        inorderRec(this.root);
    }

    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.data + " ");
            inorderRec(root.right);
        }
    }

    public void order() {
        System.out.println("Inorder:");
        inorder();
        System.out.println("\nPostorder:");
        postorder();
        System.out.println("\nPreorder:");
        preorder();
    }
}