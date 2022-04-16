package babybeb.usersusedbookstore.service;

import babybeb.usersusedbookstore.domain.*;
import babybeb.usersusedbookstore.repository.MemberRepository;
import babybeb.usersusedbookstore.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final SaleRepository saleRepository;

    /**
     * 판매 등록하기
     */
    public Long addSale(Member member, Book book, int price,
                        ItemCondition itemCondition, ImageInfo imageInfo){
        //판매상품 생성
        Item item = Item.createItem(book, price, itemCondition, imageInfo);

        //판매 생성
        Sale sale = Sale.createSale(member, item);

        //판매정보 저장
        saleRepository.save(sale);
        return sale.getId();
    }

    /**
     * 판매 수정하기
     */
//    public void updateSale(SaleDto updateDto){
//
//    }

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
