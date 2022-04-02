package babybeb.usersusedbookstore.Service;

import babybeb.usersusedbookstore.domain.Member;
import babybeb.usersusedbookstore.domain.MemberDto;
import babybeb.usersusedbookstore.repository.MemberRepository;
import babybeb.usersusedbookstore.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception{
        //given
        Member member1 = new Member( "ggeggrgg@naver.com","1234" ,"김민수",
                "GgEgg", "010-5407-9254");
        Member member2 = new Member( "test@naver.com","1234" ,"김민수",
                "test1", "010-0000-0000");
        Member member3 = new Member( "test@gmail.com","1234" ,"테스트",
                "test2", "010-1111-1111");
        Member member4 = new Member( "test2@gmail.com","1234" ,"테스트",
                "test3", "000-0000-0000");

        //when
        Long saveId1 = memberService.join(member1);
        Long saveId2 = memberService.join(member2);
        Long saveId3 = memberService.join(member3);
        Long saveId4 = memberService.join(member4);

        //then
        assertThat(member1).isEqualTo(memberRepository.findOne(saveId1));
        assertThat(member2).isEqualTo(memberRepository.findOne(saveId2));
        assertThat(member3).isEqualTo(memberRepository.findOne(saveId3));
        assertThat(member4).isEqualTo(memberRepository.findOne(saveId4));
    }

    @Test
    public void 닉네임_중복_예외() throws Exception{
        //given
        Member member1 = new Member( "ggeggrgg@naver.com","1234" ,"김민수",
                "GgEgg", "010-5407-9254");
        Member member2 = new Member( "test@naver.com","1234" ,"김민수",
                "GgEgg", "010-1111-1111");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));

        //then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 닉네임입니다.");
    }

    @Test
    public void 이메일_중복_예외() throws Exception{
        //given
        Member member1 = new Member( "ggeggrgg@naver.com","1234" ,"김민수",
                "GgEgg", "010-5407-9254");
        Member member2 = new Member( "ggeggrgg@naver.com","1234" ,"김민수",
                "test", "010-5407-9254");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));

        //then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 이메일입니다.");
    }

    @Test
    public void 휴대폰번호_중복_예외() throws Exception{
        //given
        Member member1 = new Member( "ggeggrgg@naver.com","1234" ,"김민수",
                "GgEgg", "010-5407-9254");
        Member member2 = new Member( "test@naver.com","1234" ,"테스트",
                "test", "010-5407-9254");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));

        //then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 휴대폰번호입니다.");
    }
    
    @Test
    public void 회원정보_수정() throws Exception{
        //given
        Member member = new Member("ggeggrgg@naver.com", "1234" ,"김민수",
                "GgEgg", "010-5407-9254");
        memberService.join(member);

        //when
        MemberDto memberDto = new MemberDto("ggeggrgg@naver.com", "테스트",
                "test", "010-5407-9254");
        memberService.updateMemberInfo(member.getId(), memberDto);

        //then
        assertThat(member.getEmail()).isEqualTo("ggeggrgg@naver.com");
        assertThat(member.getPhoneNumber()).isEqualTo("010-5407-9254");

        assertThat(member.getName()).isEqualTo("테스트");
        assertThat(member.getNickname()).isEqualTo("test");
    }

    @Test
    public void 회원탈퇴() throws Exception{
        //given
        Member member1 = new Member("ggeggrgg@naver.com", "1234" ,"김민수",
                "GgEgg", "010-5407-9254");
        Member member2 = new Member("test@naver.com", "1234" ,"김민수",
                "test", "010-0000-0000");
        memberService.join(member1);
        memberService.join(member2);
        List<Member> beforeMembers = memberService.findMembers();

        //when
        memberService.removeMember(member1.getId());
        List<Member> afterMembers = memberService.findMembers();

        //then
        assertThat(memberService.findOne(member1.getId())).isEqualTo(null);
        assertThat(beforeMembers.size()).isGreaterThan(afterMembers.size());
    }

    @Test
    public void 로그인_성공() throws Exception{
        //given
        Member member1 = new Member("ggeggrgg@naver.com", "1234" ,"김민수",
                "GgEgg", "010-5407-9254");
        Member member2 = new Member("test@naver.com", "1234" ,"김민수",
                "test", "010-0000-0000");
        memberService.join(member1);
        memberService.join(member2);

        //when
        Long logInMemberId = memberService.logIn("ggeggrgg@naver.com", "1234");

        //then
        assertThat(logInMemberId).isEqualTo(member1.getId());
    }
    
    @Test
    public void 로그인_실패() throws Exception{
        //given
        Member member1 = new Member("ggeggrgg@naver.com", "1234" ,"김민수",
                "GgEgg", "010-5407-9254");
        Member member2 = new Member("test@naver.com", "1234" ,"김민수",
                "test", "010-0000-0000");
        memberService.join(member1);
        memberService.join(member2);

        //when
        IllegalStateException e1 = assertThrows(IllegalStateException.class,
                () -> memberService.logIn("ggeggrgg@naver.com", "1111"));
        IllegalStateException e2 = assertThrows(IllegalStateException.class,
                () -> memberService.logIn("test", "1234"));
        
        //then
        assertThat(e1.getMessage()).isEqualTo("로그인 실패");
        assertThat(e2.getMessage()).isEqualTo("로그인 실패");

    }
    



}
