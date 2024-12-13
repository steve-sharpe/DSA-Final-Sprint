package com.binary.controller;

import com.binary.model.BinarySearchTree;
import com.binary.service.TreeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class TreeController {
    private final TreeService treeService;

    public TreeController(TreeService treeService) {
        this.treeService = treeService;
    }

    @GetMapping("/")
    public String redirectToInputPage() {
        return "redirect:/enter-numbers/";
    }

    @GetMapping("/enter-numbers/")
    public String showInputPage() {
        return "enter-numbers";
    }

    @PostMapping("/process-numbers")
    public String processNumbers(@RequestParam String numbers) {
        List<Integer> numberList = Arrays.stream(numbers.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();

        TreeNode root = null;
        for (int number : numberList) {
            root = insert(root, number);
        }

        String treeJson = serialize(root);
        BinarySearchTree tree = treeService.saveTree(numbers, treeJson);
        return "redirect:/tree-visual/" + tree.getId() + "/";
    }

    @GetMapping("/tree-visual/{id}/")
    public String showTreeVisual(@PathVariable Long id, Model model) {
        BinarySearchTree tree = treeService.getTreeById(id);
        if (tree == null || tree.getTreeStructure() == null || tree.getTreeStructure().isEmpty()) {
            throw new RuntimeException("Tree data is missing or invalid.");
        }
        model.addAttribute("treeJson", tree.getTreeStructure());
        return "tree-visual";
    }

    private String serialize(TreeNode root) {
        if (root == null) return "null";
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"value\":").append(root.value).append(",");
        sb.append("\"left\":").append(serialize(root.left)).append(",");
        sb.append("\"right\":").append(serialize(root.right));
        sb.append("}");
        return sb.toString();
    }

    @GetMapping("/previous-trees")
    public String showPreviousTrees(Model model) {
        List<BinarySearchTree> trees = treeService.getAllTrees();
        model.addAttribute("trees", trees);
        return "previous-trees";
    }

    private TreeNode insert(TreeNode node, int value) {
        if (node == null) return new TreeNode(value);
        if (value < node.value) node.left = insert(node.left, value);
        else node.right = insert(node.right, value);
        return node;
    }

    static class TreeNode {
        int value;
        TreeNode left, right;

        public TreeNode(int value) {
            this.value = value;
        }
    }
}