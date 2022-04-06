package babybeb.usersusedbookstore.service;

import babybeb.usersusedbookstore.repository.BookWishRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class BookWishServiceTest {

    @Autowired BookWishRepository bookWishRepository;
    @Autowired BookWishService bookWishService;

    @Test
    public void 도서_찜_추가하기() throws Exception{
        //given

        //when

        //then
    }

    @Test
    public void 도서_찜_조회하기() throws Exception{
        //given

        //when

        //then
    }

    @Test
    public void 도서_찜_삭제하기() throws Exception{
        //given

        //when

        //then
    }


}