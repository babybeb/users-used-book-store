package babybeb.usersusedbookstore.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
    
    @Embedded
    private Book book;
    
    private int itemPrice;
    
    private int hit;
    
    @Enumerated(EnumType.STRING)
    private ItemCondition itemCondition;
    
    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
    private List<ImageFile> imageFiles = new ArrayList<>();
    
    @Enumerated(EnumType.STRING)
    private DealStatus dealStatus;
    
    @Embedded
    private DealArea dealArea;

//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "chat_room_id")
//    private List<ChatRoom> chatRooms = new ArrayList<>();
    
    public Item(Book book, int itemPrice, ItemCondition itemCondition,
                List<ImageFile> imageFiles) {
        this.book = book;
        this.itemPrice = itemPrice;
        this.itemCondition = itemCondition;
        this.imageFiles = imageFiles;
    }
    
    /**
     * 거래 상태 변경
     */
    public void changeDealStatus(String status) {
        if (status.equals("SALE")) {
            this.dealStatus = DealStatus.SALE;
        } else if (status.equals("RESERVED")) {
            this.dealStatus = DealStatus.RESERVED;
        } else {
            this.dealStatus = DealStatus.COMP;
        }
    }
}
