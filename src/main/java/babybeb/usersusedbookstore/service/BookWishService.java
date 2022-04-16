package babybeb.usersusedbookstore.service;

import babybeb.usersusedbookstore.domain.Book;
import babybeb.usersusedbookstore.domain.BookWish;
import babybeb.usersusedbookstore.domain.Member;
import babybeb.usersusedbookstore.repository.BookWishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookWishService {

    private final BookWishRepository bookWishRepository;

    /**
     * 도서 찜하기
     */
    public Long addBookWish(Member member, Book book){
        BookWish bookWish = new BookWish(member, book);
        bookWishRepository.save(bookWish);
        return bookWish.getId();
    }

    /**
     * 도서 찜 삭제하기
     */
    public Long cancelBookWish(Long bookWishId){
        BookWish bookWish = bookWishRepository.findOne(bookWishId);
        if(!bookWish.equals(null)){
            bookWishRepository.cancelBookWish(bookWish);
            return bookWish.getId();
        }
        throw new IllegalStateException("해당 찜 항목이 없음");
    }

    /**
     * 도서 찜 목록 조회하기
     */
    public List<BookWish> findBookWishList(Member member){
        return bookWishRepository.findByMemberId(member.getId());
    }

}
