package babybeb.usersusedbookstore.service;

import babybeb.usersusedbookstore.domain.Sale;
import babybeb.usersusedbookstore.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SaleService {

    private final SaleRepository saleRepository;

    //상품 등록하기
//    @Transactional
//    public void saveSale(Sale sale){
//        saleRepository.save(sale);
//    }

    //상품 수정하기
//    @Transactional
//    public void updateSale(Long saleId, ItemDto updateDto){}

    //상품 삭제하기

    //상품 거래상태 변경하기

    //거래 평가하기
}
