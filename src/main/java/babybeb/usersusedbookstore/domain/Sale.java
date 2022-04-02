package babybeb.usersusedbookstore.domain;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
public class Sale {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    Member member;

//    @ManyToOne(fetch = LAZY)
//    Item item;

}
