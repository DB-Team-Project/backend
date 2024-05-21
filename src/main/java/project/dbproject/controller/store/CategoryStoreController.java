package project.dbproject.controller.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import project.dbproject.domain.Category;
import project.dbproject.domain.Store;
import project.dbproject.dto.CategoryStoreDto;
import project.dbproject.service.StoreService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CategoryStoreController {
    private final StoreService storeService;

    @GetMapping("/category/{category}")
    public Result getStoresByCategory(@PathVariable Category category) {
        return new Result(getStores(category));
    }

    private List<CategoryStoreDto> getStores(final Category category) {
        return storeService.getAllStores().stream()
                .filter(store -> store.getCategory() == category)
                .map(Store::toDto)
                .collect(Collectors.toList());
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }
}
