package project.dbproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryStoreDto {
    private Long storeId;
    private String storeName;
    private String description;
    private String storeImage;
    private String location;
}
