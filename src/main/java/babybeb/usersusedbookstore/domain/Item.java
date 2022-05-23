package babybeb.usersusedbookstore.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
    
    private LocalDateTime createDate;

//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "chat_room_id")
//    private List<ChatRoom> chatRooms = new ArrayList<>();
    
    public Item(Book book, int itemPrice, ItemCondition itemCondition, LocalDateTime createDate) {
        this.book = book;
        this.itemPrice = itemPrice;
        this.itemCondition = itemCondition;
        this.dealStatus = DealStatus.SALE;
        this.createDate = createDate;
    }
    
    //== 비즈니스 로직 ==//
    
    /**
     * 이미지 파일 추가
     */
    public void addImageFiles(List<ImageFile> imageFiles) {
        this.imageFiles = imageFiles;
    }
    
    /**
     * 책 변경
     */
    public void changeBook(Book book) {
        this.book = book;
    }
    
    /**
     * 가격 변경
     */
    public void changeItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }
    
    /**
     * 아이템 상태 변경
     */
    public void changeItemCondition(ItemCondition itemCondition) {
        this.itemCondition = itemCondition;
    }
    
    /**
     * 거래 지역 변경
     */
    public void changeDealArea(DealArea dealArea) {
        this.dealArea = dealArea;
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
    
    /**
     * 조회수 증가
     */
    public void addHit() {
        ++this.hit;
    }
}
