package babybeb.usersusedbookstore.api;

import babybeb.usersusedbookstore.domain.dto.ItemResponse;
import babybeb.usersusedbookstore.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {
    
    private final ItemService itemService;
    
    @PostMapping("/{item_id}")
    public ResponseEntity<ItemResponse> findItem(@PathVariable("item_id") Long itemId) {
            
            return ResponseEntity.ok(ItemResponse.toItemResponse(itemService.findById(itemId)));
    }
}
