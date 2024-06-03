package project.dbproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import project.dbproject.domain.Review;

@Data
@AllArgsConstructor
public class ResponseReviewDto {
    private Long reviewId;
    private Long memberId;
    private String memberName;
    private Integer rating;
    private String reviewText;
    private String createdDate;
    private String modifiedDate;

    public ResponseReviewDto(final Review review) {
        this.reviewId = review.getId();
        this.memberId = review.getMember().getId();
        this.memberName = review.getMember().getUserId();
        this.rating = review.getRating().intValue();
        this.reviewText = review.getComment();
        this.createdDate = review.getCreatedDateTime();
        this.modifiedDate = review.getModifiedDateTime();
    }
}