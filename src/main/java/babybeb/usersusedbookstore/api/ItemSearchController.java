package babybeb.usersusedbookstore.api;

import babybeb.usersusedbookstore.api.dto.item.ItemDto;
import babybeb.usersusedbookstore.domain.Item;
import babybeb.usersusedbookstore.service.ItemSearchService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/item/search")
public class ItemSearchController {

    private final ItemSearchService itemSearchService;

    /**
     * 모든 Item을 List로 받아오는 API
     * @return Result
     */
    @GetMapping
    public Result findItems() {

        List<Item> findItems = itemSearchService.findItems();
        List<ItemDto> collect = findItems.stream()
            .map(i -> new ItemDto(i.getBook(), i.getItemPrice(), i.getItemCondition(), i.getCreateDate(), i.getDealStatus(), i.getHit()))
            .collect(Collectors.toList());

        return new Result(collect.size(), collect);
    }

//    @GetMapping("/word")
//    public Result findItemsByWord() {
//
//    }
//
//    @GetMapping("/category")
//    public Result findItemsByCategory() {
//
//    }
//
//    @GetMapping("/address")
//    public Result findItemsByAddress() {
//
//    }

    @Data
    @AllArgsConstructor
    static class Result<T> {

        private int count;
        private T data;
    }
}
