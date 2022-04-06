package babybeb.usersusedbookstore.domain;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
public class BookWish {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

//    @OneToOne(fetch = LAZY)
//    @JoinColumn(name = "book_id")
//    private Book book;
}
