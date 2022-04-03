package babybeb.usersusedbookstore.service;

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
    
    /**
     * 판매자 평가하기
     */
    @Transactional
    public void rateSeller(int score) {
    
    }
}
