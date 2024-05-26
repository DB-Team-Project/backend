package project.dbproject.controller.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.dbproject.domain.Store;
import project.dbproject.dto.CategoryStoreDto;
import project.dbproject.dto.StoreDto;
import project.dbproject.service.StoreService;

import java.util.List;

@CrossOrigin(origins = "https://dbproject.azurewebsites.net")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryStoreController {
    private final StoreService storeService;

    //카테고리별 제휴업체 전체 조회
    @GetMapping("/category/{category}")
    public ResponseEntity<Result<List<CategoryStoreDto>>> getStoresForCategory(@PathVariable(name = "category") String category) {
        List<Store> stores = storeService.getAllStores().stream()
                .filter(s -> s.getCategory().toString().toLowerCase().equals(category))
                .toList();

        return getCategorieStoreDtoResponseEntity(stores);
    }


    //제휴업체 조회
    @GetMapping("/category/{category}/{storeId}")
    public ResponseEntity<StoreDto> getStore(@PathVariable(name = "category") String category, @PathVariable(name = "storeId") Long storeId) {
        Store store = storeService.getAllStores().stream()
                .filter(s -> s.getCategory().toString().toLowerCase().equals(category) && s.getId().equals(storeId))
                .findFirst()
                .orElse(null);
        return store == null ? null : getStoreDtoResponseEntity(store);
    }

    private ResponseEntity<Result<List<CategoryStoreDto>>> getCategorieStoreDtoResponseEntity(final List<Store> stores) {
        List<CategoryStoreDto> list = stores.stream().map(CategoryStoreDto::new).toList();
        return ResponseEntity.ok(new Result<>(list));
    }

    private ResponseEntity<StoreDto> getStoreDtoResponseEntity(final Store store) {
        return ResponseEntity.ok(new StoreDto(store));
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T stores;
    }
}
