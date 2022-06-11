package babybeb.usersusedbookstore.api;

import babybeb.usersusedbookstore.api.dto.item.ItemResponse;
import babybeb.usersusedbookstore.api.dto.item.UpdateItemRequest;
import babybeb.usersusedbookstore.domain.Item;
import babybeb.usersusedbookstore.service.ItemService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {
    
    private final ItemService itemService;
    
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
