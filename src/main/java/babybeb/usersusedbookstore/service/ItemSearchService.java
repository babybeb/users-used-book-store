package babybeb.usersusedbookstore.service;

import babybeb.usersusedbookstore.repository.ItemSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemSearchService {
    
    private final ItemSearchRepository itemSearchRepository;

}
