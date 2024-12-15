package com.binary.service;
import com.binary.model.BinarySearchTree;
import com.binary.repository.TreeRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class TreeService {
    private final TreeRepository repository;
    public TreeService(TreeRepository repository) {
        this.repository = repository;
    }
    public BinarySearchTree getTreeById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Tree not found"));
    }
    public BinarySearchTree saveTree(String inputNumbers, String treeStructure) {
        if (treeStructure == null || treeStructure.isEmpty()) {
            throw new RuntimeException("Tree structure cannot be null or empty.");
        }
        BinarySearchTree tree = new BinarySearchTree();
        tree.setInputNumbers(inputNumbers);
        tree.setTreeStructure(treeStructure);
        return repository.save(tree);
    }
    public List<BinarySearchTree> getAllTrees() {
        return repository.findAll();
    }
}