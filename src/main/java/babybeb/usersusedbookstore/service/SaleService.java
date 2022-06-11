package babybeb.usersusedbookstore.service;

import babybeb.usersusedbookstore.domain.*;
import babybeb.usersusedbookstore.domain.dealarea.DealArea;
import babybeb.usersusedbookstore.repository.MemberRepository;
import babybeb.usersusedbookstore.repository.SaleRepository;
import babybeb.usersusedbookstore.service.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SaleService {

    private final SaleRepository saleRepository;
    private final MemberRepository memberRepository;

    /**
     * 판매 등록하기
     */
    @Transactional
    public Long addSale(Long memberId, Item item){
        //맴버 조회
        Member member = memberRepository.findOne(memberId);

        //판매 생성
        Sale sale = Sale.createSale(member, item);

        saleRepository.save(sale);
        return sale.getId();
    }

    /**
     * 판매 조회하기
     */
    public Sale findOne(Long saleId){
        return saleRepository.findOne(saleId);
    }

    //아이템 아이디로 조회하기
    public Sale findByItem(Long itemId){
        return saleRepository.findByItemId(itemId);
    }

    /**
     * 판매 삭제하기
     */
    @Transactional
    public void cancelSale(Long itemId){
        Sale sale = saleRepository.findByItemId(itemId);
        saleRepository.removeSale(sale);
    }

    /**
     * 거래 평가하기
     */
    @Transactional
    public void rateBuyer(Long saleId, int score, Long BuyerId){
        Sale sale = saleRepository.findOne(saleId);
        Member buyer = memberRepository.findOne(BuyerId);
        sale.evaluate(score, buyer);
    }
}
