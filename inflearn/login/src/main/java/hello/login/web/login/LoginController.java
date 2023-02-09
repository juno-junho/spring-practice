package hello.login.web.login;

import hello.login.domain.login.LoginService;
import hello.login.domain.member.Member;
import hello.login.web.SessionConst;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final SessionManager sessionManager;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm loginForm) {
        return "login/loginForm";
    }

//    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }
        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않슴다.");
            return "login/loginForm";   // 다시 입력 받게 만들기
        }

        // 로그인 성공 처리 TODO 쿠키를 만들어 저장시켜줘야 한다.
        // 쿠키에 시간 정보를 주지 않으면 세션 쿠기가 된다(브라우저 종료시 모두 종료)
        Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
        response.addCookie(idCookie);

        return "redirect:/";
    }

//    @PostMapping("/login")
    public String loginV2(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }
        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않슴다.");
            return "login/loginForm";   // 다시 입력 받게 만들기
        }

        // 로그인 성공 처리 TODO 쿠키를 만들어 저장시켜줘야 한다.

        // 세션 관리자를 통해 세션을 생성하고 회원 데이터를 보관
        sessionManager.createSession(loginMember, response);
        // 쿠키에 시간 정보를 주지 않으면 세션 쿠기가 된다(브라우저 종료시 모두 종료)
        Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
        response.addCookie(idCookie);

        return "redirect:/";
    }
    @PostMapping("/login")
    public String loginV3(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }
        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않슴다.");
            return "login/loginForm";   // 다시 입력 받게 만들기
        }

        // 로그인 성공 처리
        // session이 있으면 있는 세션을 반환하고 없으면 신규 세션을 생성해서 반환한다.
        HttpSession session = request.getSession();
        // 세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        // 세션 관리자를 통해 세션을 생성하고 회원 데이터를 보관
//        sessionManager.createSession(loginMember, response);
        return "redirect:/";
    }
//    @PostMapping("/logout" )
    public String logout(HttpServletResponse response) {
        expireCookie(response, "memberId");
        return "redirect:/";
    }

//    @PostMapping("/logout")
    public String logoutV2(HttpServletRequest request) {
        sessionManager.expire(request);
        return "redirect:/";
    }

        @PostMapping("/logout")
    public String logoutV3(HttpServletRequest request) {
            HttpSession session = request.getSession(false); // 있는 session을 가져 온다.
            // true 라고 하면 session을 생성하기 때문에.
            if (session != null) {
                session.invalidate();
                // session과 안 data가 다 날라간다.
            }
            return "redirect:/";
    }


    private void expireCookie(HttpServletResponse response,String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
