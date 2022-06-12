package babybeb.usersusedbookstore.api.dto.item;

import lombok.Getter;

@Getter
public class SaveItemRequest {
    
    private int price;
    private String itemCondition;
    private String isbn;
    private String title;
    private int bookPrice;
    private String publisher;
    private String author;
    private int page;
    private String kdc;
    private String category;
    private String first;
    private String second;
}