package babybeb.usersusedbookstore.api.dto.member;

import babybeb.usersusedbookstore.domain.Book;
import babybeb.usersusedbookstore.domain.ItemCondition;
import babybeb.usersusedbookstore.domain.dealarea.DealArea;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ResponseItem {
    //책정보
    private Book book;

    //아이템 정보
    private int price;
    private int hit;
    private ItemCondition itemCondition;
    private DealArea dealArea;
    private LocalDateTime createDate;
}
