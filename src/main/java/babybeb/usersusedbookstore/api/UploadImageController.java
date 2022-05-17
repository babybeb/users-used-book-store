package babybeb.usersusedbookstore.api;

import babybeb.usersusedbookstore.service.AwsS3Service;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class UploadImageController {
    
    private final AwsS3Service awsS3Service;
    
    @PostMapping("/{item_id}/images")
    public ResponseEntity<List<String>> uploadImages(
        @RequestPart List<MultipartFile> multipartFiles) throws IOException {
        return ApiResponse.success(awsS3Service.uploadImages(multipartFiles));
    }
    
    @DeleteMapping("/{item_id}/images")
    public ResponseEntity<List<String>> deleteImages(
        @RequestParam List<String> fileNames) {
        awsS3Service.deleteImages(fileNames);
        return ApiResponse.success(null);
    }
}
