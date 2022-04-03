package babybeb.usersusedbookstore.repository;

import babybeb.usersusedbookstore.domain.Sale;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SaleRepository {

    private final EntityManager em;

    //거래 상품 등록
    public void save(Sale sale){
        em.persist(sale);
    }

    public Sale findOne(Long id){
        return em.find(Sale.class, id);
    }

    public List<Sale> findAll(){
        return em.createQuery("select s from Sale s", Sale.class)
                .getResultList();
    }
}