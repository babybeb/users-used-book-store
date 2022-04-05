package babybeb.usersusedbookstore.domain;

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

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    protected Sale() {
    }

    public Sale(Member member, Item item) {
        this.member = member;
        this.item = item;
    }

    //==생성 메소드==//
    public static Sale createSale(Member member, Item item){
        Sale sale = new Sale(member, item);
        return sale;
    }

    //==비즈니스 로직==//
    /**
     * 주문취소
     */
//    public void cancel(){
//        if(item.getDealStatus() == DealStatus.SALE){
//
//        }
//        else{
//            throw new IllegalStateException("판매 중인 상품만 취소 가능합니다.");
//        }
//    }

    /**
     * 구매자 평가
     */
    public void evaluate(int score){
        int resultRate;
        MemberDto updateDto = new MemberDto(member.getEmail(), member.getPassword() ,member.getName(),
                member.getNickname(), member.getPhoneNumber());
        resultRate = (int)(member.getRate() + score)/2;
        updateDto.setRate(resultRate);
        member.updateMemberInfo(updateDto);
    }
}
