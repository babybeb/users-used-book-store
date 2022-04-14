package babybeb.usersusedbookstore.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BookDto {
    
    private String isbn;
    
    private String title;
    
    private int price;
    
    private String publisher;
    
    private String author;
    
    private int page;
    
    private String kdc;
    
    public BookDto(String isbn, String title, int price, String publisher, String author, int page,
                   String kdc) {
        this.isbn = isbn;
        this.title = title;
        this.price = price;
        this.publisher = publisher;
        this.author = author;
        this.page = page;
        this.kdc = kdc;
    }
}