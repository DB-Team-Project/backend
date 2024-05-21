package project.dbproject.repository;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import project.dbproject.domain.Member;

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
}
