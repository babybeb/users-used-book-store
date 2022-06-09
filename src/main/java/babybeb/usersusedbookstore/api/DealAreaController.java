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
        
        List<String> incheon = new ArrayList<>();
        incheon.add("강화군");
        incheon.add("계양구");
        incheon.add("남동구");
        incheon.add("미추홀구");
        incheon.add("부평구");
        incheon.add("연수구");
        incheon.add("옹진군");
        incheon.add("중구");
        incheon.add("동구");
        incheon.add("서구");
        result.add(incheon);
        
        List<String> gangwon = new ArrayList<>();
        gangwon.add("강릉시");
        gangwon.add("고성군");
        gangwon.add("동해시");
        gangwon.add("삼척시");
        gangwon.add("속초시");
        gangwon.add("양구군");
        gangwon.add("양양군");
        gangwon.add("영월군");
        gangwon.add("원주시");
        gangwon.add("인제군");
        gangwon.add("정선군");
        gangwon.add("철원군");
        gangwon.add("춘천시");
        gangwon.add("태백시");
        gangwon.add("평창군");
        gangwon.add("홍천군");
        gangwon.add("화천군");
        gangwon.add("횡성군");
        result.add(gangwon);
        
        List<String> daejeon = new ArrayList<>();
        daejeon.add("대덕구");
        daejeon.add("동구");
        daejeon.add("서구");
        daejeon.add("유성구");
        daejeon.add("중구");
        result.add(daejeon);
        
        List<String> chungnam = new ArrayList<>();
        chungnam.add("계룡시");
        chungnam.add("공주시");
        chungnam.add("금산군");
        chungnam.add("논산시");
        chungnam.add("당진시");
        chungnam.add("보령시");
        chungnam.add("부여군");
        chungnam.add("서산시");
        chungnam.add("서천군");
        chungnam.add("아산시");
        chungnam.add("예산군");
        chungnam.add("천안시");
        chungnam.add("청양군");
        chungnam.add("태안군");
        chungnam.add("홍성군");
        result.add(chungnam);
        
        List<String> chungbuk = new ArrayList<>();
        chungbuk.add("괴산군");
        chungbuk.add("단양군");
        chungbuk.add("보은군");
        chungbuk.add("영동군");
        chungbuk.add("옥천군");
        chungbuk.add("음성군");
        chungbuk.add("제천시");
        chungbuk.add("증평군");
        chungbuk.add("진천군");
        chungbuk.add("청주시");
        chungbuk.add("충주시");
        result.add(chungbuk);
        
        List<String> busan = new ArrayList<>();
        busan.add("강서구");
        busan.add("금정구");
        busan.add("기장군");
        busan.add("남구");
        busan.add("동구");
        busan.add("동래구");
        busan.add("부산진구");
        busan.add("북구");
        busan.add("사상구");
        busan.add("사하구");
        busan.add("서구");
        busan.add("수영구");
        busan.add("연제구");
        busan.add("영도구");
        busan.add("중구");
        busan.add("해운대구");
        result.add(busan);
        
        List<String> ulsan = new ArrayList<>();
        ulsan.add("남구");
        ulsan.add("동구");
        ulsan.add("북구");
        ulsan.add("울주군");
        ulsan.add("중구");
        result.add(ulsan);
        
        List<String> gyeongnam = new ArrayList<>();
        gyeongnam.add("거제시");
        gyeongnam.add("거창군");
        gyeongnam.add("고성군");
        gyeongnam.add("김해시");
        gyeongnam.add("남해군");
        gyeongnam.add("밀양시");
        gyeongnam.add("사천시");
        gyeongnam.add("산청군");
        gyeongnam.add("양산시");
        gyeongnam.add("의령군");
        gyeongnam.add("진주시");
        gyeongnam.add("창녕군");
        gyeongnam.add("창원시");
        gyeongnam.add("통영시");
        gyeongnam.add("하동군");
        gyeongnam.add("함안군");
        gyeongnam.add("함양군");
        gyeongnam.add("합천군");
        result.add(gyeongnam);
        
        List<String> gyeongbuk = new ArrayList<>();
        gyeongbuk.add("경산시");
        gyeongbuk.add("경주시");
        gyeongbuk.add("고령군");
        gyeongbuk.add("구미시");
        gyeongbuk.add("군위군");
        gyeongbuk.add("김천시");
        gyeongbuk.add("문경시");
        gyeongbuk.add("봉화군");
        gyeongbuk.add("상주시");
        gyeongbuk.add("성주군");
        gyeongbuk.add("안동시");
        gyeongbuk.add("영덕군");
        gyeongbuk.add("영양군");
        gyeongbuk.add("영주시");
        gyeongbuk.add("영천시");
        gyeongbuk.add("예천군");
        gyeongbuk.add("울릉군");
        gyeongbuk.add("울진군");
        gyeongbuk.add("의성군");
        gyeongbuk.add("청도군");
        gyeongbuk.add("청송군");
        gyeongbuk.add("칠곡군");
        gyeongbuk.add("포항시");
        result.add(gyeongbuk);
        
        List<String> daegu = new ArrayList<>();
        daegu.add("남구");
        daegu.add("달서구");
        daegu.add("달성군");
        daegu.add("동구");
        daegu.add("북구");
        daegu.add("서구");
        daegu.add("수성구");
        daegu.add("중구");
        result.add(daegu);
        
        List<String> gwangju = new ArrayList<>();
        gwangju.add("광산구");
        gwangju.add("남구");
        gwangju.add("동구");
        gwangju.add("북구");
        gwangju.add("서구");
        result.add(gwangju);
        
        List<String> jeonnam = new ArrayList<>();
        jeonnam.add("강진군");
        jeonnam.add("고흥군");
        jeonnam.add("곡성군");
        jeonnam.add("광양시");
        jeonnam.add("구례군");
        jeonnam.add("나주시");
        jeonnam.add("담양군");
        jeonnam.add("목포시");
        jeonnam.add("무안군");
        jeonnam.add("보성군");
        jeonnam.add("순천시");
        jeonnam.add("신안군");
        jeonnam.add("여수시");
        jeonnam.add("영광군");
        jeonnam.add("영암군");
        jeonnam.add("완도군");
        jeonnam.add("장성군");
        jeonnam.add("장흥군");
        jeonnam.add("진도군");
        jeonnam.add("함평군");
        jeonnam.add("해남군");
        jeonnam.add("화순군");
        result.add(jeonnam);
    
        List<String> jeonbuk = new ArrayList<>();
        jeonbuk.add("고창군");
        jeonbuk.add("군산시");
        jeonbuk.add("김제시");
        jeonbuk.add("남원시");
        jeonbuk.add("무주군");
        jeonbuk.add("부안군");
        jeonbuk.add("순창군");
        jeonbuk.add("완주군");
        jeonbuk.add("익산시");
        jeonbuk.add("임실군");
        jeonbuk.add("장수군");
        jeonbuk.add("전주시");
        jeonbuk.add("정읍시");
        jeonbuk.add("진안군");
        result.add(jeonbuk);
        
        List<String> jeju = new ArrayList<>();
        jeju.add("서귀포시");
        jeju.add("제주시");
        result.add(jeju);
        
        return ResponseEntity.ok(result);
    }
}
