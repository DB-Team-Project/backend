package project.dbproject.dto;

import lombok.Getter;
import project.dbproject.domain.Review;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class RequestReviewDto {
    private Long memberId;
    private Long storeId;
    private BigDecimal rating;
    private String comment;
    private String createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    private String modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));

    public Review toEntity() {
        Review review = new Review();
        review.setComment(comment);
        review.setRating(rating);
        review.setCreatedDateTime(createdDate);
        review.setModifiedDateTime(modifiedDate);
        return review;
    }
}
