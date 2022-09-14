package other;

import other.Member;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Locker {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    // 읽기 전용
    @OneToOne(mappedBy = "locker")
    private Member member;

}
