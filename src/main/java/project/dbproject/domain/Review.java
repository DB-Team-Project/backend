package project.dbproject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import project.dbproject.dto.ReviewDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Review {
    @Id @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Column(precision = 2, scale = 1)
    private BigDecimal rating = BigDecimal.ZERO;

    private String comment;

    private LocalDateTime reviewDate;

    public void addStore(final Store store) {
        this.store = store;
        store.getReviews().add(this);
    }
    public void addMember(final Member member) {
        this.member = member;
        member.getReviews().add(this);
    }

    public ReviewDto toDto(){
        return new ReviewDto(
                this.id,
                this.member.getUsername(),
                this.rating.intValue(), // assuming this is valid conversion
                this.comment,
                this.reviewDate
        );
    }
}
