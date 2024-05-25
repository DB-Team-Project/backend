package project.dbproject.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import project.dbproject.domain.Member;

import java.util.Optional;

@Repository
public class MemberRepository {
    private final EntityManager em;

    public MemberRepository(EntityManager em) {
        this.em = em;
    }

    public void save(final Member member) {
        em.persist(member);
    }

    public Member findById(final Long id) {
        return em.find(Member.class, id);
    }

    public Optional<Member> findByUserId(String memberName) {
        try {
            TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m WHERE m.userId = :memberName", Member.class);
            query.setParameter("memberName", memberName);
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
