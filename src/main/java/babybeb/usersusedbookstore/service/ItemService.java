package babybeb.usersusedbookstore.service;

import babybeb.usersusedbookstore.domain.Book;
import babybeb.usersusedbookstore.domain.ImageFile;
import babybeb.usersusedbookstore.domain.Item;
import babybeb.usersusedbookstore.domain.ItemCondition;
import babybeb.usersusedbookstore.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    
    private final ItemRepository itemRepository;
    
    @Transactional
    public Long saveItem(Item item) {
        Long id = itemRepository.save(item);
        return id;
    }
    
    @Transactional
    public void updateItem(Long itemId, Book book, int itemPrice, ItemCondition itemCondition, ImageFile imageFile) {
    
    }
    
    public Item findById(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
