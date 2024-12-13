package com.binary.repository;



import com.binary.model.BinarySearchTree;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreeRepository extends JpaRepository<BinarySearchTree, Long> {
}
