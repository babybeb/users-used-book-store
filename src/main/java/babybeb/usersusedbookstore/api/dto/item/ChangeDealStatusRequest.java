package babybeb.usersusedbookstore.api.dto.item;

import lombok.Getter;

@Getter
public class ChangeDealStatusRequest {

    private String buyerInfo; // 구매자의 이메일 혹은 휴대폰번호
    private String dealStatus;
}
