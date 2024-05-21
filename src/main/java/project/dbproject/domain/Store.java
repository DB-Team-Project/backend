package project.dbproject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Store {
    @Id @GeneratedValue
    @Column(name = "store_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    private String name;

    private String description;

    private String location;

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

    public void addCategory(final Category category) {
        this.category = category;
        category.getStores().add(this);
    }
}
