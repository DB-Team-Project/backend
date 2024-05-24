package project.dbproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

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
}