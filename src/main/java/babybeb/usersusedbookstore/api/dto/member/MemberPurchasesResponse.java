package babybeb.usersusedbookstore.api.dto.member;

import babybeb.usersusedbookstore.domain.Item;
import babybeb.usersusedbookstore.domain.ItemCondition;
import babybeb.usersusedbookstore.domain.dealarea.DealArea;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MemberPurchasesResponse {
    //책 정보
    private String title;

    //아이템 정보
    private int price;
    private int hit;
    private ItemCondition itemCondition;
    private DealArea dealArea;
    private LocalDateTime createDate;

    public static MemberPurchasesResponse toItemResponse(ResponseItem responseItem) {
        return new MemberPurchasesResponse(responseItem.getBook().getTitle(), responseItem.getPrice(),
                responseItem.getHit(), responseItem.getItemCondition(), responseItem.getDealArea(),
                responseItem.getCreateDate());
    }
}
