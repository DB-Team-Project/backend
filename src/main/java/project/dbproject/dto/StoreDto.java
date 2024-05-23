package project.dbproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

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
    private List<ReviewDto> reviews;
}
