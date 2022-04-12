package babybeb.usersusedbookstore.domain;

import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
    
    @Embedded
    private Book book;
    
    private int price;
    
    private int hit;
    
    @Enumerated(EnumType.STRING)
    private ItemCondition itemCondition;
    
    @Embedded
    private ImageInfo imageInfo;
    
    @Enumerated(EnumType.STRING)
    private DealStatus dealStatus;

//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "chat_room_id")
//    private List<ChatRoom> chatRooms = new ArrayList<>();
    
    /**
     * 생성 메서드
     */
    public static Item createItem(Book book, int price, ItemCondition itemCondition,
                                  ImageInfo imageInfo) {
        Item item = new Item(book, price, itemCondition, imageInfo);
        return item;
    }
    
    /**
     * 생성자
     */
    public Item(Book book, int price, ItemCondition itemCondition, ImageInfo imageInfo) {
        this.book = book;
        this.price = price;
        this.itemCondition = itemCondition;
        this.imageInfo = imageInfo;
    }
}
