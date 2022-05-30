package babybeb.usersusedbookstore.api.dto.sign;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class signInRequest {
    private String email;
    private String password;
}
