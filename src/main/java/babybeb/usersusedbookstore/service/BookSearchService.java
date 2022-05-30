package babybeb.usersusedbookstore.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookSearchService {
    
    @Value("${ISBNApi.certKey}")
    private String certKey;
    
    // 책 제목을 가지고 도서 API를 통해 도서 정보 받아오기
    public List<BookDto> searchBookInfosByTitle(String searchTitle) throws IOException {
        
        StringBuilder sbURL = new StringBuilder(
            "https://seoji.nl.go.kr/landingPage/SearchApi.do?");
        sbURL.append("cert_key=" + certKey);
        sbURL.append("&result_style=json");
        sbURL.append("&page_no=1");
        sbURL.append("&page_size=10");
        sbURL.append("&title=" + URLEncoder.encode(searchTitle, "UTF-8"));
        URL url = new URL(sbURL.toString());
        
        return parseData(url);
    }
    
    public List<BookDto> searchBookInfosByAuthor(String searchAuthor) throws IOException {
        
        StringBuilder sbURL = new StringBuilder(
            "https://seoji.nl.go.kr/landingPage/SearchApi.do?");
        sbURL.append("cert_key=" + certKey);
        sbURL.append("&result_style=json");
        sbURL.append("&page_no=1");
        sbURL.append("&page_size=10");
        sbURL.append("&author=" + URLEncoder.encode(searchAuthor, "UTF-8"));
        URL url = new URL(sbURL.toString());
        
        return parseData(url);
    }
    
    private List<BookDto> parseData(URL url) {
        
        String parsingData = "";
        List<BookDto> result = new LinkedList<>();
        
        try {
            BufferedReader bf = new BufferedReader(
                new InputStreamReader(url.openStream(), "UTF-8"));
            parsingData = bf.readLine();
            
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(parsingData);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray jsonArray = (JSONArray) jsonObject.get("docs");
            
            for (int i = 0; (i < jsonArray.size()) && (i < 10); i++) {
                JSONObject o = (JSONObject) jsonArray.get(i);
                System.out.println("JSONObject: " + o.toString());
                if (o.get("FORM").equals("전자책")) {
                    continue;
                }
                int page = calculatePage((String) o.get("PAGE"));
                int adjustedPrePrice = adjustPrePrice((String) o.get("PRE_PRICE"));
                BookDto bookInfo = new BookDto((String) o.get("EA_ISBN"),
                                               (String) o.get("TITLE"), adjustedPrePrice,
                                               (String) o.get("PUBLISHER"),
                                               (String) o.get("AUTHOR"),
                                               page,
                                               (String) o.get("KDC"));
                
                result.add(bookInfo);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    private int calculatePage(String page) {
        
        if (page.isEmpty()) {
            return 0;
        } else {
            String calculatedPage = "";
            for (int i = 0; i < page.length(); i++) {
                char curChar = page.charAt(i);
                if (curChar >= '0' && curChar <= '9') { // 페이지에 숫자 정보가 있으면 calculatedPage에 추가한다
                    calculatedPage += curChar;
                }
            }
            return Integer.parseInt(calculatedPage);
        }
    }
    
    private int adjustPrePrice(String originPrePrice) {
        
        if (originPrePrice.equals("비매품/무료") || originPrePrice.isEmpty()) {
            return 0;
        } else {
            String adjustedPrePrice = "";
            for (int i = 0; i < originPrePrice.length(); i++) {
                char curChar = originPrePrice.charAt(i);
                if (curChar >= '0' && curChar <= '9') {
                    adjustedPrePrice += curChar;
                }
            }
            return Integer.parseInt(adjustedPrePrice);
        }
    }
}