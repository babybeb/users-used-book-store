package babybeb.usersusedbookstore.service;

import babybeb.usersusedbookstore.api.dto.item.CreateItemDto;
import babybeb.usersusedbookstore.domain.Book;
import babybeb.usersusedbookstore.domain.Member;
import babybeb.usersusedbookstore.domain.Purchase;
import babybeb.usersusedbookstore.domain.dealarea.DealArea;
import babybeb.usersusedbookstore.domain.Item;
import babybeb.usersusedbookstore.domain.ItemCondition;
import babybeb.usersusedbookstore.repository.ItemRepository;
import babybeb.usersusedbookstore.repository.MemberRepository;
import babybeb.usersusedbookstore.repository.PurchaseRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    
    private final ItemRepository itemRepository;
    private final PurchaseRepository purchaseRepository;
    private final MemberRepository memberRepository;
    
    @Transactional
    public Long saveItem(CreateItemDto createItemDto) {
        Item item = new Item(createItemDto.getBook(), createItemDto.getItemPrice(),
                             createItemDto.getItemCondition(), createItemDto.getDealArea());
        Long id = itemRepository.save(item);
        return id;
    }
    
    @Transactional
    public void changeDealStatus(Item item, String dealStatus) {
        
        item.changeDealStatus(dealStatus);
    }
    
    @Transactional
    public Long deleteItem(Long itemId) {
        return itemRepository.delete(itemId);
    }
    
    @Transactional
    public void updateItem(Long itemId, Book book, int itemPrice, ItemCondition itemCondition,
                           DealArea dealArea) {
        Item findItem = itemRepository.findOne(itemId);
        
        findItem.changeBook(book);
        findItem.changeItemPrice(itemPrice);
        findItem.changeItemCondition(itemCondition);
        findItem.changeDealArea(dealArea);
    }
    
    @Transactional
    public Item findById(Long itemId) {
    
        Item item = itemRepository.findOne(itemId);
        item.addHit();
        
        return item;
    }
}