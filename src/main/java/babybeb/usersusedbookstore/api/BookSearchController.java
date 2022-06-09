package babybeb.usersusedbookstore.api;

import babybeb.usersusedbookstore.api.dto.book.BookResponse;
import babybeb.usersusedbookstore.domain.Book;
import babybeb.usersusedbookstore.service.BookSearchService;
import babybeb.usersusedbookstore.service.dto.BookDto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book/")
public class BookSearchController {
    
    private final BookSearchService bookSearchService;
    
    @GetMapping("/title")
    public ResponseEntity<List<BookResponse>> findBookByTitle(@NotEmpty @RequestParam String title)
        throws IOException {
        
        List<BookResponse> result = new ArrayList<>();
        
        List<BookDto> bookDtoList = bookSearchService.searchBookInfosByTitle(title);
        
        for (BookDto bookDto : bookDtoList) {
            result.add(BookResponse.toItemResponse(
                new Book(bookDto.getIsbn(), bookDto.getTitle(), bookDto.getPrice(),
                         bookDto.getPublisher(), bookDto.getAuthor(), bookDto.getPage(),
                         bookDto.getKdc(), bookDto.getCategory())));
        }
        
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/author")
    public ResponseEntity<List<BookResponse>> findBookByAuthor(@NotEmpty @RequestParam String author)
        throws IOException {
        
        List<BookResponse> result = new ArrayList<>();
        
        List<BookDto> bookDtoList = bookSearchService.searchBookInfosByAuthor(author);
        
        for (BookDto bookDto : bookDtoList) {
            result.add(BookResponse.toItemResponse(
                new Book(bookDto.getIsbn(), bookDto.getTitle(), bookDto.getPrice(),
                         bookDto.getPublisher(), bookDto.getAuthor(), bookDto.getPage(),
                         bookDto.getKdc(), bookDto.getCategory())));
        }
        
        return ResponseEntity.ok(result);
    }
}