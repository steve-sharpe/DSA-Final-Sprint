package com.binary;

import com.binary.model.TreeRecord;
import com.binary.service.TreeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class TreeServiceTest {

    @Test
    public void testCreateTree() {
        TreeService service = new TreeService();
        Map<String,Object> tree = service.createTree(List.of(5,3,7,2));
        Assertions.assertEquals(5, tree.get("value"));
    }
}
