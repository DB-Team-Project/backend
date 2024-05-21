package project.dbproject.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import project.dbproject.domain.Store;

import java.util.List;

@Repository
public class StoreRepository {
    private final EntityManager em;

    public StoreRepository(EntityManager em) {
        this.em = em;
    }

    public void save(final Store store) {
        em.persist(store);
    }

    public Store findById(final Long id) {
        return em.find(Store.class, id);
    }

    public List<Store> findAll() {
        TypedQuery<Store> query = em.createQuery("SELECT s FROM Store s", Store.class);
        return query.getResultList();
    }

    //카테고리별 조회
    public List<Store> findByCategoryName(final String categoryName) {
        TypedQuery<Store> query = em.createQuery("SELECT s FROM Store s WHERE s.category.categoryName = :categoryName", Store.class);
        query.setParameter("categoryName", categoryName);
        return query.getResultList();
    }
}
