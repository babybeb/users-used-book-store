package babybeb.usersusedbookstore.api.dto.item;

import babybeb.usersusedbookstore.domain.ItemCondition;
import babybeb.usersusedbookstore.domain.dealarea.DealArea;
import lombok.Getter;

@Getter
public class UpdateItemRequest {
    
    private int itemPrice;
    
    private ItemCondition itemCondition;
    
    private DealArea dealArea; // value object
}
