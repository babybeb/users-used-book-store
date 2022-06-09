package babybeb.usersusedbookstore.api.dto.member;

import babybeb.usersusedbookstore.api.dto.book.BookResponse;
import babybeb.usersusedbookstore.domain.Book;
import babybeb.usersusedbookstore.domain.Item;
import babybeb.usersusedbookstore.domain.dealarea.DealArea;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MemberSalesResponse {
    
    //책정보
    private String title;

    //아이템 정보
    private int price;
    private int hit;
    private DealArea dealArea;
    private String createDate;

    public static MemberSalesResponse toItemResponse(Item item) {
        return new MemberSalesResponse(item.getBook().getTitle(), item.getItemPrice(), item.getHit(),
                item.getDealArea(), item.getCreateDate().toString());
    }
}
