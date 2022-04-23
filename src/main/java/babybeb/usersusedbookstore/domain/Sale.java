package babybeb.usersusedbookstore.domain;

import babybeb.usersusedbookstore.domain.dto.MemberDto;
import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
public class Sale{

    @Id @GeneratedValue
    @Column(name="Sale_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private Item item;

    protected Sale() {
    }

    private Sale(Member member, Item item) {
        this.member = member;
        this.item = item;
    }

    //==연관관계 메소드==//
    private void addSaleListToMember(Member member){
        member.getSaleList().add(this);
    }

    //==생성 메소드==//
    public static Sale createSale(Member member, Item item){
        Sale sale = new Sale(member, item);
        sale.addSaleListToMember(member);
        return sale;
    }

    //==비즈니스 로직==//
    /**
     * 구매자 평가
     */
    public void evaluate(int score, Member buyer){
        int resultRate;
        MemberDto updateDto = new MemberDto(buyer.getEmail(), buyer.getPassword() ,buyer.getName(),
                buyer.getNickname(), buyer.getPhoneNumber());
        resultRate = (int)(buyer.getRate() + score)/2;
        updateDto.setRate(resultRate);
        buyer.updateMemberRate(updateDto);
    }
}
