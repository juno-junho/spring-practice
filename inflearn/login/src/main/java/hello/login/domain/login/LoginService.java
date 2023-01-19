package hello.login.domain.login;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
    // 핵심 비즈니스 로직
    private final MemberRepository memberRepository;

    /**
     * @return null이면 로그인 실패
     *
     */
    public Member login(String loginId, String password) {
        Optional<Member> findMemberOptional = memberRepository.findByLoginId(loginId);
        return findMemberOptional.filter(m -> m.getPassword().equals(password)).orElse(null);
    }
}
