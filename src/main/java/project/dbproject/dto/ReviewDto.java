package project.dbproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ReviewDto {
    private Long reviewId;
    private String memberName;
    private Integer rating;
    private String reviewText;
    private LocalDateTime reviewDate;
}