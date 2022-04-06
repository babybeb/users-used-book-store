package babybeb.usersusedbookstore.domain;

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
    
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "item_id")
//    Item item;
}
