package babybeb.usersusedbookstore.service;

import babybeb.usersusedbookstore.domain.*;
import babybeb.usersusedbookstore.repository.MemberRepository;
import babybeb.usersusedbookstore.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final SaleRepository saleRepository;
    private final MemberRepository memberRepository;

    /**
     * 판매 등록하기
     */
    public Long addSale(Long memberId, BookDto dto, int itemPrice, ItemCondition condition, LocalDateTime createTime){
        //맴버 조회
        Member member = memberRepository.findOne(memberId);

        //판매상품 정보 입력
        Book book = new Book(dto.getIsbn(), dto.getTitle(), dto.getPrice(), dto.getPublisher(), dto.getAuthor(),
                dto.getPage(), dto.getKdc());

        //판매상품 생성
        Item item = new Item(book, itemPrice, condition, createTime);

        //판매 생성
        Sale sale = Sale.createSale(member, item);

        //판매정보 저장
        saleRepository.save(sale);
        return sale.getId();
    }

    /**
     * 판매 조회하기
     */
    public Sale findOne(Long saleId){
        return saleRepository.findOne(saleId);
    }

    /**
     * 판매 삭제하기
     */
    public void cancelSale(Long saleId){
        Sale sale = saleRepository.findOne(saleId);
        saleRepository.removeSale(sale);
    }

    /**
     * 거래 평가하기
     */
    public void rateBuyer(Long saleId, int score, Long BuyerId){
        Sale sale = saleRepository.findOne(saleId);
        Member buyer = memberRepository.findOne(BuyerId);
        sale.evaluate(score, buyer);
    }
}
