package babybeb.usersusedbookstore.repository;

import babybeb.usersusedbookstore.domain.BookWish;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookWishRepository {

    private final EntityManager em;

    public void save(BookWish bookWish){
        em.persist(bookWish);
    }

    public BookWish findOne(Long id){
        return em.find(BookWish.class, id);
    }

    public List<BookWish> findByMemberId(Long memberId) {
        return em.createQuery("select iw from ItemWish iw where iw.member.id = :memberId", BookWish.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

//    public List<BookWish> findByBookId(Long bookId){
//        return em.createQuery("select bw from BookWish bw where bw.book.id = :bookId", BookWish.class)
//                .setParameter("bookId", bookId)
//                .getResultList();
//    }

    public void cancelBookWish(BookWish bookWish){
        em.remove(bookWish);
    }
}
