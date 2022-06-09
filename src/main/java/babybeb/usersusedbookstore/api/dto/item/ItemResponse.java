package babybeb.usersusedbookstore.api.dto.item;

import babybeb.usersusedbookstore.domain.Book;
import babybeb.usersusedbookstore.domain.dealarea.DealArea;
import babybeb.usersusedbookstore.domain.DealStatus;
import babybeb.usersusedbookstore.domain.Item;
import babybeb.usersusedbookstore.domain.ItemCondition;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponse {
    
    private Long id;
    
    private Book book;
    
    private int itemPrice;
    
    private ItemCondition itemCondition;
    
    private DealArea dealArea; // value object
    
    private int hit;
    
    private DealStatus dealStatus;
    
    private LocalDateTime createDate;
    
    public static ItemResponse toItemResponse(Item item) {
        return new ItemResponse(item.getId(), item.getBook(), item.getItemPrice(),
                                item.getItemCondition(), item.getDealArea(), item.getHit(),
                                item.getDealStatus(), item.getCreateDate());
    }
}