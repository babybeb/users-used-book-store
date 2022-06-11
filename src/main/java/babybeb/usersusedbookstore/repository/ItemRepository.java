package babybeb.usersusedbookstore.repository;

import babybeb.usersusedbookstore.domain.Item;
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
        return em.find(Item.class, id);
    }
    
    public Long delete(Long itemId) {
        em.remove(findOne(itemId));
        return itemId;
    }
}