package com.binary.controller;

import com.binary.model.TreeRecord;
import com.binary.service.TreeService;
import com.binary.service.TreeStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class TreeController {

    @Autowired
    public TreeService treeService;

    @Autowired
    public TreeStorageService storageService;

    @PostMapping("/process-numbers")
    public Map<String, Object> processNumbers(@RequestBody Map<String, Object> payload) {
        // Expecting { "numbers": "5,3,7,2" } or possibly ["5","3","7","2"]
        Object numbersObj = payload.get("numbers");
        List<Integer> numbers;
        if (numbersObj instanceof String) {
            String[] parts = ((String) numbersObj).split(",");
            numbers = new ArrayList<>();
            for (String part : parts) {
                numbers.add(Integer.parseInt(part.trim()));
            }
        } else if (numbersObj instanceof List) {
            // If frontend sends a list directly
            numbers = new ArrayList<>();
            for (Object o : (List<?>) numbersObj) {
                numbers.add(Integer.parseInt(o.toString()));
            }
        } else {
            throw new IllegalArgumentException("Invalid input format");
        }

        Map<String, Object> treeJson = treeService.createTree(numbers);
        storageService.saveTree(numbers, treeJson);

        return treeJson;
    }

    @GetMapping("/previous-trees")
    public List<Map<String, Object>> getPreviousTrees() {
        List<TreeRecord> records = storageService.getAllTrees();
        List<Map<String, Object>> result = new ArrayList<>();
        for (TreeRecord record : records) {
            Map<String, Object> map = new HashMap<>();
            map.put("input", record.getInputNumbers());
            map.put("tree", record.getTreeJson());
            result.add(map);
        }
        return result;
    }
}
