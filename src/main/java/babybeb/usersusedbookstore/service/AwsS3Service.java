package babybeb.usersusedbookstore.service;

import babybeb.usersusedbookstore.domain.ImageFile;
import babybeb.usersusedbookstore.domain.Item;
import babybeb.usersusedbookstore.repository.ImageFileRepository;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class AwsS3Service {
    
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    
    private final ImageFileRepository imageFileRepository;
    private final AmazonS3Client amazonS3Client;
    private final ItemService itemService;
    
    /**
     * MultipartFile List를 매개변수로 받아 S3에 업로드하는 메서드
     *
     * @param multipartFiles
     * @return fileNameList
     * @throws IOException
     */
    public List<ImageFile> uploadImages(Long itemId, List<MultipartFile> multipartFiles) throws IOException {
        List<ImageFile> imageFileList = new ArrayList<>();
        Item item = itemService.findById(itemId);
    
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                imageFileList.add(uploadImage(item, multipartFile));
            }
        }
        
        imageFileRepository.saveAll(imageFileList);
        
        return imageFileList;
    }
    
    /**
     * fileName List를 매개변수로 받아 해당 파일들을 S3 버킷에서 지우는 메서드
     *
     * @param fileNames
     */
    public void deleteImages(List<String> fileNames) {
        for (String fileName : fileNames) {
            deleteImage(fileName);
        }
    }
    
    /**
     * MultipartFile 1개를 S3에 업로드하는 메서드
     *
     * @param multipartFile
     * @return fileName
     */
    private ImageFile uploadImage(Item item, MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            return null;
        }
        
        String fileName = createFileName(multipartFile.getOriginalFilename());
        putS3(multipartFile, fileName);
    
        
        return new ImageFile(item, multipartFile.getOriginalFilename(), fileName);
    }
    
    /**
     * MultipartFile과 String을 매개변수로 받아 S3에 public 읽기 권한으로 put하는 메서드
     *
     * @param uploadFile
     * @param fileName
     */
    private void putS3(MultipartFile uploadFile, String fileName) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(uploadFile.getSize());
        objectMetadata.setContentType(uploadFile.getContentType());
        
        try (InputStream inputStream = uploadFile.getInputStream()) {
            amazonS3Client.putObject(
                new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "이미지 업로드 실패");
        }
    }
    
    /**
     * originalFileName을 매개변수로 받아 uuid로 file의 이름을 생성한 것에 원래 파일의 확장자를 더해 반환하는 메서드
     *
     * @param originalFileName
     * @return
     */
    private String createFileName(String originalFileName) {
        String ext = extractExt(originalFileName);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }
    
    /**
     * originalFileName을 매개변수로 받아 해당 파일의 확장자를 구하는 메서드
     *
     * @param originalFileName
     * @return
     */
    private String extractExt(String originalFileName) {
        try {
            return originalFileName.substring(originalFileName.lastIndexOf("." + 1));
        } catch (StringIndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "파일 확장자 오류");
        }
    }
    
    /**
     * fileName 1개를 매개변수로 받아 해당 파일을 S3 버킷에서 지우는 메서드
     *
     * @param fileName
     */
    private void deleteImage(String fileName) {
        amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, fileName));
    }
}