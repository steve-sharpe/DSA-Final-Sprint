package com.binary;

import com.binary.model.TreeRecord;
import com.binary.repository.TreeRecordRepository;
import com.binary.service.TreeStorageService;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

public class TreeStorageServiceTest {

    @Test
    public void testSaveTree() {
        TreeRecordRepository mockRepo = mock(TreeRecordRepository.class);
        TreeStorageService service = new TreeStorageService();
        service.repository = mockRepo;

        service.saveTree(List.of(5,3,7), Map.of("value",5));
        verify(mockRepo, times(1)).save(any(TreeRecord.class));
    }
}
