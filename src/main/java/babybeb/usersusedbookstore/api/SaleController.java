package babybeb.usersusedbookstore.api;

import babybeb.usersusedbookstore.api.dto.sale.*;
import babybeb.usersusedbookstore.domain.Member;
import babybeb.usersusedbookstore.domain.Sale;
import babybeb.usersusedbookstore.service.ItemService;
import babybeb.usersusedbookstore.service.MemberService;
import babybeb.usersusedbookstore.service.SaleService;
import babybeb.usersusedbookstore.service.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sale")
public class SaleController {

    private final MemberService memberService;
    private final SaleService saleService;

    /**
     * API 부분
     */
    //거래 평가하기 API
    @PutMapping("{saleId}/rateBuyer")
    public RateBuyerResponse RateBuyer(
            @PathVariable("saleId") Long saleId,
            @RequestBody @Valid RateBuyerRequest request){
        saleService.rateBuyer(saleId, request.getRate(), request.getBuyerId());
        Member buyer = memberService.findOne(request.getBuyerId());
        return new RateBuyerResponse(buyer.getNickname(), request.getRate());
    }
}
