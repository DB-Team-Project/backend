package project.dbproject.controller.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.dbproject.domain.Category;
import project.dbproject.domain.Store;
import project.dbproject.dto.CategoryStoreDto;
import project.dbproject.dto.StoreDto;
import project.dbproject.service.StoreService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CategoryStoreController {
    private final StoreService storeService;

    //카테고리별 제휴업체 전체 조회
    @GetMapping("/category/{category}")
    public Result<List<CategoryStoreDto>> getStoresForCategory(@PathVariable(name = "category") String category) {
        List<Store> stores = storeService.getAllStores().stream()
                .filter(s -> s.getCategory().toString().toLowerCase().equals(category))
                .collect(Collectors.toList());
        for (Store store : stores) {
            System.out.println("store.getName() = " + store.getName());
        }
        List<CategoryStoreDto> storeDtos = stores.stream()
                .map(Store::toCategoryDto)
                .collect(Collectors.toList());
        return new Result<>(storeDtos);
    }


    //제휴업체 조회
    @GetMapping("/category/{category}/{storeId}")
    public StoreDto getStore(@PathVariable(name = "category") String category, @PathVariable(name ="storeId") Long storeId) {
        Store store = storeService.getAllStores().stream()
                .filter(s -> s.getCategory().toString().toLowerCase().equals(category) && s.getId().equals(storeId))
                .findFirst()
                .orElse(null);
        return store == null ? null : store.toStoreDto();
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T stores;
    }
}
