package project.dbproject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import project.dbproject.dto.RequestReviewDto;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Review {
    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Column(precision = 2, scale = 1)
    private BigDecimal rating = BigDecimal.ZERO;

    private String comment;

    private String createdDateTime;

    private String modifiedDateTime;

    public void addStore(final Store store) {
        this.store = store;
        store.getReviews().add(this);
    }

    public void addMember(final Member member) {
        this.member = member;
        member.getReviews().add(this);
    }

    public void updateReview(final RequestReviewDto reviewDto) {
        if (reviewDto.getRating() != null) {
            this.rating = reviewDto.getRating();
        }
        if (reviewDto.getComment() != null) {
            this.comment = reviewDto.getComment();
        }
        if (reviewDto.getCreatedDate() != null) {
            this.createdDateTime = reviewDto.getCreatedDate();
        }
        if (reviewDto.getModifiedDate() != null) {
            this.modifiedDateTime = reviewDto.getModifiedDate();
        }
    }
}
