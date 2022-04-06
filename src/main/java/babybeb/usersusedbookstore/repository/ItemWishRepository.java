package babybeb.usersusedbookstore.repository;

import babybeb.usersusedbookstore.domain.ItemWish;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemWishRepository {

    private final EntityManager em;

    public void save(ItemWish itemWish){
        em.persist(itemWish);
    }

    public ItemWish findOne(Long id){
        return em.find(ItemWish.class, id);
    }

    public List<ItemWish> findByMemberId(Long memberId) {
        return em.createQuery("select iw from ItemWish iw where iw.member.id = :memberId", ItemWish.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    public List<ItemWish> findByItemId(Long itemId){
        return em.createQuery("select iw from ItemWish iw where iw.item.id = :itemId", ItemWish.class)
                .setParameter("itemId", itemId)
                .getResultList();
    }

    public void cancelItemWish(ItemWish itemWish){
        em.remove(itemWish);
    }
}
