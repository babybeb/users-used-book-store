package babybeb.usersusedbookstore.service;

import babybeb.usersusedbookstore.domain.Item;
import babybeb.usersusedbookstore.domain.Member;
import babybeb.usersusedbookstore.domain.Sale;
import babybeb.usersusedbookstore.repository.MemberRepository;
import babybeb.usersusedbookstore.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final SaleRepository saleRepository;
    private final MemberRepository memberRepository;
//    private final BookRepository bookRepository;

    /**
     * 판매 등록하기
     */
//    public Long addSale(Long memberId, Long bookId){
//
//        //엔티티 조회
//        Member member = memberRepository.findOne(memberId);
//        Book book = bookRepository.findOne(bookId);
//
//        //판매상품 생성
//        Item item = Item.createItem(book, book.getPrice());
//
//        //판매 생성
//        Sale sale = Sale.createSale(member, item);
//
//        //판매정보 저장
//        saleRepository.save(sale);
//        return sale.getId();
//    }

    /**
     * 판매 수정하기
     */
//    public void updateSale(Long saleId, ItemDto updateDto){}

    /**
     * 판매 삭제하기
     */
    public void cancelSale(Long saleId){
        Sale sale = saleRepository.findOne(saleId);
        sale.cancel();
    }

    /**
     * 거래 평가하기
     */
    public void rateBuyer(Long saleId, int score){
        Sale sale = saleRepository.findOne(saleId);
        sale.evaluate(score);
    }
}
