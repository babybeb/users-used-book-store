package babybeb.usersusedbookstore.service;

import babybeb.usersusedbookstore.domain.*;
import babybeb.usersusedbookstore.repository.SaleRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SaleServiceTest {

    @Autowired SaleService saleService;
    @Autowired SaleRepository saleRepository;
    @Autowired MemberService memberService;
    @Autowired BookSearchService bookSearchService;

//    @Test
//    public void 판매_등록하기() throws Exception{
//        //given
//        Member member = createMember();
//
//        String searchTitle = "갈매기의 꿈";
//        List<BookDto> bookInfos = bookSearchService.searchBookInfosByTitle(searchTitle);
//        BookDto selectBook = bookInfos.get(0);
//        ImageInfo image = new ImageInfo();
//
//        //when
//        Long saleId = saleService.addSale(member.getId(), selectBook, 30000, ItemCondition.MINT, image);
//        Sale sale = saleRepository.findByMemberId(member.getId()).get(0);
//
//        //then
//        assertThat(saleId).isEqualTo(sale.getId());
//        assertEquals(DealStatus.SALE, sale.getItem().getDealStatus(), "상품의 거래상태가 SALE 이어야 한다.");
//    }

    @Test
    public void 판매_수정하기() throws Exception{
        //given

        //when

        //then
    }

//    @Test
//    public void 판매_삭제하기() throws Exception{
//        //given
//        Member member = createMember();
//
//        String searchTitle = "갈매기의 꿈";
//        List<BookDto> bookInfos = bookSearchService.searchBookInfosByTitle(searchTitle);
//        BookDto selectBook = bookInfos.get(0);
//        ImageInfo image = new ImageInfo();
//
//        //when
//        Long saleId = saleService.addSale(member.getId(), selectBook, 30000, ItemCondition.MINT, image);
//        saleService.cancelSale(saleId);
//        saleRepository.findOne(saleId); //예외가 발생해야함
//
//        //then
//        Assertions.fail("삭제한 sale을 조회했을때 예외가 발생해야함");
//    }

//    @Test
//    public void 거래_평가하기() throws Exception{
//        //given
//        Member member = createMember();
//        Member buyer = new Member("test@naver.com", "1234" ,"김민수",
//                "test", "010-2222-2222", true);
//        memberService.join(buyer);
//
//        String searchTitle = "갈매기의 꿈";
//        List<BookDto> bookInfos = bookSearchService.searchBookInfosByTitle(searchTitle);
//        BookDto selectBook = bookInfos.get(0);
//        ImageInfo image = new ImageInfo();
//
//        //when
//        Long saleId = saleService.addSale(member.getId(), selectBook, 30000, ItemCondition.MINT, image);
//        saleService.rateBuyer(saleId, 100, buyer.getId()); //buyer의 평점은 (100+0)/2를 해서 50이 될것이다.
//
//        //then
//        assertEquals(buyer.getRate(), 50, "구매자의 평점이 (100+0)/2가 된다.");
//    }


    private Member createMember(){
        Member member = new Member("ggeggrgg@naver.com", "1234" ,"김민수",
                "GgEgg", "010-1111-1111", true);
        memberService.join(member);
        return member;
    }

}
