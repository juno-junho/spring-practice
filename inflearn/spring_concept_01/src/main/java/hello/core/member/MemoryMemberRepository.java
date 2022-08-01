package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{

    // 동시성 이슈가 있을 수 있기 때문에 concurrentHashMap을 써야함. (여러군데에서 이것을 접근하면 문제 ..)
    private static Map<Long, Member> store = new HashMap<>();


    // 회원 저장
    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }
    //회원 검색
    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
