package babybeb.usersusedbookstore.repository;

import babybeb.usersusedbookstore.domain.Item;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ItemSearchRepository {
    
    private final EntityManager em;
    
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
            .getResultList();
    }
}
