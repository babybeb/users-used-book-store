package babybeb.usersusedbookstore.service;

import babybeb.usersusedbookstore.domain.Category;
import babybeb.usersusedbookstore.domain.Item;
import babybeb.usersusedbookstore.domain.dealarea.DealArea;
import babybeb.usersusedbookstore.domain.dealarea.First;
import babybeb.usersusedbookstore.domain.dealarea.Second;
import babybeb.usersusedbookstore.repository.ItemSearchRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemSearchService {
    
    private final ItemSearchRepository itemSearchRepository;
    
    public List<Item> findItems() {
        return itemSearchRepository.findAll();
    }
    
    public List<Item> findAllByTitleContains(String title) {
        return itemSearchRepository.findByBookTitleContains(title);
    }
    
    public List<Item> findAllByCategory(String category) {
        
        /* String type의 category를 enum type으로 변환 */
        Category enumCategory;
        enumCategory = Category.valueOf(category);
        
        return itemSearchRepository.findAllByBookCategory(enumCategory);
    }
    
    public List<Item> findAllByDealArea(String first, String second) {
    
//        return itemSearchRepository.findAllByDealArea(new DealArea(First.valueOf(first), Second.valueOf(second)));
        return itemSearchRepository.findByDealAreaFirstAndDealAreaSecond(First.valueOf(first), Second.valueOf(second));
    }
    
    public List<Item> findAllByDealArea(String first) {
        System.out.println("second가 비어있음");
        return itemSearchRepository.findByDealAreaFirst(First.valueOf(first));
    }
}
