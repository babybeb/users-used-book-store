package babybeb.usersusedbookstore.api;

import babybeb.usersusedbookstore.api.dto.item.ItemResponse;
import babybeb.usersusedbookstore.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {
    
    private final ItemService itemService;
//
//    @PostMapping("/items")
//    public CreateItemResponse saveItem(@RequestBody CreateItemRequest request) {
//
//        Item item = new Item(request.getBook(),request.getItemPrice(), request.getItemCondition(), request.getCreateDate());
//
//        Long id = itemService.saveItem(item);
//
//        return new CreateItemResponse(id);
//    }
    
//    @PutMapping("/items/{item_id}")
//    public UpdateItemResponse updateMemberV2(
//        @PathVariable("item_id") Long itemId,
//        @RequestBody @Valid UpdateItemRequest request) {
//
//        itemService.updateItem(itemId, request.getName());
//        Item findItem = itemService.findById(itemId);
//        return new UpdateItemResponse(findMember.getId(), findMember.getName());
//    }
    
    @GetMapping("/{item_id}")
    public ResponseEntity<ItemResponse> findItem(@PathVariable("item_id") Long itemId) {
        
        return ResponseEntity.ok(ItemResponse.toItemResponse(itemService.findById(itemId)));
    }
}
