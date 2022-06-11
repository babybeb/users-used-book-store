package babybeb.usersusedbookstore.api;

import babybeb.usersusedbookstore.api.dto.item.ChangeDealStatusRequest;
import babybeb.usersusedbookstore.api.dto.item.CreateItemDto;
import babybeb.usersusedbookstore.api.dto.item.ItemResponse;
import babybeb.usersusedbookstore.api.dto.item.SaveItemRequest;
import babybeb.usersusedbookstore.api.dto.item.UpdateItemRequest;
import babybeb.usersusedbookstore.domain.Book;
import babybeb.usersusedbookstore.domain.DealStatus;
import babybeb.usersusedbookstore.domain.Item;
import babybeb.usersusedbookstore.service.ItemService;
import babybeb.usersusedbookstore.service.MemberService;
import babybeb.usersusedbookstore.service.PurchaseService;
import babybeb.usersusedbookstore.service.SaleService;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/items")
public class ItemController {
    
    private final ItemService itemService;
    private final SaleService saleService;
    private final PurchaseService purchaseService;
    private final MemberService memberService;
    
    /**
     * Item 저장
     *
     * @param memberId
     * @param request
     * @return itemId
     */
    @ApiOperation(value = "memberId와 SaveItemRequest를 받아 Item 저장")
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
    
    /**
     * Item 삭제
     *
     * @param itemId
     * @return return itemId
     */
    @ApiOperation(value = "itemId를 받아 Item 삭제")
    @DeleteMapping("/{item_id}")
    public ResponseEntity<Long> deleteItem(
        @PathVariable("item_id") Long itemId) {
        
        Item findItem = itemService.findById(itemId);
        if (findItem.getDealStatus().equals(DealStatus.SALE)) {
            return new ResponseEntity("이미 거래 완료된 상품은 삭제할 수 없습니다.", HttpStatus.OK);
        }
        
        itemService.deleteItem(itemId);
        saleService.cancelSale(itemId);
        
        return ResponseEntity.ok(itemId);
    }
    
    /**
     * DealStatus 변경
     *
     * @param itemId
     * @param request
     * @return "존재하지 않는 회원입니다." 또는 ItemResponse
     */
    @ApiOperation(value = "itemId와 ChangeDealStatusRequest를 받아 Item의 DealStatus 변경")
    @PatchMapping("/{item_id}")
    public ResponseEntity<ItemResponse> changeDealStatus(
        @PathVariable("item_id") Long itemId,
        @RequestBody @Valid ChangeDealStatusRequest request) {
        
        Item findItem = itemService.findById(itemId);
        
        if (request.getDealStatus().equals("COMP")) {
            // 실재하는 회원인지 검사
            Long buyerId = 0l;
            String option = memberService.isAlreadyExists(request.getBuyerInfo());
            if (option.equals("email")) { // option 값은 enum이나 int로 바꿔도 될듯?
                buyerId = memberService.findByEmail(request.getBuyerInfo()).getId();
            } else if (option.equals("phone")) {
                buyerId = memberService.findByPhoneNumber(request.getBuyerInfo()).getId();
            } else {
                return new ResponseEntity("존재하지 않는 회원입니다.", HttpStatus.OK);
            }
            purchaseService.savePurchase(buyerId, findItem);
        } else if (findItem.getDealStatus().equals(DealStatus.SALE)) {
            return new ResponseEntity("이미 거래 완료된 상품에 대해서는 거래 상태를 변경할 수 없습니다.", HttpStatus.OK);
        }
        itemService.changeDealStatus(findItem, request.getDealStatus());
        
        return ResponseEntity.ok(ItemResponse.toItemResponse(itemService.findById(itemId)));
    }
    
    /**
     * Item 수정
     *
     * @param itemId
     * @param request
     * @return ItemResponse
     */
    @ApiOperation(value = "itemId와 UpdateItemRequest를 받아 Item Update")
    @PutMapping("/{item_id}")
    public ResponseEntity<ItemResponse> updateItem(
        @PathVariable("item_id") Long itemId,
        @RequestBody UpdateItemRequest request) {
        
        Item findItem = itemService.findById(itemId);
        itemService.updateItem(itemId, findItem.getBook(), request.getItemPrice(),
                               request.getItemCondition(), request.getDealArea());
    
        if (findItem.getDealStatus().equals(DealStatus.SALE)) {
            return new ResponseEntity("이미 거래 완료된 상품에 대해서는 상품 상태를 변경할 수 없습니다.", HttpStatus.OK);
        }
        
        return ResponseEntity.ok(ItemResponse.toItemResponse(itemService.findById(itemId)));
    }
    
    /**
     * Item 찾기
     *
     * @param itemId
     * @return ItemResponse
     */
    @ApiOperation(value = "itemId를 받아 Item 개별 조회")
    @GetMapping("/{item_id}")
    public ResponseEntity<ItemResponse> findItem(@PathVariable("item_id") Long itemId) {
        
        return ResponseEntity.ok(ItemResponse.toItemResponse(itemService.findById(itemId)));
    }
}