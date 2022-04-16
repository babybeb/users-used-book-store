package babybeb.usersusedbookstore.domain;

import babybeb.usersusedbookstore.service.BookDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor
public class BookWish {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Embedded
    private Book book;

    public BookWish(Member member, BookDto dto) {
        this.member = member;
        this.book = new Book(dto.getIsbn(), dto.getTitle(), dto.getPrice(),
                dto.getPublisher(), dto.getAuthor(), dto.getPage(), dto.getKdc());
    }
}
