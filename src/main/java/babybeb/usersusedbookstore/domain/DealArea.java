package babybeb.usersusedbookstore.domain;

import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class DealArea {
    
    private String siDo;
    
    private String siGunGu;
    
    private String eupMyeonDong;
    
    private String ri;
    
    public DealArea(String siDo) {
        this.siDo = siDo;
    }
    
    public DealArea(String siDo, String siGunGu) {
        this.siDo = siDo;
        this.siGunGu = siGunGu;
    }
    
    public DealArea(String siDo, String siGunGu, String eupMyeonDong) {
        this.siDo = siDo;
        this.siGunGu = siGunGu;
        this.eupMyeonDong = eupMyeonDong;
    }
    
    public DealArea(String siDo, String siGunGu, String eupMyeonDong, String ri) {
        this.siDo = siDo;
        this.siGunGu = siGunGu;
        this.eupMyeonDong = eupMyeonDong;
        this.ri = ri;
    }
}