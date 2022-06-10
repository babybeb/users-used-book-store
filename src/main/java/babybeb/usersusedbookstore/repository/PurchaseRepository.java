package babybeb.usersusedbookstore.repository;

import babybeb.usersusedbookstore.domain.Purchase;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PurchaseRepository {
    
    private final EntityManager em;
    
    public Long save(Purchase purchase) {
        em.persist(purchase);
        return purchase.getId();
    }
    
    public Purchase findOne(Long id) {
        return em.find(Purchase.class, id);
    }
    
    public List<Purchase> findByMemberId(Long memberId) {
        return em.createQuery("select p from Purchase p where p.member.id = :memberId",
                              Purchase.class)
            .setParameter("memberId", memberId)
            .getResultList();
    }
}
