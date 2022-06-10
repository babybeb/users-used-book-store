package babybeb.usersusedbookstore.service;

import babybeb.usersusedbookstore.domain.Book;
import babybeb.usersusedbookstore.domain.Member;
import babybeb.usersusedbookstore.domain.Purchase;
import babybeb.usersusedbookstore.domain.dealarea.DealArea;
import babybeb.usersusedbookstore.domain.Item;
import babybeb.usersusedbookstore.domain.ItemCondition;
import babybeb.usersusedbookstore.repository.ItemRepository;
import babybeb.usersusedbookstore.repository.MemberRepository;
import babybeb.usersusedbookstore.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    
    private final ItemRepository itemRepository;
    //    private final PurchaseService purchaseService;
    private final PurchaseRepository purchaseRepository;
    private final MemberRepository memberRepository;
    
    @Transactional
    public Long saveItem(Item item) {
        Long id = itemRepository.save(item);
        return id;
    }
    
    @Transactional
    public void changeDealStatus(Long buyerId, Long itemId, String dealStatus) {
        Item findItem = itemRepository.findOne(itemId);
        
        if (dealStatus.equals("COMP")) {
            savePurchase(buyerId, findItem);
        }
        
        findItem.changeDealStatus(dealStatus);
    }
    
    private void savePurchase(Long buyerId, Item findItem) {
        Member findMember = memberRepository.findOne(buyerId);
        Purchase purchase = new Purchase(findMember, findItem);
        purchaseRepository.save(purchase);
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
    
    public Item findById(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
