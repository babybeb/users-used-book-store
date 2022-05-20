package babybeb.usersusedbookstore.api;

import babybeb.usersusedbookstore.domain.DealStatus;
import babybeb.usersusedbookstore.domain.Item;
import babybeb.usersusedbookstore.domain.Member;
import babybeb.usersusedbookstore.domain.Sale;
import babybeb.usersusedbookstore.service.BookDto;
import babybeb.usersusedbookstore.service.ItemService;
import babybeb.usersusedbookstore.service.MemberService;
import babybeb.usersusedbookstore.service.SaleService;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class SaleController {

    private final MemberService memberService;
    private final SaleService saleService;

    private final ItemService itemService;

    /**
     * API 부분
     */
    //상품 등록 API
//    @PostMapping("sale/{memberId}/new")
//    public saveSaleResponse saveSale(
//            @PathVariable("memberId") Long id,
//            @RequestBody @Valid SaveSaleRequest request){
//        Sale sale = saleService.addSale(id, );
//    }

    //상품 삭제 API
    @DeleteMapping("sale/{saleId}")
    public DeleteSaleResponse delSale(
            @PathVariable("saleId") Long saleId){
        Sale findSale = saleService.findOne(saleId);
        String bookTitle = findSale.getItem().getBook().getTitle();
        saleService.cancelSale(saleId);
        return new DeleteSaleResponse(bookTitle);
    }

    //상품 거래 상태 변경 API
    @PutMapping("sale/{saleId}/dealStatus")
    public ChangeStatusResponse ChangeSaleStatus(
            @Valid ChangeStatusRequest request,
            @PathVariable("saleId") Long saleId){
        Sale findSale = saleService.findOne(saleId);
        findSale.getItem().changeDealStatus(request.dealStatus);
        return new ChangeStatusResponse(findSale.getItem().getDealStatus());
    }

    //거래 평가하기 API
    @PutMapping("sale/{saleId}/rateBuyer")
    public RateBuyerResponse RateBuyer(
            @Valid RateBuyerRequest request,
            @PathVariable("saleId") Long saleId){
        saleService.rateBuyer(saleId, request.rate, request.buyerId);
        Member buyer = memberService.findOne(request.buyerId);
        return new RateBuyerResponse(buyer.getNickname(), request.rate);
    }

    /**
     * DATA 부분
     */
    @Data
    static class SaveSaleRequest{
        //받아야 하는 값
    }

    @Data
    @AllArgsConstructor
    static class SaveSaleResponse{
        //반환값
    }

    @Data
    @AllArgsConstructor
    static class DeleteSaleResponse{
        //반환값
        private String bookTitle;
    }

    @Data
    static class ChangeStatusRequest{
        //받아야 하는 값
        private String dealStatus;
    }

    @Data
    @AllArgsConstructor
    static class ChangeStatusResponse{
        //반환값
        private DealStatus dealStatus;
    }

    @Data
    static class RateBuyerRequest{
        //받아야 하는 값
        private Long buyerId;
        private int rate;
    }

    @Data
    @AllArgsConstructor
    static class RateBuyerResponse{
        private String buyerNickname;
        private int rate;
    }
}
