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
    
    private int price;
    
    private String publisher;
    
    private String author;
    
    private int page;
    
    private String kdc;
}