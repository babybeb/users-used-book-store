package babybeb.usersusedbookstore.repository;

import babybeb.usersusedbookstore.domain.Item;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    
    private final EntityManager em;
    
    public Long save(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        } else {
            em.merge(item);
        }
        return item.getId();
    }
    
    public void update(Item item) {
        em.merge(item);
    }
    
    public Item findOne(Long id) {
        return em.find(Item.class,id);
    }
    
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
            .getResultList();
    }
}