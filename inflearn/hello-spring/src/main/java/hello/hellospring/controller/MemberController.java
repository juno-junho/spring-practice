package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

//    private final MemberService memberService = new MemberService();
    // memberservice를 가져다 써야하는데 new로 하면 이렇게 쓰면 memberController 말고 여러 controller 들이
    // 가져다 쓸 수 있음. 그런데 별 기능이 없다. 하나만 생성해놓고 공용으로 쓰면 된다
    // -> spring container에 등록을 해놓고 쓰면된다.
    private final MemberService memberService;

    // membercontroller가 생성될때 등록되어 있는 객체를 spring이 넣어준다. dependency injection
    @Autowired // spring container에 있는 memberService랑 연결시켜준다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
