package project.dbproject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import project.dbproject.dto.CategoryStoreDto;
import project.dbproject.dto.LocationStoreDto;
import project.dbproject.dto.StoreDto;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter @Setter
public class Store {
    @Id @GeneratedValue
    @Column(name = "store_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String name;

    private String description;

    private String location;

    private Double latitude;

    private Double longitude;

    private String image;

    @OneToMany(mappedBy = "store")
    private List<Review> reviews = new ArrayList<>();

    //평균 점수
    private BigDecimal averageRating;

    public void updateAverageRating() {
        if (reviews.isEmpty()) {
            this.averageRating = BigDecimal.ZERO;
        } else {
            BigDecimal totalRating = reviews.stream()
                    .map(Review::getRating)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            this.averageRating = totalRating.divide(
                    BigDecimal.valueOf(reviews.size()), MathContext.DECIMAL32);
        }
    }

    public CategoryStoreDto toCategoryDto(){
        return new CategoryStoreDto(
                this.id,
                this.name,
                this.description,
                this.image,
                this.location
        );
    }

    public StoreDto toStoreDto() {
        return new StoreDto(
                this.name,
                this.description,
                this.averageRating,
                this.image,
                this.location,
                reviews.stream().map(Review::toDto).collect(Collectors.toList())
        );
    }

    public LocationStoreDto toLocationDto() {
        return new LocationStoreDto(
                this.id,
                this.name,
                this.category.name(),
                this.latitude,
                this.longitude
        );
    }
}
