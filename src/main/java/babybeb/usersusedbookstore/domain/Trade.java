package babybeb.usersusedbookstore.domain;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
public abstract class Trade {

    @Id @GeneratedValue
    @Column(name="trade_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    //거래 평가하기
    public void evaluate(int score){
        int resultRate;
        MemberDto updateDto = new MemberDto(member.getEmail(), member.getPassword() ,member.getName(),
                                            member.getNickname(), member.getPhoneNumber());
        resultRate = (int)(member.getRate() + score)/2;
        updateDto.setRate(resultRate);
        member.updateMemberInfo(updateDto);
    }
}
