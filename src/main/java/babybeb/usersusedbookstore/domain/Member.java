package babybeb.usersusedbookstore.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String email;
    private String name;
    private String nickname;
    private String phoneNumber;
    private String sex;

//    @OneToMany
//    private List<Purchase> purchaseList;
//
//    @OneToMany(mappedBy = "member")
//    private List<Sale> saleList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "member")
//    private List<Wish> wishList = new ArrayList<>();
//
//    @OneToMany
//    private List<chatRoom> chatRoomList;

}
