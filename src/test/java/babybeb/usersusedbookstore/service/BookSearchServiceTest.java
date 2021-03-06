//package babybeb.usersusedbookstore.service;
//
//import babybeb.usersusedbookstore.service.dto.BookDto;
//import java.util.List;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Transactional
//class BookSearchServiceTest {
//
//    @Autowired
//    BookSearchService bookSearchService;
//
//    @Test
//    @DisplayName("검색결과 첫 번째 책의 제목은 searchTitle을 포함한다")
//    public void 영어_책_이름으로_정보_검색하기() throws Exception {
//
//        //given
//        String searchTitle = "hello";
//
//        //when
//        List<BookDto> bookInfos = bookSearchService.searchBookInfosByTitle(searchTitle);
//
//        //then
//        Assertions.assertThat(bookInfos.get(0).getTitle())
//            .containsIgnoringCase(searchTitle);
//    }
//
//    @Test
//    @DisplayName("검색 결과는 10개 이하이며 검색한 책의 카테고리는 비어있지 않다")
//    public void 한글_책_이름으로_정보_검색하기() throws Exception {
//
//        //given
//        String searchTitle = "갈매기의 꿈";
//
//        //when
//        List<BookDto> bookInfos = bookSearchService.searchBookInfosByTitle(searchTitle);
//
//        //then
//        Assertions.assertThat(bookInfos.size()).isLessThan(11);
//        Assertions.assertThat(bookInfos.get(0).getCategory()).isNotNull();
//    }
//
//    @Test
//    @DisplayName("검색 결과 첫 번째 책의 지은이는 searchAuthor를 포함한다")
//    public void 책_지은이로_정보_검색하기() throws Exception {
//
//        //given
//        String searchAuthor = "김영한";
//
//        //when
//        List<BookDto> bookInfos = bookSearchService.searchBookInfosByAuthor(searchAuthor);
//
//        //then
//        Assertions.assertThat(bookInfos.get(0).getAuthor())
//            .containsIgnoringCase(searchAuthor);
//    }
//}