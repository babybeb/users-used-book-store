package babybeb.usersusedbookstore.service;

import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:data.properties")
public class MessageService {

    @Value("${apiKey}")
    private String apiKey;

    @Value("${apiSecret}")
    private String apiSecret;

    @Value("${fromNumber}")
    private String fromNumber;

    public void sendMessage(String toNumber, String randomNumber){

        Message coolsms = new Message(apiKey, apiSecret);

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", toNumber);
        params.put("from", fromNumber);
        params.put("type", "SMS");
        params.put("text", "[UsersUsedBookStore]인증번호 " + randomNumber + " 를 입력하세요.");

        try{
            JSONObject obj = (JSONObject) coolsms.send(params);
        } catch (CoolsmsException e){
            throw new IllegalStateException("전송 실패");
        }
    }
}
