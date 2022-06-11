package babybeb.usersusedbookstore.api.dto.member;

import babybeb.usersusedbookstore.api.dto.book.BookResponse;
import babybeb.usersusedbookstore.domain.Book;
import babybeb.usersusedbookstore.domain.DealStatus;
import babybeb.usersusedbookstore.domain.Item;
import babybeb.usersusedbookstore.domain.ItemCondition;
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
    private Long id;
    private int price;
    private int hit;
    private ItemCondition itemCondition;
    private DealArea dealArea;
    private LocalDateTime createDate;
    private DealStatus dealStatus;

    public static MemberSalesResponse toItemResponse(ResponseItem responseItem) {
        return new MemberSalesResponse(responseItem.getBook().getTitle(), responseItem.getId(),
                responseItem.getPrice(), responseItem.getHit(), responseItem.getItemCondition(),
                responseItem.getDealArea(), responseItem.getCreateDate(), responseItem.getDealStatus());
    }
}
