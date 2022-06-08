package babybeb.usersusedbookstore.domain.dealarea;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class DealArea {
    
    @Enumerated(EnumType.STRING)
    private First first;
    
    @Enumerated(EnumType.STRING)
    private Second second;
    
    @Enumerated(EnumType.STRING)
    private Third third;
    
    @Enumerated(EnumType.STRING)
    private Fourth fourth;
    
    public DealArea(First first) {
        this.first = first;
    }
    
    public DealArea(First first, Second second) {
        this.first = first;
        this.second = second;
    }
    
    public DealArea(First first, Second second,
                    Third third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }
    
    public DealArea(First first, Second second,
                    Third third, Fourth fourth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
    }
}