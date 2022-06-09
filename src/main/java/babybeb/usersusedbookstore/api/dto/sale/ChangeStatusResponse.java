package babybeb.usersusedbookstore.api.dto.sale;

import babybeb.usersusedbookstore.domain.DealStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChangeStatusResponse {
    //반환값
    private DealStatus dealStatus;
}
