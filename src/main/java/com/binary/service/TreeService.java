package com.binary.service;

import com.binary.model.BinarySearchTree;
import com.binary.model.TreeNode;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

@Service
public class TreeService {

    public Map<String, Object> createTree(List<Integer> numbers) {
        BinarySearchTree bst = new BinarySearchTree();
        for (int n : numbers) {
            bst.insert(n);
        }

        // Convert BST to JSON structure
        return nodeToMap(bst.getRoot());
    }

    private Map<String, Object> nodeToMap(TreeNode node) {
        if (node == null) return null;

        return Map.of(
                "value", node.getValue(),
                "left", nodeToMap(node.getLeft()),
                "right", nodeToMap(node.getRight())
        );
    }
}
