package babybeb.usersusedbookstore.service;

import babybeb.usersusedbookstore.domain.Book;
import babybeb.usersusedbookstore.domain.Item;
import babybeb.usersusedbookstore.domain.ItemCondition;
import babybeb.usersusedbookstore.domain.Member;
import babybeb.usersusedbookstore.domain.Purchase;
import babybeb.usersusedbookstore.domain.Sale;
import babybeb.usersusedbookstore.domain.dealarea.DealArea;
import babybeb.usersusedbookstore.repository.ItemRepository;
import babybeb.usersusedbookstore.repository.MemberRepository;
import babybeb.usersusedbookstore.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional()
@RequiredArgsConstructor
public class PurchaseService {
    
    private final PurchaseRepository purchaseRepository;
    private final MemberRepository memberRepository;
    private final SaleService saleService;
    
    /**
     * 판매자 평가하기
     */
    @Transactional
    public void rateSeller(Long purchaseId, Long sellerId, int score) {
        Purchase purchase = purchaseRepository.findOne(purchaseId);
        Member seller = saleService.findOne(sellerId).getMember();
        purchase.evaluate(seller, score);
    }
    
    /**
     * 구매 등록하기
     */
    @Transactional
    public Long savePurchase(Long memberId, Item item) {
        Member findMember = memberRepository.findOne(memberId);
        Purchase purchase = new Purchase(findMember, item);
        Long id = purchaseRepository.save(purchase);
        return id;
    }
    
    public Purchase findById(Long purchaseId) {
        return purchaseRepository.findOne(purchaseId);
    }
}