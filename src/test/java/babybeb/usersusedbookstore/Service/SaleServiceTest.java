package babybeb.usersusedbookstore.Service;

import babybeb.usersusedbookstore.domain.Item;
import babybeb.usersusedbookstore.domain.Member;
import babybeb.usersusedbookstore.domain.Sale;
import babybeb.usersusedbookstore.repository.MemberRepository;
import babybeb.usersusedbookstore.repository.SaleRepository;
import babybeb.usersusedbookstore.service.MemberService;
import babybeb.usersusedbookstore.service.SaleService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SaleServiceTest {

    @Autowired SaleService saleService;
    @Autowired SaleRepository saleRepository;
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
//    @Autowired ItemRepository itemRepository;

    @Test
    public void 판매_등록하기() throws Exception{
        //given

        //when

        //then

    }

    @Test
    public void 판매_수정하기() throws Exception{
        //given

        //when

        //then
    }

    @Test
    public void 판매_삭제하기() throws Exception{
        //given

        //when

        //then
    }

    @Test
    public void 거래_평가하기() throws Exception{
        //given

        //when

        //then
    }



}
