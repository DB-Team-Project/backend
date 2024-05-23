package project.dbproject.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import project.dbproject.domain.Store;
import project.dbproject.repository.StoreRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StoreServiceTest {
    @Autowired
    private StoreService storeService;

    @Autowired
    private StoreRepository storeRepository;


    @Test
    @DisplayName("Requesting a Store by its ID returns the correct Store")
    void getStoreByIdTest() {
        final Long storeId = 1L;
        Store expectedStore = storeService.getStoreById(storeId);
        assertEquals(expectedStore.getName(), "네일 데이유");
    }

    @Test
    @DisplayName("Requesting all Stores returns a List of all Stores")
    void getAllStoresTest() {
        List<Store> allStores = storeService.getAllStores();
        assertEquals(allStores.size(), 12);
    }
}