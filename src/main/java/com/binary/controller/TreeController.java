package com.binary.controller;

import com.binary.model.BinarySearchTree;
import com.binary.model.TreeNode;
import com.binary.service.TreeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TreeController {
    private final TreeService treeService;

    public TreeController(TreeService treeService) {
        this.treeService = treeService;
    }

    @PostMapping("/api/tree")
    public ResponseEntity<TreeNode> createTree(@RequestBody List<Integer> numbers) {
        TreeNode root = null;
        for (int number : numbers) {
            root = insert(root, number);
        }
        String treeJson = serialize(root);
        treeService.saveTree(numbers.stream().map(String::valueOf).collect(Collectors.joining(",")), treeJson);
        return ResponseEntity.ok(root);
    }

    @GetMapping("/api/entries")
    public ResponseEntity<List<BinarySearchTree>> getAllEntries() {
        List<BinarySearchTree> trees = treeService.getAllTrees();
        return ResponseEntity.ok(trees);
    }

    @GetMapping("/trees")
    public ResponseEntity<List<BinarySearchTree>> getAllTrees() {
        List<BinarySearchTree> trees = treeService.getAllTrees();
        return ResponseEntity.ok(trees);
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
    public String processNumbers(@RequestParam String numbers, Model model) {
        List<Integer> numberList = Arrays.stream(numbers.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        TreeNode root = null;
        for (int number : numberList) {
            root = insert(root, number);
        }
        String treeJson = serialize(root);
        BinarySearchTree tree = treeService.saveTree(numbers, treeJson);
        model.addAttribute("treeJson", treeJson);
        return "redirect:/tree-visual/" + tree.getId() + "/";
    }

    @GetMapping("/tree-visual/{id}/")
    public String showTreeVisual(@PathVariable Long id, Model model) {
        BinarySearchTree tree = treeService.getTreeById(id);
        if (tree == null) {
            throw new RuntimeException("Tree not found");
        }
        model.addAttribute("treeJson", tree.getTreeStructure());
        return "tree-visual";
    }

    private String serialize(TreeNode root) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(root);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting tree to JSON", e);
        }
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
}