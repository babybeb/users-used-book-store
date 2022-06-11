package babybeb.usersusedbookstore.api.dto.sale;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SaleToItemResponse {
    private String nickname;
    private String phoneNumber;
    private String email;
}
