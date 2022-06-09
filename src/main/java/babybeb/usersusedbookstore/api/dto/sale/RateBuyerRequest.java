package babybeb.usersusedbookstore.api.dto.sale;

import lombok.Getter;

@Getter
public class RateBuyerRequest {
    //받아야 하는 값
    private Long buyerId;
    private int rate;
}
