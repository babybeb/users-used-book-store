package babybeb.usersusedbookstore;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

//Spring에서 제공하는 RequestContextHolder를 이용하여 request객체를 Service에 전달하지 않고
//사용할 수 있게 해줌
public class SessionUtil {

    /**
     * attribute 값을 가져오기 위한 method
     */
    public static Object getAttribute(String name) throws Exception{
        return (Object) RequestContextHolder.getRequestAttributes()
                .getAttribute(name, RequestAttributes.SCOPE_SESSION);
    }

    /**
     * attribute 값을 설정하기 위한 method
     */
    public static void setAttribute(String name, Object object) throws Exception{
        RequestContextHolder.getRequestAttributes()
                .setAttribute(name, object, RequestAttributes.SCOPE_SESSION);
    }

    /**
     * 설정한 attribute 삭제
     */
    public static void removeAttribute(String name) throws Exception{
        RequestContextHolder.getRequestAttributes()
                .removeAttribute(name, RequestAttributes.SCOPE_SESSION);
    }

    /**
     * 세션 아이디값 반환
     */
    public static String getSessionId(){
        return RequestContextHolder.getRequestAttributes().getSessionId();
    }
}
