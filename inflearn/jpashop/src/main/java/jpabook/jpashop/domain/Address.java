package jpabook.jpashop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable //내장타입
@Getter
@AllArgsConstructor
public class Address {

    protected Address(){

    }
    private String city;
    private String street;
    private String zipcode;


}
