package babybeb.usersusedbookstore.api.dto.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateMemberResponse {
    private Long id;
    private String nickname;
}
