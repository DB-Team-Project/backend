package project.dbproject.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import project.dbproject.domain.Review;

import java.util.List;

@Repository
public class ReviewRepository {
    private final EntityManager em;

    public ReviewRepository(EntityManager em) {
        this.em = em;
    }

    public void save(final Review review) {
        em.persist(review);
    }

    public List<Review> findAllReviewsByStoreId(final Long storeId) {
        TypedQuery<Review> query = em.createQuery("SELECT r FROM Review r WHERE r.store.id = :storeId", Review.class);
        query.setParameter("storeId", storeId);
        return query.getResultList();
    }

    public void deleteReviewByMemberAndId(final Long reviewId, final Long memberId) {
        Review review = em.find(Review.class, reviewId);
        if (review == null) {
            throw new IllegalArgumentException("Review with ID " + reviewId + " not found.");
        }
        if (!review.getMember().getId().equals(memberId)) {
            throw new IllegalArgumentException("Member with id " + memberId + " is not the author of the review with id " + reviewId);
        }

        em.remove(review);
    }
}
