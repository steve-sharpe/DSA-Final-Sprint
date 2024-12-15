package com.binary;

import com.binary.controller.TreeController;
import com.binary.service.TreeService;
import com.binary.service.TreeStorageService;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.mockito.Mockito.*;

public class TreeControllerTest {

    @Test
    public void testProcessNumbers() {
        TreeService mockService = mock(TreeService.class);
        TreeStorageService mockStorage = mock(TreeStorageService.class);
        TreeController controller = new TreeController();
        controller.treeService = mockService;
        controller.storageService = mockStorage;

        when(mockService.createTree(anyList())).thenReturn(Map.of("value", 5));

        Map<String, Object> result = controller.processNumbers(Map.of("numbers","5,3,7"));
        // Check mock interactions or result validations
    }
}
