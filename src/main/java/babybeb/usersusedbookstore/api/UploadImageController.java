package babybeb.usersusedbookstore.api;

import babybeb.usersusedbookstore.domain.ImageFile;
import babybeb.usersusedbookstore.service.AwsS3Service;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    @ApiOperation(value = "Upload Images to AWS S3")
    @PostMapping("/{item_id}/images")
    public List<UploadImagesResponse> uploadImages(@PathVariable("item_id") Long itemId,
                                                   @RequestPart List<MultipartFile> multipartFiles)
        throws IOException {
        List<UploadImagesResponse> uploadImagesResponses = new ArrayList<>();
        
        List<ImageFile> imageFiles = awsS3Service.uploadImages(itemId, multipartFiles);
        for (ImageFile imageFile : imageFiles) {
            uploadImagesResponses.add(new UploadImagesResponse(imageFile.getId()));
        }
        
        return uploadImagesResponses;
    }
    
    @ApiOperation(value = "Upload Images to AWS S3")
    @DeleteMapping("/{item_id}/images")
    public HttpStatus deleteImages(@PathVariable("item_id") Long itemId,
                                   @RequestParam List<String> fileNames) {
        awsS3Service.deleteImages(fileNames);
        
        return HttpStatus.OK;
    }
    
    @AllArgsConstructor
    static class UploadImagesResponse {
        
        private Long id;
    }
}
