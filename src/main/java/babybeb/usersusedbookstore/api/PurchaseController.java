package babybeb.usersusedbookstore.api;

import babybeb.usersusedbookstore.api.dto.purchase.RateSellerRequest;
import babybeb.usersusedbookstore.api.dto.purchase.RateSellerResponse;
import babybeb.usersusedbookstore.domain.Member;
import babybeb.usersusedbookstore.service.MemberService;
import babybeb.usersusedbookstore.service.PurchaseService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/purchase")
public class PurchaseController {
    
    private final PurchaseService purchaseService;
    private final MemberService memberService;
    
    /* 거래 평가 폼을 제공하는 GetMapping 컨트롤러 추가*/
    
    /**
     * 거래 평가 컨트롤러(거래 평가 부분만 업데이트한다.)
     * @param purchaseId
     * @param request
     * @return
     */
    @PostMapping("/{purchase_id}/rate")
    public RateSellerResponse RateSeller(
        @PathVariable("purchase_id") Long purchaseId,
        @RequestBody @Valid RateSellerRequest request) {
        
        Member seller = memberService.findOne(request.getSellerId());
        int rate = request.getRate();
        purchaseService.rateSeller(purchaseId, seller.getId(), rate);
        
        return new RateSellerResponse(seller.getNickname(), rate);
    }
}
