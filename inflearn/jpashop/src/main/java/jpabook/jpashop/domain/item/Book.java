package jpabook.jpashop.domain.item;

import jpabook.jpashop.controller.BookForm;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
@Getter
@Setter
public class Book extends Item {

    private String author;
    private String isbn;

    protected Book() {

    }
    public static Book createBook(Long id,String name, int price, int stockQuantity, String author, String isbn) {
        Book form = new Book();
        form.setId(id);
        form.setName(name);
        form.setPrice(price);
        form.setStockQuantity(stockQuantity);
        form.setAuthor(author);
        form.setIsbn(isbn);
        return form;
    }
    public static Book createBook(String name, int price, int stockQuantity, String author, String isbn) {
        Book form = new Book();
        form.setName(name);
        form.setPrice(price);
        form.setStockQuantity(stockQuantity);
        form.setAuthor(author);
        form.setIsbn(isbn);
        return form;
    }
    public static Book createBook(String name, int price, int stockQuantity) {
        Book form = new Book();
        form.setName(name);
        form.setPrice(price);
        form.setStockQuantity(stockQuantity);
        return form;
    }
}
