package babybeb.usersusedbookstore.api.dto.item;

import babybeb.usersusedbookstore.domain.Book;
import babybeb.usersusedbookstore.domain.DealStatus;
import babybeb.usersusedbookstore.domain.ItemCondition;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ItemDto {
    
    private Book book;
    private int itemPrice;
    private ItemCondition itemCondition;
    private LocalDateTime createDate;
    private DealStatus dealStatus;
    private int hit;
}