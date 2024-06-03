package project.dbproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import project.dbproject.domain.Store;

@Data
@AllArgsConstructor
public class CategoryStoreDto {
    private Long storeId;
    private String storeName;
    private String categoryName;
    private String description;
    private String storeImage;
    private String location;

    public CategoryStoreDto(final Store store) {
        this.storeId = store.getId();
        this.storeName = store.getName();
        this.categoryName = store.getCategory().name().toLowerCase();
        this.description = store.getDescription();
        this.storeImage = store.getImage();
        this.location = store.getLocation();
    }
}
