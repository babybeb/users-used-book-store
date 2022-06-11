package babybeb.usersusedbookstore.api.dto.item;

import babybeb.usersusedbookstore.domain.Category;
import babybeb.usersusedbookstore.domain.ItemCondition;
import babybeb.usersusedbookstore.domain.dealarea.DealArea;
import lombok.Getter;

@Getter
public class SaveItemRequest {
    
    private int price;
    private ItemCondition itemCondition;
    private String isbn;
    private String title;
    private int bookPrice;
    private String publisher;
    private String author;
    private int page;
    private String kdc;
    private Category category;
    private DealArea dealArea;
}
