package babybeb.usersusedbookstore.repository;

import babybeb.usersusedbookstore.domain.Category;
import babybeb.usersusedbookstore.domain.Item;
import babybeb.usersusedbookstore.domain.dealarea.DealArea;
import babybeb.usersusedbookstore.domain.dealarea.First;
import babybeb.usersusedbookstore.domain.dealarea.Second;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemSearchRepository extends JpaRepository<Item, Long> {
    
    public List<Item> findAll();
    
    public List<Item> findByBookTitleContains(String title);
    
    public List<Item> findAllByBookCategory(Category category);
    
//    public List<Item> findAllByDealArea(DealArea dealArea);
    
    public List<Item> findByDealAreaFirstAndDealAreaSecond(First first, Second second);
    
    public List<Item> findByDealAreaFirst(First first);
}
