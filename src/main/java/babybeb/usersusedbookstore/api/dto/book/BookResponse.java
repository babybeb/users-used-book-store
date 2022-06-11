package babybeb.usersusedbookstore.api.dto.book;

import babybeb.usersusedbookstore.domain.Book;
import babybeb.usersusedbookstore.domain.Category;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {
    
    private String isbn;
    
    private String title;
    
    private int bookPrice;
    
    private String publisher;
    
    private String author;
    
    private int page;
    
    private String kdc;
    
    @Enumerated(EnumType.STRING)
    private Category category;
    
    public static BookResponse toBookResponse(Book book) {
        return new BookResponse(book.getIsbn(), book.getTitle(), book.getBookPrice(),
                                book.getPublisher(), book.getAuthor(), book.getPage(),
                                book.getKdc(), book.getCategory());
    }
}
