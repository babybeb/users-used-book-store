package babybeb.usersusedbookstore.domain;

import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Book {
    
    private String isbn;
    
    private String title;
    
    private int bookPrice;
    
    private String publisher;
    
    private String author;
    
    private int page;
    
    private String kdc;
    
    public Book(String isbn, String title, int bookPrice, String publisher, String author, int page,
                String kdc) {
        this.isbn = isbn;
        this.title = title;
        this.bookPrice = bookPrice;
        this.publisher = publisher;
        this.author = author;
        this.page = page;
        this.kdc = kdc;
    }
}