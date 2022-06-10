package babybeb.usersusedbookstore.api.dto.purchase;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RateSellerResponse {
    private String sellerNickName;
    private int newRate;
}