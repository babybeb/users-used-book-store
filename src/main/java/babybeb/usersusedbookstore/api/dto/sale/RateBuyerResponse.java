package babybeb.usersusedbookstore.api.dto.sale;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RateBuyerResponse {
    private String buyerNickname;
    private int rate;
}
