package com.binary.model;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
@Entity
public class BinarySearchTree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String inputNumbers;
    @Column(columnDefinition = "TEXT")
    private String treeStructure;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    private List<Integer> inputList;
    // Getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getInputNumbers() {
        return inputNumbers;
    }
    public void setInputNumbers(String inputNumbers) {
        this.inputNumbers = inputNumbers;
    }
    public String getTreeStructure() {
        return treeStructure;
    }
    public void setTreeStructure(String treeStructure) {
        this.treeStructure = treeStructure;
    }
    public List<Integer> getInputList() {
        return inputList;
    }
    public void setInputList(List<Integer> inputList) {
        this.inputList = inputList;
    }
}