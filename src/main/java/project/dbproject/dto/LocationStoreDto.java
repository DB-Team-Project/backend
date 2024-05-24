package project.dbproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import project.dbproject.domain.Store;

@Data
@AllArgsConstructor
public class LocationStoreDto {
    private Long storeId;
    private String storeName;
    private String category;
    private Double latitude;
    private Double longitude;

    public LocationStoreDto(final Store store) {
        this.storeId = store.getId();
        this.storeName = store.getName();
        this.category = store.getCategory().toString();
        this.latitude = store.getLatitude();
        this.longitude = store.getLongitude();
    }
}
