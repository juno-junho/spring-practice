package study.datajpa.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    protected Member() {    // protected 까지만 허용. 프록싱 기술 쓸때 객체를 만든다.

    }

    public Member(String username) {
        this.username = username;
    }
}
