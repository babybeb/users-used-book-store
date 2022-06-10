package babybeb.usersusedbookstore.domain;

import babybeb.usersusedbookstore.domain.dto.MemberDto;
import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Purchase {
    
    @Id
    @GeneratedValue
    @Column(name = "purchase_id")
    Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    Member member;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    Item item;
    
    public Purchase(Member member, Item item) {
        this.member = member;
        this.item = item;
    }
    
    /* 비즈니스 로직 */
    public void evaluate(Member seller, int score) {
        
        MemberDto updateMember = new MemberDto(seller.getEmail(), seller.getPassword(),
                                               seller.getName(), seller.getNickname(),
                                               seller.getPhoneNumber());
        int resultRate =
            ((seller.getSaleList().size() - 1) * seller.getRate() + score) /
                seller.getSaleList().size();
        updateMember.setRate(resultRate);
        seller.updateMemberRate(updateMember);
    }
}