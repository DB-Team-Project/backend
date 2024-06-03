package project.dbproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import project.dbproject.domain.Store;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class StoreDto {
    private String storeName;
    private String description;
    private BigDecimal avgRating;
    private String storeImage;
    private String location;
    private List<ResponseReviewDto> reviews;

    public StoreDto(final Store store) {
        this.storeName = store.getName();
        this.description = store.getDescription();
        this.avgRating = store.getAverageRating();
        this.storeImage = store.getImage();
        this.location = store.getLocation();
        this.reviews = store.getReviews().stream()
                .map(ResponseReviewDto::new).toList();
    }
}
