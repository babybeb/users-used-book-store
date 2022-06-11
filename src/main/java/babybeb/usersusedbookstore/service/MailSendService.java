package babybeb.usersusedbookstore.service;

import babybeb.usersusedbookstore.MailUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service("mss")
@RequiredArgsConstructor
public class MailSendService {

    private final JavaMailSender mailSender;
    private int size;

    @Value("${Email.fromEmail}")
    private String fromEmail;

    /**
     * 인증키 생성
     */
    private String getKey(int size){
        this.size = size;
        return getAuthCode();
    }

    /**
     * 인증코드 난수 발생
     */
    private String getAuthCode(){
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        int num = 0;

        while(buffer.length() < size){
            num = random.nextInt(10);
            buffer.append(num);
        }

        return buffer.toString();
    }

    /**
     * 인증메일 보내기
     */
    public String sendAuthMail(String email){
        //6자리 난수 인증번호 생성
        String authKey = getKey(6);

        //인증메일 보내기
        try{
            MailUtils sendMail = new MailUtils(mailSender);
            sendMail.setSubject("회원가입 이메일 인증");
            sendMail.setText(new StringBuffer().append("<h1>중고 서적 직거래 사이트</h1>")
                    .append("<p>아래의 코드를 입력해서 회원가입을 완료합니다.<p>")
                    .append("<P>authKey=")
                    .append(authKey)
                    .append("</P>")
                    .toString());
            sendMail.setFrom(fromEmail, "중고 서적 직거래 사이트");
            sendMail.setTo(email);
            sendMail.send();
        } catch (MessagingException e){
            e.printStackTrace();
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return authKey;
    }
}
