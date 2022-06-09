package babybeb.usersusedbookstore.service;

import static org.junit.Assert.assertEquals;

import babybeb.usersusedbookstore.domain.Book;
import babybeb.usersusedbookstore.domain.Item;
import babybeb.usersusedbookstore.domain.ItemCondition;
import babybeb.usersusedbookstore.domain.dealarea.DealArea;
import babybeb.usersusedbookstore.domain.dealarea.First;
import babybeb.usersusedbookstore.domain.dealarea.Second;
import babybeb.usersusedbookstore.repository.ItemRepository;
import babybeb.usersusedbookstore.service.dto.BookDto;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Autowired
    BookSearchService bookSearchService;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("아이템이 정상적으로 저장되었는지 확인한다")
    public void 아이템_생성_테스트() throws Exception {
        //given
        Item item = createItem();

        //when
        Long saveId = itemService.saveItem(item);

        //then
        assertEquals(item, itemRepository.findOne(saveId));
    }

//    @Test
//    public void 아이템_수정_테스트() throws Exception {
//        //given
//        Item item = createItem();
//        Long saveId = itemService.saveItem(item);
//        Item updatedItem = itemService.findById(saveId);
//
//        //when
//        itemService.updateItem(updatedItem.getId(), updatedItem.getBook(), 10000, updatedItem.getItemCondition());
//
//        //then
//        assertEquals(10000, itemService.findById(updatedItem.getId()).getItemPrice());
//    }

    @Test
    public void 아이템_삭제_테스트() throws Exception { // 거래 상태가 예약중이거나 거래완료인 아이템은 삭제가 불가능하다
        //given

        //when

        //then
    }

    public Item createItem() throws IOException {
        BookDto bookDto = bookSearchService.searchBookInfosByTitle("뱁새").get(0);
        Book book = new Book(bookDto.getIsbn(), bookDto.getTitle(), bookDto.getPrice(),
                             bookDto.getPublisher(), bookDto.getAuthor(), bookDto.getPage(),
                             bookDto.getKdc(), bookDto.getCategory());

        Item item = new Item(book, 5000, ItemCondition.MINT, LocalDateTime.now(), new DealArea(
            First.충청남도, Second.천안시));
//        for (ImageFile imageFile : imageFiles) {
//            imageFile.registerItem(item);
//        }

//        String originalFileName = "original";
//        String storeFileName = "store";
//        List<ImageFile> imageFiles = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            imageFiles.add(new ImageFile(originalFileName, storeFileName));
//        }

        return item;
    }
}