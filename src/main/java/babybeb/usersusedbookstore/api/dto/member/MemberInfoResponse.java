package babybeb.usersusedbookstore.api.dto.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberInfoResponse {
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String phoneNumber;
    private boolean auth;
}
