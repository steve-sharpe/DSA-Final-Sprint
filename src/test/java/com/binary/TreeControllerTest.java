package com.binary;
import com.binary.controller.TreeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class TreeControllerTests {
    @Autowired
    private TreeController controller;
    @Test
    void contextLoads() {
        assert(controller != null);
    }
    @Test
    void testBSTInsertion() {
        String json = controller.processNumbers("5,3,7,1");
        assert(json.contains("value\":5"));
    }
}