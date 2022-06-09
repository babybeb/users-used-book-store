package babybeb.usersusedbookstore.api;

import babybeb.usersusedbookstore.api.dto.member.*;
import babybeb.usersusedbookstore.domain.Member;
import babybeb.usersusedbookstore.domain.Sale;
import babybeb.usersusedbookstore.domain.dto.MemberDto;
import babybeb.usersusedbookstore.service.MailSendService;
import babybeb.usersusedbookstore.service.MemberService;
import babybeb.usersusedbookstore.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Random;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MailSendService mailSendService;
    private final MessageService messageService;

    /**
     * API 부분
     */
    //회원가입 API
    @PostMapping("/member/new")
    public SaveMemberResponse saveMember(
            @RequestBody
            @Valid SaveMemberRequest request) {
        Member member = new Member(request.getEmail(), request.getPassword(), request.getName(),
                request.getNickname(), request.getPhoneNumber());
        Long id = memberService.join(member);
        return new SaveMemberResponse(id);
    }

    //회원정보 수정 API
    @PutMapping("/member/{id}")
    public UpdateMemberResponse UpdateMember(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateMemberRequest request) {

        Member findMember = memberService.findOne(id);
        MemberDto updateInfo = new MemberDto(request.getEmail(), request.getPassword(),
                request.getName(), request.getNickname(), request.getPhoneNumber());
        memberService.updateMemberInfo(findMember.getId(), updateInfo);
        return new UpdateMemberResponse(findMember.getId(), findMember.getNickname());
    }

    //회원정보 조회 API
    @GetMapping("/member/{id}")
    public MemberInfoResponse MemberInfo(@PathVariable("id") Long id) {
        Member findMember = memberService.findOne(id);
        return new MemberInfoResponse(findMember.getEmail(), findMember.getPassword(),
                findMember.getName(), findMember.getNickname(), findMember.getPhoneNumber(),
                findMember.isAuth());
    }

    //회원탈퇴 API
    @DeleteMapping("/member/{id}")
    public MemberRemoveResponse MemberRemove(@PathVariable("id") Long id) {
        Member findMember = memberService.findOne(id);
        memberService.removeMember(findMember.getId());
        return new MemberRemoveResponse(findMember.getName());
    }

    //회원 판매 기록 조회 API (미완성)
    @GetMapping("member/{id}/sales")
    public MemberSalesResponse MemberSales(@PathVariable("id") Long memberId) {
        List<Sale> sales = memberService.findSales(memberId);
        return new MemberSalesResponse();
    }


    //회원 구매 기록 조회 API (미완성)
//    @GetMapping("member/{id}/purchases")
//    public MemberPurchasesResponse MemberPurchases(@PathVariable("id") Long id) {
//        return new MemberPurchasesResponse();
//    }

    //이메일 인증 API
    @PostMapping("member/email")
    public MemberEmailAuthResponse MemberEmail(
            @RequestBody @Valid MemberEmailAuthRequest request) {
        String authKey = mailSendService.sendAuthMail(request.getEmail());
        return new MemberEmailAuthResponse(authKey);
    }

    //이메일 인증 확인 API
    @GetMapping("/member/{email}/{authkey}")
    public MemberEmailAuthAcceptResponse EmailAuthAccept(
        @PathVariable("email") String email,
        @PathVariable("authkey") String authKey){

        Member findMember = memberService.findByEmail(email);
        if(findMember.getEmailKey() == authKey){
            return new MemberEmailAuthAcceptResponse(findMember.getName());
        } else {
            throw new IllegalStateException("인증이 안되었습니다.");
        }
    }

    //휴대폰 인증 API
    @PostMapping("member/phone")
    public MemberPhoneAuthResponse MemberPhone(
            @RequestBody @Valid MemberPhoneAuthRequest request) {
        Random random = new Random();
        String authKey = String.valueOf(random.nextInt(8888) + 1111);
        messageService.sendMessage(request.getPhoneNumber(), authKey);
        return new MemberPhoneAuthResponse(authKey);
    }
}
