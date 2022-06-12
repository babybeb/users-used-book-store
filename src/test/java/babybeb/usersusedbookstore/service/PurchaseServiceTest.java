// package babybeb.usersusedbookstore.service;

// import static org.junit.jupiter.api.Assertions.*;

// import babybeb.usersusedbookstore.domain.Member;
// import babybeb.usersusedbookstore.domain.Purchase;
// import babybeb.usersusedbookstore.repository.MemberRepository;
// import babybeb.usersusedbookstore.repository.PurchaseRepository;
// import javax.persistence.EntityManager;
// import org.junit.jupiter.api.Test;
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.junit4.SpringRunner;
// import org.springframework.transaction.annotation.Transactional;

// @RunWith(SpringRunner.class)
// @SpringBootTest
// @Transactional
// class PurchaseServiceTest {
    
//     @Autowired
//     MemberService memberService;
//     @Autowired
//     MemberRepository memberRepository;
//     @Autowired
//     PurchaseRepository purchaseRepository;
//     @Autowired
//     PurchaseService purchaseService;
    
//     // 아이템 추가 필요
    
//     @Autowired
//     EntityManager em;
    
//     @Test
//     public void 판매자_평가하기() throws Exception {
//         //given
//         Long member1 = memberService.join(new Member("1111@naver.com", "1111", "member1",
//                                                      "member1", "010-0000-1111", true));
//         Long member2 = memberService.join(new Member("2222@naver.com", "2222", "member2",
//                                                      "member2", "010-1111-2222",true));
        
// //        purchaseRepository.save(new Purchase(memberRepository.findOne(member1), itemRepository.findOne(1L)));
        
//         //when
//         purchaseService.rateSeller(80);
        
//         //then
// //        assertEquals("판매자 평가 점수는 80점이다.",80,memberService.findOne(member2).getScore());
//     }
// }
