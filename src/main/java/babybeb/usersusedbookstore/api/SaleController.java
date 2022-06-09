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
public class SaleController {

    private final MemberService memberService;
    private final SaleService saleService;

    private final ItemService itemService;

    /**
     * API 부분
     */
    //판매 등록 API
    @PostMapping("sale/{memberId}/new")
    public SaveSaleResponse saveSale(
            @PathVariable("memberId") Long memberId,
            @RequestBody @Valid SaveSaleRequest request){
        //판매 등록 시간 저장 (yyyy-MM-dd T HH:mm:ss)
        LocalDateTime date = LocalDateTime.now();

        //책정보(ISBN, 제목, 가격, 출판사, 저자, 페이지, Kdc, 카테고리)
        BookDto book = new BookDto(request.getIsbn(), request.getTitle(), request.getBookPrice(),
                request.getPublisher(), request.getAuthor(), request.getPage(), request.getKdc(),
                request.getCategory());

        //세일 생성(회원아이디, 책정보, 판매가격, 책 상태, 등록날짜, 거래지역)
        Long itemId = saleService.addSale(memberId, book, request.getPrice(), request.getItemCondition(), date,
                request.getDealArea());

        //이미지 등록을 위한 itemId 값 반환
        return new SaveSaleResponse(itemId);
    }

    //판매 삭제 API
    @DeleteMapping("sale/{saleId}")
    public DeleteSaleResponse delSale(
            @PathVariable("saleId") Long saleId){
        Sale findSale = saleService.findOne(saleId);
        String bookTitle = findSale.getItem().getBook().getTitle();
        saleService.cancelSale(saleId);
        return new DeleteSaleResponse(bookTitle);
    }

    //판매 거래 상태 변경 API
    @PutMapping("sale/{saleId}/dealStatus")
    public ChangeStatusResponse ChangeSaleStatus(
            @PathVariable("saleId") Long saleId,
            @RequestBody @Valid ChangeStatusRequest request){
        Sale findSale = saleService.findOne(saleId);
        findSale.getItem().changeDealStatus(request.getDealStatus());
        return new ChangeStatusResponse(findSale.getItem().getDealStatus());
    }

    //거래 평가하기 API
    @PutMapping("sale/{saleId}/rateBuyer")
    public RateBuyerResponse RateBuyer(
            @PathVariable("saleId") Long saleId,
            @RequestBody @Valid RateBuyerRequest request){
        saleService.rateBuyer(saleId, request.getRate(), request.getBuyerId());
        Member buyer = memberService.findOne(request.getBuyerId());
        return new RateBuyerResponse(buyer.getNickname(), request.getRate());
    }
}
