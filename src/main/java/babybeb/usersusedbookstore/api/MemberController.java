package babybeb.usersusedbookstore.api;

import babybeb.usersusedbookstore.domain.Member;
import babybeb.usersusedbookstore.domain.dto.MemberDto;
import babybeb.usersusedbookstore.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * API 부분
     */
    //회원가입 API
    @PostMapping("/member/new")
    public SaveMemberResponse saveMember(
            @RequestBody
            @Valid SaveMemberRequest request){
        Member member = new Member(request.email, request.password, request.name,
                                    request.nickname, request.phoneNumber, false);
        Long id = memberService.join(member);
        return new SaveMemberResponse(id);
    }

    //회원정보 수정 API
    @PutMapping("/member/{id}")
    public UpdateMemberResponse UpdateMember(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateMemberRequest request){

        Member findMember = memberService.findOne(id);
        MemberDto updateInfo = new MemberDto(request.getEmail(), request.getPassword(),
                request.getName(), request.getNickname(), request.getPhoneNumber());
        memberService.updateMemberInfo(findMember.getId(), updateInfo);
        return new UpdateMemberResponse(findMember.getId(), findMember.getNickname());
    }

    //회원정보 조회 API
    @GetMapping("/member/{id}")
    public MemberInfoResponse MemberInfo(@PathVariable("id") Long id){
        Member findMember = memberService.findOne(id);
        return new MemberInfoResponse(findMember.getEmail(), findMember.getPassword(),
                findMember.getName(), findMember.getNickname(), findMember.getPhoneNumber(),
                findMember.isAuth());
    }

    //회원탈퇴 API
    @DeleteMapping("/member/{id}")
    public MemberRemoveResponse MemberRemove(@PathVariable("id") Long id){
        Member findMember = memberService.findOne(id);
        memberService.removeMember(findMember.getId());
        return new MemberRemoveResponse(findMember.getName());
    }

    //회원 판매 기록 조회 API (미완성)
    @GetMapping("member/{id}/sales")
    public MemberSalesResponse MemberSales(@PathVariable("id") Long id){
        return new MemberSalesResponse();
    }


    //회원 구매 기록 조회 API (미완성)
    @GetMapping("member/{id}/purchases")
    public MemberPurchasesResponse MemberPurchases(@PathVariable("id") Long id){
        return new MemberPurchasesResponse();
    }

    /**
     * Data 부분
     */
    @Data
    static class SaveMemberRequest{
        private String email;
        private String password;
        private String name;
        private String nickname;
        private String phoneNumber;
    }

    @Data
    @AllArgsConstructor
    static class SaveMemberResponse{
        private Long id;
    }

    @Data
    static class UpdateMemberRequest{
        private String email;
        private String password;
        private String name;
        private String nickname;
        private String phoneNumber;
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse{
        private Long id;
        private String nickName;
    }

    @Data
    @AllArgsConstructor
    static class MemberInfoResponse{
        private String email;
        private String password;
        private String name;
        private String nickname;
        private String phoneNumber;
        private boolean auth;
    }

    @Data
    @AllArgsConstructor
    static class MemberRemoveResponse{
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class MemberSalesResponse{

    }

    @Data
    @AllArgsConstructor
    static class MemberPurchasesResponse{

    }
}
