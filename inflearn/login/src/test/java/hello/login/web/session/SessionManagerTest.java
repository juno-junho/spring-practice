package hello.login.web.session;

import hello.login.domain.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;

class SessionManagerTest {

    SessionManager sessionManager = new SessionManager();

    @Test
    void sessionTest() {
        // HttoServletResponse같은 경우 interface이기 때문에 테스트 하기가 어렵다 -> Mock을 제공해준다 (Spring이 제공)
        // 세션 생성 (server에서 웹브라우저로 응답이 나감)
        MockHttpServletResponse response = new MockHttpServletResponse();
        Member member = new Member();
        sessionManager.createSession(member, response);

        // 요청에 응답 쿠키 저장되었는지 확인(client에서 server로 전송하는 것) 웹브라우저가 쿠키를 만들어 우리 서버에 전달해 주는것.
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());

        // 세션을 조회 (서버에서 확인해 보면 된다)
        Object result = sessionManager.getSession(request);
        assertThat(result).isEqualTo(member);

        // 세션 만료
        sessionManager.expire(request);
        Object expired = sessionManager.getSession(request);
        assertThat(expired).isNull();
    }

}