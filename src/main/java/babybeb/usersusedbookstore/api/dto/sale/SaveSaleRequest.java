package babybeb.usersusedbookstore.api.dto.sale;

import babybeb.usersusedbookstore.domain.Category;
import babybeb.usersusedbookstore.domain.ItemCondition;
import babybeb.usersusedbookstore.domain.dealarea.DealArea;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class SaveSaleRequest {
    //받아야 하는 값
    private int price;
    private ItemCondition itemCondition;

    //Book 데이터
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
