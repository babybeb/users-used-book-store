package babybeb.usersusedbookstore.service.dto;

import babybeb.usersusedbookstore.domain.Category;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookDto {
    
    private String isbn;
    
    private String title;
    
    private int price;
    
    private String publisher;
    
    private String author;
    
    private int page;
    
    private String kdc;
    
    @Enumerated(EnumType.STRING)
    private Category category;
}