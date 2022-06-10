package babybeb.usersusedbookstore.api.dto.purchase;

import lombok.Getter;

@Getter
public class RateSellerRequest {
    
    private Long sellerId;
    private int rate;
}
