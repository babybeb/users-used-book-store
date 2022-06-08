package babybeb.usersusedbookstore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageFile {
    
    @Id
    @GeneratedValue
    @Column(name = "image_file_id")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
    
    @NotEmpty
    private String originalFileName;
    
    @NotEmpty
    private String storeFileName;
    
    public ImageFile(Item item, String originalFileName, String storeFileName) {
        this.item = item;
        this.originalFileName = originalFileName;
        this.storeFileName = storeFileName;
    }
}