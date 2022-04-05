package babybeb.usersusedbookstore.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String phoneNumber;
    private int rate;

    public MemberDto(String email, String password, String name, String nickname, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
    }
}
