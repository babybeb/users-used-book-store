package babybeb.usersusedbookstore.domain;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
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
