package project.dbproject.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.dbproject.domain.Member;
import project.dbproject.domain.Review;
import project.dbproject.domain.Store;
import project.dbproject.dto.DeleteReviewRequestDto;
import project.dbproject.dto.RequestReviewDto;
import project.dbproject.repository.MemberRepository;
import project.dbproject.repository.ReviewRepository;
import project.dbproject.repository.StoreRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    public Review getReview(final Long reviewId) {
        return reviewRepository.findById(reviewId);
    }

    public List<Review> getReviewsByStoreId(final Long storeId) {
        return reviewRepository.findAllReviewsByStoreId(storeId);
    }

    public Long saveReview(final RequestReviewDto dto) {
        Member member = memberRepository.findById(dto.getMemberId());
        Store store = storeRepository.findById(dto.getStoreId());

        final Review review = dto.toEntity();
        review.addMember(member);
        review.addStore(store);
        store.updateAverageRating();

        reviewRepository.save(review);
        return review.getId();
    }

    public Long updateReview(final Long reviewId, final RequestReviewDto dto) {
        Store store = storeRepository.findById(dto.getStoreId());
        Review review = reviewRepository.findById(reviewId);
        if (review != null) {
            review.updateReview(dto);
            store.updateAverageRating();
            return review.getId();
        }
        return null;
    }

    public void deleteReview(final Long reviewId, DeleteReviewRequestDto dto) {
        Store store = storeRepository.findById(dto.getStoreId());
        reviewRepository.deleteReview(reviewId, dto.getMemberId());
        store.updateAverageRating();
    }
}
