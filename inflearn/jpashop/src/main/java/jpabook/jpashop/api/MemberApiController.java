package jpabook.jpashop.api;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

//@Controller @ResponseBody
@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @GetMapping("/api/v1/members")
    public List<Member> membersV1(){
        return memberService.findMembers();
    }

    @GetMapping("/api/v2/members")
    public Result membersV2(){
        List<Member> findMembers = memberService.findMembers();
        List<MemberDto> collect = findMembers.stream()
                .map(m -> new MemberDto(m.getName()))
                .collect(Collectors.toList());


        return new Result(collect.size(),collect);
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String name;
    }
    @Data
    @AllArgsConstructor
    static class Result<T>{
        private int count;
        private T data;
    }
    // @Valid가 Member entity에 붙어있는   validation 검증함.
    @PostMapping("/api/v1/members") // 회원 등록
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {
        Member member = new Member();
        member.setName(request.getName());

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateMemberRequest request) {
        // command랑 쿼리를 분리하는 스타일로 개발함.
        memberService.update(id, request.getName()); //커멘드
        Member findMember = memberService.findOne(id); // 쿼리
        return new UpdateMemberResponse(findMember.getId(), findMember.getName());
    }

    // 아래 클래스 들은 모두 DTO
    @Data
    @AllArgsConstructor
    static class UpdateMemberRequest {
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse {
        private Long id;
        private String name;
    }

    @Data
     // dto 객체
     static class CreateMemberRequest {
        @NotEmpty
        private String name;
    }

    // 응답 값
    @Data
    static class CreateMemberResponse {
        private Long id;
        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }

}
