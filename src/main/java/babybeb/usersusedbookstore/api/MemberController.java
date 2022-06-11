package babybeb.usersusedbookstore.api;

import babybeb.usersusedbookstore.api.dto.book.BookResponse;
import babybeb.usersusedbookstore.api.dto.member.*;
import babybeb.usersusedbookstore.domain.*;
import babybeb.usersusedbookstore.domain.dto.MemberDto;
import babybeb.usersusedbookstore.service.MailSendService;
import babybeb.usersusedbookstore.service.MemberService;
import babybeb.usersusedbookstore.service.MessageService;
import babybeb.usersusedbookstore.service.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;
    private final MailSendService mailSendService;
    private final MessageService messageService;

    /**
     * API 부분
     */
    //회원가입 API
    @PostMapping("/new")
    public SaveMemberResponse saveMember(
            @RequestBody
            @Valid SaveMemberRequest request) {
        Member member = new Member(request.getEmail(), request.getPassword(), request.getName(),
                request.getNickname(), request.getPhoneNumber());
        Long id = memberService.join(member);
        return new SaveMemberResponse(id);
    }

    //회원정보 수정 API
    @PutMapping("/{id}")
    public UpdateMemberResponse UpdateMember(
            @PathVariable("id") Long memberId,
            @RequestBody @Valid UpdateMemberRequest request) {

        Member findMember = memberService.findOne(memberId);
        MemberDto updateInfo = new MemberDto(request.getEmail(), request.getPassword(),
                request.getName(), request.getNickname(), request.getPhoneNumber());
        memberService.updateMemberInfo(findMember.getId(), updateInfo);
        return new UpdateMemberResponse(findMember.getId(), findMember.getNickname());
    }

    //회원정보 조회 API
    @GetMapping("/{id}")
    public MemberInfoResponse MemberInfo(@PathVariable("id") Long memberId) {
        Member findMember = memberService.findOne(memberId);
        return new MemberInfoResponse(findMember.getEmail(), findMember.getPassword(),
                findMember.getName(), findMember.getNickname(), findMember.getPhoneNumber(),
                findMember.isAuth());
    }

    //회원탈퇴 API
    @DeleteMapping("/{id}")
    public MemberRemoveResponse MemberRemove(@PathVariable("id") Long memberId) {
        Member findMember = memberService.findOne(memberId);
        memberService.removeMember(findMember.getId());
        return new MemberRemoveResponse(findMember.getName());
    }

    //회원 판매 기록 조회 API
    @GetMapping("/{id}/sales")
    public ResponseEntity<List<MemberSalesResponse>> MemberSales(@PathVariable("id") Long memberId) {
        List<Sale> sales = memberService.findSales(memberId);
        List<MemberSalesResponse> result = new ArrayList<>();
        for (Sale sale : sales) {
            Item item = sale.getItem();
            result.add(MemberSalesResponse.toItemResponse(
                    new ResponseItem(item.getBook(), item.getId(), item.getItemPrice(), item.getHit(),
                            item.getItemCondition(), item.getDealArea(), item.getCreateDate(),
                            item.getDealStatus())));
        }
        return ResponseEntity.ok(result);
    }


    //회원 구매 기록 조회 API
    @GetMapping("/{id}/purchases")
    public ResponseEntity<List<MemberPurchasesResponse>> MemberPurchases(@PathVariable("id") Long memberId) {
        List<Purchase> purchases = memberService.findPurchases(memberId);
        List<MemberPurchasesResponse> result = new ArrayList<>();
        for (Purchase purchase : purchases) {
            Item item = purchase.getItem();
            result.add(MemberPurchasesResponse.toItemResponse(
                    new ResponseItem(item.getBook(), item.getId(), item.getItemPrice(), item.getHit(),
                            item.getItemCondition(), item.getDealArea(), item.getCreateDate(),
                            item.getDealStatus())));
        }
        return ResponseEntity.ok(result);
    }

    //이메일 인증 API
    @GetMapping("/email")
    public MemberEmailAuthResponse MemberEmail(@RequestParam String email) {
        String authKey = mailSendService.sendAuthMail(email);
        return new MemberEmailAuthResponse(authKey);
    }

    //휴대폰 인증 API
    @GetMapping("/phone")
    public MemberPhoneAuthResponse MemberPhone(@RequestParam String phoneNumber) {
        Random random = new Random();
        String authKey = String.valueOf(random.nextInt(8888) + 1111);
        messageService.sendMessage(phoneNumber, authKey);
        return new MemberPhoneAuthResponse(authKey);
    }
}
