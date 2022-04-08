package babybeb.usersusedbookstore.domain;

import babybeb.usersusedbookstore.domain.dto.MemberDto;
import java.awt.print.Book;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    @JoinColumn(name = "book_id")
    private Book book;
    
    private int price;
    
    private int hit;
    
    @Enumerated(EnumType.STRING)
    private ItemCondition itemCondition;
    
    private ImageInfo imageInfo;
    
    @Enumerated(EnumType.STRING)
    private DealStatus dealStatus;
    
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private List<ChatRoom> chatRooms = new ArrayList<>();
    
    public static Item createItem(Book book, int price, String itemCondition, ImageInfo imageInfo) {
        Item item = new Item();
        item
        
        return item;
    }
    
    public Item(Book book, int price, ItemCondition itemCondition, ImageInfo imageInfo) {
        this.book = book;
        this.price = price;
        this.itemCondition = itemCondition;
        this.imageInfo = imageInfo;
    }
}
