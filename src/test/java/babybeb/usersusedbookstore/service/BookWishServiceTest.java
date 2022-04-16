package babybeb.usersusedbookstore.service;

import babybeb.usersusedbookstore.domain.Book;
import babybeb.usersusedbookstore.domain.BookWish;
import babybeb.usersusedbookstore.domain.Member;
import babybeb.usersusedbookstore.repository.BookWishRepository;
import babybeb.usersusedbookstore.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class BookWishServiceTest {

    @Autowired BookWishRepository bookWishRepository;
    @Autowired BookWishService bookWishService;
    @Autowired BookSearchService bookSearchService;
    @Autowired MemberService memberService;

    @Test
    public void 도서_찜_추가하기() throws Exception{
        //given
        Member member = new Member("ggeggrgg@naver.com", "1234" ,"김민수",
                "GgEgg", "010-5407-9254", true);
        memberService.join(member);

        String searchTitle = "갈매기의 꿈";
        List<BookDto> bookInfos = bookSearchService.searchBookInfosByTitle(searchTitle);
        Book selectBook = new Book(bookInfos.get(0));

        //when
        Long bookWishId = bookWishService.addBookWish(member, selectBook);
        BookWish findBookWish = bookWishRepository.findByIsbnWithMember(member.getId(), selectBook.getIsbn());

        //then
        assertThat(bookWishId).isEqualTo(findBookWish.getId());
    }

    @Test
    public void 도서_찜_조회하기() throws Exception{
        //given
        Member member = new Member("ggeggrgg@naver.com", "1234" ,"김민수",
                "GgEgg", "010-5407-9254", true);
        memberService.join(member);

        String searchTitle = "갈매기의 꿈";
        List<BookDto> bookInfos = bookSearchService.searchBookInfosByTitle(searchTitle);
        Book selectBook = new Book(bookInfos.get(0));
        bookWishService.addBookWish(member, selectBook);

        //when
        List<BookWish> bookWishList = bookWishService.findBookWishList(member);

        //then
        assertThat(bookWishList.get(0).getBook().getTitle()).isEqualTo("갈매기의 꿈");
    }

    @Test
    public void 도서_찜_삭제하기() throws Exception{
        //given
        Member member = new Member("ggeggrgg@naver.com", "1234" ,"김민수",
                "GgEgg", "010-5407-9254", true);
        memberService.join(member);

        String searchTitle = "갈매기의 꿈";
        List<BookDto> bookInfos = bookSearchService.searchBookInfosByTitle(searchTitle);
        Book selectBook = new Book(bookInfos.get(0));
        bookWishService.addBookWish(member, selectBook);

        //when
        List<BookWish> bookWishList = bookWishService.findBookWishList(member);
        Long cancelBookWishId = bookWishService.cancelBookWish(bookWishList.get(0).getId());

        //then
        assertThat(selectBook.getId()).isEqualTo(cancelBookWishId);
    }
}