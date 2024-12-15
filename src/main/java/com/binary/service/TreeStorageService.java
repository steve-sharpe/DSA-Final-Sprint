package com.binary.service;

import com.binary.model.TreeRecord;
import com.binary.repository.TreeRecordRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TreeStorageService {
    @Autowired
    public TreeRecordRepository repository;

    public void saveTree(List<Integer> numbers, Map<String, Object> treeJson) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonStr = mapper.writeValueAsString(treeJson);
            String inputStr = numbers.toString().replace("[","").replace("]","");
            TreeRecord record = new TreeRecord(inputStr, jsonStr);
            repository.save(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<TreeRecord> getAllTrees() {
        return repository.findAll();
    }
}
