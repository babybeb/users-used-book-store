package babybeb.usersusedbookstore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class ImageFile {
    
    @Id
    @GeneratedValue
    @Column(name = "image_file_id")
    private Long id;
    
    @ManyToOne
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