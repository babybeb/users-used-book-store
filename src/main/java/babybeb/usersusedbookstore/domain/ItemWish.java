package babybeb.usersusedbookstore.domain;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
public class ItemWish {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
}
