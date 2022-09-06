package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberContorller {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    // Member를 바로쓰지 왜 MemberForm을 만드냐 : validation을 넣으면 지저분해지고, controller에서 넘어오는 validation이랑
    // domain이 원하는 validation이 다를 수 있다. entity를 form data로 쓰기 시작하면 안맞다. 실수에서는 그렇게 단순한 form이 거의 없다.

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "members/createMemberForm";
        }
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        // 단순하니깐 entity를 그대로 뿌린것이고
        // 실무적으로 복잡해지면 dto로 변환해서 화면에 필요한 데이터들만 가지고 출력하는것을 권장함.
        // 여기서는 entity를 손안댈것이 없고 심플하게 화면에 출력할수 있는 상황이기 때문에 사용한것.
        // template engine에서 렌더링할때는 서버안에서 기능이 돌기 때문에 member 엔티티를 화면 템플릿에 전달해도 괜찮다.
        // 하지만 api를 만들때에는 이유를 불문하고 절대 entity를 외부로 반환하면 안된다.
        // 1. 엔티티에 필드 추가하면 필드가 그대로 노출됨 2. api 스펙이 변해버림.
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
