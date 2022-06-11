package babybeb.usersusedbookstore.api.dto.item;

import babybeb.usersusedbookstore.domain.Book;
import babybeb.usersusedbookstore.domain.ItemCondition;
import babybeb.usersusedbookstore.domain.dealarea.DealArea;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateItemDto {
    
    private Book book;
    private int itemPrice;
    private ItemCondition itemCondition;
    private DealArea dealArea;
}