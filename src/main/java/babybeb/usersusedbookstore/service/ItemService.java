package babybeb.usersusedbookstore.service;

import babybeb.usersusedbookstore.domain.Book;
import babybeb.usersusedbookstore.domain.dealarea.DealArea;
import babybeb.usersusedbookstore.domain.Item;
import babybeb.usersusedbookstore.domain.ItemCondition;
import babybeb.usersusedbookstore.repository.ItemRepository;
import java.util.List;
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
    public void updateItem(Long itemId, Book book, int itemPrice, ItemCondition itemCondition, DealArea dealArea) {
        Item findItem = itemRepository.findOne(itemId);
        
        findItem.changeBook(book);
        findItem.changeItemPrice(itemPrice);
        findItem.changeItemCondition(itemCondition);
        findItem.changeDealArea(dealArea);
    }
    
    public Item findById(Long itemId) {
        return itemRepository.findOne(itemId);
    }
    
    public List<Item> findItems() {
        return itemRepository.findAll();
    }
}
