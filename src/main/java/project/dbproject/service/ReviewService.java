package project.dbproject.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.dbproject.domain.Member;
import project.dbproject.domain.Review;
import project.dbproject.domain.Store;
import project.dbproject.dto.RequestReviewDto;
import project.dbproject.repository.MemberRepository;
import project.dbproject.repository.ReviewRepository;
import project.dbproject.repository.StoreRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    public Long saveReview(final RequestReviewDto dto) {
        // 멤버와 스토어 엔티티 찾기
        Member member = memberRepository.findById(dto.getMemberId());
        Store store = storeRepository.findById(dto.getStoreId());

        // DTO에서 리뷰 엔티티 생성
        final Review review = dto.toEntity();
        review.addMember(member);
        review.addStore(store);

        // 리뷰 저장 및 ID 반환
        reviewRepository.save(review);

        return review.getId();
    }

    public Long updateReview(final Long reviewId, final RequestReviewDto dto) {
        Review review = reviewRepository.findById(reviewId);
        if (review != null) {
            review.updateReview(dto);
            return review.getId();
        }
        return null;
    }

    public void deleteReview(final Long reviewId, final Long memberId) {
        reviewRepository.deleteReview(reviewId, memberId);
    }
}
