package babybeb.usersusedbookstore.api.dto.member;

import lombok.Getter;

@Getter
public class SaveMemberRequest {
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String phoneNumber;
}
