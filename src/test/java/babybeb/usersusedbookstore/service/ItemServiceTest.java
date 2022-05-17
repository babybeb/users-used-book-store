package babybeb.usersusedbookstore.service;

import static org.junit.Assert.assertEquals;

import babybeb.usersusedbookstore.domain.Book;
import babybeb.usersusedbookstore.domain.ImageFile;
import babybeb.usersusedbookstore.domain.Item;
import babybeb.usersusedbookstore.domain.ItemCondition;
import babybeb.usersusedbookstore.repository.ItemRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
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
    public void 아이템_생성_테스트() throws Exception {
        //given
        Item item = createItem();

        //when
        Long saveId = itemService.saveItem(item);

        //then
        assertEquals(item, itemRepository.findOne(saveId));
    }

    @Test
    public void 아이템_수정_테스트() throws Exception {
        //given
        Item item = createItem();
    
        //when
        Long saveId = itemService.saveItem(item);
    
        //then
        assertEquals(item, itemRepository.findOne(saveId));
    }

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
                             bookDto.getKdc());
        
        String originalFileName = "original";
        String storeFileName = "store";
        List<ImageFile> imageFiles = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            imageFiles.add(new ImageFile(originalFileName, storeFileName));
        }
        
        Item item = new Item(book, 5000, ItemCondition.MINT, imageFiles);
        for (ImageFile imageFile : imageFiles) {
            imageFile.registerItem(item);
        }
        
        return item;
    }
}