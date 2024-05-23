package project.dbproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import project.dbproject.domain.Category;

@Data
@AllArgsConstructor
public class LocationStoreDto {
    private Long storeId;
    private String storeName;
    private String category;
    private Double latitude;
    private Double longitude;
}
