package babybeb.usersusedbookstore.api;

import babybeb.usersusedbookstore.api.dto.sign.SessionCheckResponse;
import babybeb.usersusedbookstore.api.dto.sign.signInRequest;
import babybeb.usersusedbookstore.api.dto.sign.signInResponse;
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
    @PostMapping("/sign")
    public signInResponse signIn(
            HttpServletRequest requestSession,
            @Valid @RequestBody
            signInRequest request) {
        HttpSession session = requestSession.getSession();
        Long id = memberService.signIn(request.getEmail(), request.getPassword());
        Member findMember = memberService.findOne(id);
        session.setAttribute("USER", findMember.getEmail());
        session.setAttribute("SIGN", "OK");
        return new signInResponse(findMember.getNickname());
    }

    @DeleteMapping("/sign")
    public void signOut(
            HttpServletRequest requestSession) {
        HttpSession session = requestSession.getSession();
        session.removeAttribute("USER");
        session.removeAttribute("SIGN");
        session.invalidate();
    }

    @GetMapping("/session")
    public SessionCheckResponse isSignIn(
            HttpServletRequest requestSession) {
        HttpSession session = requestSession.getSession();
        String user = session.getAttribute("USER").toString();
        if (user.equals("") || user.equals(null)) {
            return new SessionCheckResponse();
        }
        else{
            Member member = memberService.findByEmail(user);
            return new SessionCheckResponse(member.getId(), member.getNickname());
        }
    }
}
