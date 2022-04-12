package babybeb.usersusedbookstore.service;

import static org.junit.jupiter.api.Assertions.*;


import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class BookSearchServiceTest {
    
    @Autowired
    BookSearchService bookSearchService;
    
    @Test
    public void 책_이름으로_정보_검색하기() throws Exception {
        
        //given
        String searchTitle = "갈매기의 꿈";
        
        //when
        List<BookDto> bookInfos = bookSearchService.searchBookInfosByTitle(searchTitle);
        
        //then
        assertEquals("검색결과 첫번째 책의 제목은 searchTitle과 같다", searchTitle, bookInfos.get(0).getTitle());
    }

//    @Test
//    public void 책_지은이로_정보_검색하기() throws Exception {
//
//        //given
//        String searchAuthor = "김영한";
//
//        //when
//        List<BookDto> bookInfos = bookSearchService.searchBookInfosByAuthor(searchAuthor);
//
//        //then
//        assertEquals("검색결과 첫번째 책의 지은이는 searchWord과 같다", searchAuthor, bookInfos.get(0).getTitle());
//    }
}