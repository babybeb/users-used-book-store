package babybeb.usersusedbookstore.api;

import babybeb.usersusedbookstore.api.dto.item.ChangeDealStatusRequest;
import babybeb.usersusedbookstore.api.dto.item.CreateItemDto;
import babybeb.usersusedbookstore.api.dto.item.ItemDto;
import babybeb.usersusedbookstore.api.dto.item.ItemResponse;
import babybeb.usersusedbookstore.api.dto.item.SaveItemRequest;
import babybeb.usersusedbookstore.api.dto.item.UpdateItemRequest;
import babybeb.usersusedbookstore.api.dto.sale.ChangeStatusRequest;
import babybeb.usersusedbookstore.api.dto.sale.ChangeStatusResponse;
import babybeb.usersusedbookstore.api.dto.sale.DeleteSaleResponse;
import babybeb.usersusedbookstore.api.dto.sale.SaveSaleRequest;
import babybeb.usersusedbookstore.api.dto.sale.SaveSaleResponse;
import babybeb.usersusedbookstore.domain.Book;
import babybeb.usersusedbookstore.domain.Item;
import babybeb.usersusedbookstore.domain.Member;
import babybeb.usersusedbookstore.domain.Sale;
import babybeb.usersusedbookstore.service.ItemService;
import babybeb.usersusedbookstore.service.MemberService;
import babybeb.usersusedbookstore.service.PurchaseService;
import babybeb.usersusedbookstore.service.SaleService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {
    
    private final ItemService itemService;
    private final SaleService saleService;
    private final PurchaseService purchaseService;
    private final MemberService memberService;
    
    @PostMapping("/{member_id}")
    public ResponseEntity<Long> saveItem(
        @PathVariable("member_id") Long memberId,
        @RequestBody @Valid SaveItemRequest request) {
        
        Book book = new Book(request.getIsbn(), request.getTitle(), request.getBookPrice(),
                             request.getPublisher(), request.getAuthor(), request.getPage(),
                             request.getKdc(),
                             request.getCategory());
        CreateItemDto createItemDto = new CreateItemDto(book, request.getPrice(),
                                                        request.getItemCondition(),
                                                        request.getDealArea());
        
        Long itemId = itemService.saveItem(createItemDto);
        
        saleService.addSale(memberId, itemService.findById(itemId));
        
        //이미지 등록을 위한 itemId 값 반환
        return ResponseEntity.ok(itemId);
    }
    
    @DeleteMapping("/{item_id}")
    public ResponseEntity<Long> deleteItem(
        @PathVariable("item_id") Long itemId) {
        
        itemService.deleteItem(itemId);
        saleService.cancelSale(itemId);
        
        return ResponseEntity.ok(itemId);
    }
    
    @PostMapping("{item_id}/dealstatus")
    public ResponseEntity<ItemResponse> changeDealStatus(
        @PathVariable("item_id") Long itemId,
        @RequestBody @Valid ChangeDealStatusRequest request) {
        
        Item findItem = itemService.findById(itemId);
        
        if(request.getDealStatus().equals("COMP")) {
            // 실재하는 회원인지 검사
            // String option = memberService.실재하는 회원인가요?
            // if option.isEqual("false") { throw exception }
            // else if (option.isEqual("email")) {Long buyerId = memberService.findByEmail(request.getBuyerInfo);}
            // else if (option.isEqual("phone")) {Long buyerId = memberService.findByPhoneNumber(request.getBuyerInfo);}
            // purchaseService.savePurchase(buyerId, findItem);
        }
        itemService.changeDealStatus(findItem, request.getDealStatus());
        
        return ResponseEntity.ok(ItemResponse.toItemResponse(itemService.findById(itemId)));
    }
    
    @PutMapping("/{item_id}")
    public ResponseEntity<ItemResponse> updateItem(
        @PathVariable("item_id") Long itemId,
        @RequestBody UpdateItemRequest request) {
        
        Item findItem = itemService.findById(itemId);
        itemService.updateItem(itemId, findItem.getBook(), request.getItemPrice(),
                               request.getItemCondition(), request.getDealArea());
        
        return ResponseEntity.ok(ItemResponse.toItemResponse(itemService.findById(itemId)));
    }
    
    @GetMapping("/{item_id}")
    public ResponseEntity<ItemResponse> findItem(@PathVariable("item_id") Long itemId) {
        
        return ResponseEntity.ok(ItemResponse.toItemResponse(itemService.findById(itemId)));
    }
}
