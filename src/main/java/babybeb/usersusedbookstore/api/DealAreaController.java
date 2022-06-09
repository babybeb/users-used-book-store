package babybeb.usersusedbookstore.api;

import babybeb.usersusedbookstore.domain.dealarea.First;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dealarea")
public class DealAreaController {
    
    @ApiOperation(value = "First 지역 반환", notes = "First 지역을 리스트로 반환합니다. 서울, 경기, "
        + "인천, 강원, 대전, 충남, 충북, 부산, 울산, 경남, 경북, 대구, 광주, 전남, 전북, 제주, 세종 순입니다. ")
    @GetMapping("/first")
    public ResponseEntity<List<String>> getFirstDealArea() {
        
        List<String> result = new ArrayList<>();
        
        for (First first : First.values()) {
            result.add(first.toString());
        }
        
        return ResponseEntity.ok(result);
    }
    
    @ApiOperation(value = "Second 지역 반환", notes = "Second 지역을 리스트로 반환합니다. 서울, 경기, "
        + "인천, 강원, 대전, 충남, 충북, 부산, 울산, 경남, 경북, 대구, 광주, 전남, 전북, 제주 순입니다. "
        + "세종은 Second 지역이 없습니다.")
    @GetMapping("/second")
    public ResponseEntity<List<List<String>>> getSecondDealArea() {
        
        List<List<String>> result = new ArrayList<>(new ArrayList<>());
        
        List<String> seoul = new ArrayList<>();
        seoul.add("강남구");
        seoul.add("강동구");
        seoul.add("강북구");
        seoul.add("강서구");
        seoul.add("관악구");
        seoul.add("광진구");
        seoul.add("구로구");
        seoul.add("금천구");
        seoul.add("노원구");
        seoul.add("도봉구");
        seoul.add("동대문구");
        seoul.add("동작구");
        seoul.add("마포구");
        seoul.add("서대문구");
        seoul.add("서초구");
        seoul.add("성동구");
        seoul.add("성북구");
        seoul.add("송파구");
        seoul.add("양천구");
        seoul.add("영등포구");
        seoul.add("용산구");
        seoul.add("은평구");
        seoul.add("종로구");
        seoul.add("중구");
        seoul.add("중랑구");
        result.add(seoul);
        
        List<String> gyeonggi = new ArrayList<>();
        gyeonggi.add("가평군");
        gyeonggi.add("고양시");
        gyeonggi.add("과천시");
        gyeonggi.add("광명시");
        gyeonggi.add("광주시");
        gyeonggi.add("구리시");
        gyeonggi.add("군포시");
        gyeonggi.add("김포시");
        gyeonggi.add("남양주시");
        gyeonggi.add("동두천시");
        gyeonggi.add("부천시");
        gyeonggi.add("성남시");
        gyeonggi.add("수원시");
        gyeonggi.add("시흥시");
        gyeonggi.add("안산시");
        gyeonggi.add("안성시");
        gyeonggi.add("안양시");
        gyeonggi.add("양주시");
        gyeonggi.add("양평군");
        gyeonggi.add("여주시");
        gyeonggi.add("연천군");
        gyeonggi.add("오산시");
        gyeonggi.add("용인시");
        gyeonggi.add("의왕시");
        gyeonggi.add("의정부시");
        gyeonggi.add("이천시");
        gyeonggi.add("파주시");
        gyeonggi.add("평택시");
        gyeonggi.add("포천시");
        gyeonggi.add("하남시");
        gyeonggi.add("화성시");
        result.add(gyeonggi);
        
        return ResponseEntity.ok(result);
    }
}
