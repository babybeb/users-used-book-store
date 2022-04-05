package babybeb.usersusedbookstore.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("SALE")
@Getter
public class Sale extends Trade{

}
