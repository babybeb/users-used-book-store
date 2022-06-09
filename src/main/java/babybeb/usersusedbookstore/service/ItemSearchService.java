package babybeb.usersusedbookstore.service;

import babybeb.usersusedbookstore.domain.Item;
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
}
