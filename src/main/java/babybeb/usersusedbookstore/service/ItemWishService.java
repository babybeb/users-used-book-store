package babybeb.usersusedbookstore.service;

import babybeb.usersusedbookstore.domain.BookWish;
import babybeb.usersusedbookstore.domain.ItemWish;
import babybeb.usersusedbookstore.domain.Member;
import babybeb.usersusedbookstore.repository.BookWishRepository;
import babybeb.usersusedbookstore.repository.ItemWishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemWishService {

    private final ItemWishRepository itemWishRepository;

    /**
     * 상품 찜하기
     */
    @Transactional
    public Long addItemWish(ItemWish itemWish){
        itemWishRepository.save(itemWish);
        return itemWish.getId();
    }

    /**
     * 상품 찜 삭제하기
     */
    @Transactional
    public void cancelItemWish(Long itemWishId){
        ItemWish itemWish = itemWishRepository.findOne(itemWishId);
        if(!itemWish.equals(null)){
            itemWishRepository.cancelItemWish(itemWish);
            return;
        }
        throw new IllegalStateException("해당 찜 항목이 없음");
    }

    /**
     * 상품 찜 목록 조회하기
     */
    //회원 별 찜 목록 조회
    public List<ItemWish> findItemWishListByMember(Long memberId){
        return itemWishRepository.findByMemberId(memberId);
    }

    //아이템 별 찜 목록 조회
    public List<ItemWish> findItemWishListByItem(Long itemId){
        return itemWishRepository.findByItemId(itemId);
    }
}
