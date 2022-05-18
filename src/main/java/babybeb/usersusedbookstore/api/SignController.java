package babybeb.usersusedbookstore.api;

import babybeb.usersusedbookstore.domain.Member;
import babybeb.usersusedbookstore.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class SignController {

    private final MemberService memberService;

    /**
     * API 부분
     */
    @PostMapping("/signIn")
    public signInResponse signIn(
            @RequestBody @Valid
            HttpServletRequest requestSession, signInRequest request){
        HttpSession session = requestSession.getSession();
        Long id = memberService.signIn(request.getEmail(), request.getPassword());
        Member findMember = memberService.findOne(id);
        session.setAttribute("USER", findMember.getEmail());
        session.setAttribute("SIGN", "OK");
        return new signInResponse(findMember.getNickname());
    }

    @DeleteMapping("/signOut")
    public void signOut(
            @RequestBody @Valid
            HttpServletRequest requestSession){
        HttpSession session = requestSession.getSession();
        session.removeAttribute("USER");
        session.removeAttribute("SIGN");
        session.invalidate();
    }

    /**
     * Data 부분
     */
    @Data
    static class signInRequest{
        private String email;
        private String password;
    }

    @Data
    @AllArgsConstructor
    static class signInResponse{
        private String nickName;
    }
}
