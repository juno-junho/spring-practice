package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    // item 저장
    @Transactional // readOnly 덮어쓴것
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public Item updateItem(Long itemId, Book param){
        Item findItem = itemRepository.findOne(itemId);
        findItem.setPrice(param.getPrice());
        findItem.setName(param.getName());
        findItem.setStockQuantity(param.getStockQuantity());
//        itemRepository.save(); // 호출 할 필요없다. (영속상태이기 때문)
        return findItem;
    }

    @Transactional
    public void updateItem(Long itemId,String name, int price, int stockQuantity) {
        Item item = itemRepository.findOne(itemId);
        item.setName(name);
        item.setPrice(price);
        item.setStockQuantity(stockQuantity);
    }
    // item list 찾기
    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    // item 하나 찾기
    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
