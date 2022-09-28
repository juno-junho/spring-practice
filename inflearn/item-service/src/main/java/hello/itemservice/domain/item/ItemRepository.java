package hello.itemservice.domain.item;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>();   // 실제로는 쓰면 안됨. ConcurrentHashMap 써야함.
    private static long sequence = 0l;  // 따로 new 해서 ItemRepository 안쓰니 이렇게 만드는 것. atomicLong 써야함

    //저장
    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    // id로 찾기
    public Item findById(Long id) {
        return store.get(id);
    }

    //find all
    public List<Item> findAll() {
        return new ArrayList<>(store.values());
//        return (List<Item>) store.values();
        // 감싸서 반환하게 되면 arraylist에 값을 넣어도 더이상 store에는 변함이 없기 때문에 안전하게 감싼것. 타입 바꿔야 하는 문제도 있기 때문에
    }

    // update -> map에 다시 put 안해줘도 되나? -> 컬렉션에 값은 바꾸면 그대로 유지됨. 다시 안넣어줘도됨.
    // 이 경우에는 parameter dto로 만들어서 id 빼고 나머지 3개의 필드만 사용해야함. 그게 더 깔끔하고 설계상 명확함.
    // 중복 vs 명확성일때에는 명확성을 따르는게 훨씬 낫다.
    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }

}
