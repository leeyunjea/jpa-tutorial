package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    /**
     * merge 방법 : 쓰지 않는것이 좋음.
     * 모든 데이터 필드를 수정함. 데이터가 없는경우 null로 삽입.
     */
    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    /**
     * 변경 감지
     * save할 필요 없이 @Transactional 때문에 커밋이 일어남. (flush)
     */
    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) { // UpdateItemDto로 넘기는게 유지보수 좋음.
        Item findItem = itemRepository.findOne(itemId);
//        findItem.change(price, name, stockQuantity); : 의미 있는 함수를 만들어서 변경하는것이 좋음. setter는 지양하기.
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
