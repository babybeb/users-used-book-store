package babybeb.usersusedbookstore.domain;

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
    private SiDo siDo;
    
    @Enumerated(EnumType.STRING)
    private SiGunGu siGunGu;
    
    @Enumerated(EnumType.STRING)
    private EupMyeonDong eupMyeonDong;
    
    @Enumerated(EnumType.STRING)
    private Ri ri;
    
    public DealArea(SiDo siDo) {
        this.siDo = siDo;
    }
    
    public DealArea(SiDo siDo, SiGunGu siGunGu) {
        this.siDo = siDo;
        this.siGunGu = siGunGu;
    }
    
    public DealArea(SiDo siDo, SiGunGu siGunGu,
                    EupMyeonDong eupMyeonDong) {
        this.siDo = siDo;
        this.siGunGu = siGunGu;
        this.eupMyeonDong = eupMyeonDong;
    }
    
    public DealArea(SiDo siDo, SiGunGu siGunGu,
                    EupMyeonDong eupMyeonDong, Ri ri) {
        this.siDo = siDo;
        this.siGunGu = siGunGu;
        this.eupMyeonDong = eupMyeonDong;
        this.ri = ri;
    }
}