package babybeb.usersusedbookstore.api;

import babybeb.usersusedbookstore.api.dto.item.ItemDto;
import babybeb.usersusedbookstore.domain.Item;
import babybeb.usersusedbookstore.service.ItemSearchService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @ApiOperation(value = "모든 Item을 List로 반환")
    @GetMapping
    public Result findItems() {

        List<Item> findItems = itemSearchService.findItems();
        List<ItemDto> collect = findItems.stream()
            .map(i -> new ItemDto(i.getBook(), i.getItemPrice(), i.getItemCondition(), i.getCreateDate(), i.getDealStatus(), i.getDealArea(), i.getHit()))
            .collect(Collectors.toList());

        return new Result(collect.size(), collect);
    }
    
    @ApiOperation(value = "title이 책의 이름에 포함된 Item List 반환", notes = "title이 책의 이름에 포함된 Item List를 반환합니다. title을 String으로 받습니다.")
    @GetMapping("/title")
    public List<ItemDto> findItemsByTitle(@RequestParam("title") @NotEmpty String title) {

        List<Item> findItems = itemSearchService.findAllByTitleContains(title);
        List<ItemDto> result = findItems.stream()
            .map(i -> new ItemDto(i.getBook(), i.getItemPrice(), i.getItemCondition(), i.getCreateDate(), i.getDealStatus(), i.getDealArea(), i.getHit()))
            .collect(Collectors.toList());

        return result;
    }

    @ApiOperation(value = "카테고리별 Item List 반환", notes = "카테고리별 Item List를 반환합니다. 카테고리의 이름을 String으로 받습니다.")
    @GetMapping("/category")
    public List<ItemDto> findItemsByCategory(@RequestParam("category") @NotEmpty String category) {
    
        List<Item> findItems = itemSearchService.findAllByCategory(category);
        List<ItemDto> result = findItems.stream()
            .map(i -> new ItemDto(i.getBook(), i.getItemPrice(), i.getItemCondition(), i.getCreateDate(), i.getDealStatus(), i.getDealArea(), i.getHit()))
            .collect(Collectors.toList());
    
        return result;
    }
    
    @ApiOperation(value = "주소별 Item List 반환", notes = "주소별 Item List를 반환합니다. 주소의 이름을 first, second 별로 String으로 받습니다.")
    @GetMapping("/dealarea")
    public List<ItemDto>  findItemsByDealArea(@RequestParam("first") @NotEmpty String first, @RequestParam("second") @NotEmpty String second) {
    
        List<Item> findItems = itemSearchService.findAllByDealArea(first, second);
        List<ItemDto> result = findItems.stream()
            .map(i -> new ItemDto(i.getBook(), i.getItemPrice(), i.getItemCondition(), i.getCreateDate(), i.getDealStatus(), i.getDealArea(), i.getHit()))
            .collect(Collectors.toList());
    
        return result;
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {

        private int count;
        private T data;
    }
}