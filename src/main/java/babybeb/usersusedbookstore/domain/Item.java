package babybeb.usersusedbookstore.domain;

import java.awt.print.Book;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {
    
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "book_id")
    private Book book;
    
    private int price;
    
    private int hit;
    
    @Enumerated(EnumType.STRING)
    private ItemCondition itemCondition;
    
    private ImageInfo imageInfo;
    
    @Enumerated(EnumType.STRING)
    private dealStatus dealStatus;
    
    @OneToOne(fetch = FetchType.LAZY)
    private ChatRoom chatRoom;
}
