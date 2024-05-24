package project.dbproject.controller.review;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.dbproject.dto.DeleteReviewRequestDto;
import project.dbproject.dto.RequestReviewDto;
import project.dbproject.service.ReviewService;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/review")
    public ResponseEntity addReview(@RequestBody RequestReviewDto reviewDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.saveReview(reviewDto));
    }


    @DeleteMapping("/review/{reviewId}")
    public ResponseEntity deleteReview(@PathVariable("reviewId") Long reviewId, @RequestBody DeleteReviewRequestDto deleteDto) {
        reviewService.deleteReview(reviewId, deleteDto.getMemberId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/review/{reviewId}")
    public ResponseEntity updateReview(@PathVariable("reviewId") Long reviewId, @RequestBody RequestReviewDto updateDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.updateReview(reviewId, updateDto));
    }
}
