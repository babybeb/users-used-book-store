package babybeb.usersusedbookstore.repository;

import babybeb.usersusedbookstore.domain.Item;
import babybeb.usersusedbookstore.domain.Purchase;
import babybeb.usersusedbookstore.domain.Sale;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SaleRepository {

    private final EntityManager em;

    public void save(Sale sale){
        em.persist(sale);
    }

    public Sale findOne(Long id){
        return em.find(Sale.class, id);
    }

    public List<Sale> findByMemberId(Long memberId) {
        return em.createQuery("select s from Sale s where s.member.id = :memberId", Sale.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }
    public Sale findByItemId(Long itemId){
        return em.createQuery("select s from Sale s where s.item.id = :itemId", Sale.class)
                .setParameter("itemId", itemId)
                .getSingleResult();
    }

    public void removeSale(Sale sale){
        em.remove(sale);
    }
}