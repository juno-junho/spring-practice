package hello.itemservice.domain.item;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Item test1 = new Item("test1", 3, 1);
        Item test2 = new Item("test2", 5, 2);
        Item test3 = new Item("test3", 24, 4);
        Item test4 = new Item("test4", 32, 3);
        ItemRepository repository = new ItemRepository();
        repository.save(test1);
        repository.save(test2);
        System.out.println(repository.findAll());
        repository.save(test4);
        repository.update(1l, test3);

    }
}
