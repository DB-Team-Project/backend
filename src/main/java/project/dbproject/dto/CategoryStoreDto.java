package project.dbproject.dto;

import lombok.Data;

@Data
public class CategoryStoreDto {
    private Long storeId;
    private String storeName;
    private String description;
    private String storeImage;
    private String location;

    public CategoryStoreDto(Long storeId, String storeName, String description, String storeImage, String location) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.description = description;
        this.storeImage = storeImage;
        this.location = location;
    }
}
