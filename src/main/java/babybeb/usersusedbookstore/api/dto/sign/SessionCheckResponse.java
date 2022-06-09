package babybeb.usersusedbookstore.api.dto.sign;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SessionCheckResponse {
    private Long memberId;
    private String nickname;
}
