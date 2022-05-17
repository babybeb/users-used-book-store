package babybeb.usersusedbookstore.repository;

import babybeb.usersusedbookstore.domain.ImageFile;
import babybeb.usersusedbookstore.domain.Item;
import babybeb.usersusedbookstore.domain.Member;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ImageFileRepository {
    
    private final EntityManager em;
    
    public List<Long> saveAll(List<ImageFile> imageFileList) {
        List<Long> idList = new ArrayList<>();
        for (ImageFile imageFile : imageFileList) {
            idList.add(save(imageFile));
        }
        
        return idList;
    }
    
    public Long save(ImageFile imageFile) {
        if (imageFile.getId() == null) {
            em.persist(imageFile);
        } else {
            em.merge(imageFile);
        }
        return imageFile.getId();
    }
    
    public ImageFile findOne(Long id) {
        return em.find(ImageFile.class, id);
    }
}
