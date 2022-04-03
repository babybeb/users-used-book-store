package babybeb.usersusedbookstore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
    @Column(name = "member_id")
    Member member;
    
    @OneToOne(fetch = FetchType.LAZY)
    @Column(name = "item_id")
    Item item;
}
