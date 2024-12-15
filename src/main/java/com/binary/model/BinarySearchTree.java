package com.binary.model;

public class BinarySearchTree {
    private TreeNode root;

    public void insert(int val) {
        root = insertRec(root, val);
    }

    private TreeNode insertRec(TreeNode node, int val) {
        if (node == null) {
            node = new TreeNode(val);
            return node;
        }
        if (val < node.getValue()) {
            node.setLeft(insertRec(node.getLeft(), val));
        } else {
            node.setRight(insertRec(node.getRight(), val));
        }
        return node;
    }

    public TreeNode getRoot() {
        return root;
    }
}
