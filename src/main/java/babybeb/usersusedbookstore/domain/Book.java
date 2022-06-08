package babybeb.usersusedbookstore.domain;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    
    private String isbn;
    
    private String title;
    
    private int bookPrice;
    
    private String publisher;
    
    private String author;
    
    private int page;
    
    private String kdc;
    
    @Enumerated(EnumType.STRING)
    private Category category;
}