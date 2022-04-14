package babybeb.usersusedbookstore.service;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookSearchService {
    
    @Value("${ISBNApi.certKey}")
    private String certKey;
    
    // 책 제목을 가지고 도서 API를 통해 도서 정보 받아오기
    public List<BookDto> searchBookInfosByTitle(String searchTitle) {
        
        List<BookDto> result = new LinkedList<>();
        
        try {
            StringBuilder stringBuilder = new StringBuilder(
                "https://seoji.nl.go.kr/landingPage/SearchApi.do?");
            stringBuilder.append("cert_key=" + certKey);
            stringBuilder.append("&result_style=json");
            stringBuilder.append("&page_no=1");
            stringBuilder.append("&page_size=10");
            stringBuilder.append("title=" + searchTitle);
            URL url = new URL(stringBuilder.toString());
            
            BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(url.openStream(), "UTF-8"));
            String jsonString = bufferedReader.readLine();
            
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("docs");
            
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject o = jsonArray.getJSONObject(i);
                BookDto bookInfo = new BookDto(o.getString("EA_ISBN"), o.getString("TITLE"),
                                               o.getInt("PRE_PRICE"),
                                               o.getString("PUBLISHER"), o.getString("AUTHOR"),
                                               o.getInt("PAGE"), o.getString("KDC"));
                result.add(bookInfo);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }

//    public List<BookDto> searchBookInfosByAuthor(String searchAuthor) {
//
//        return result;
//    }
}