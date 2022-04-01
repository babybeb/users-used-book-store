package babybeb.usersusedbookstore.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String email;
    private String password;
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


    protected Member() {
    }

    public Member(String email, String password, String name, String nickname, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
    }

    public void updateMemberInfo(MemberDto memberDTO){
        this.email = memberDTO.getEmail();
        this.name = memberDTO.getName();
        this.nickname = memberDTO.getNickname();
        this.phoneNumber = memberDTO.getPhoneNumber();
        this.sex = memberDTO.getSex();
    }
}
