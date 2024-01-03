package hello.servlet.ch03.web.frontcontroller.v4.controller;

import hello.servlet.ch02.domain.member.Member;
import hello.servlet.ch02.domain.member.MemberRepository;
import hello.servlet.ch03.web.frontcontroller.v4.ControllerV4;
import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4 {
    private MemberRepository repository = MemberRepository.getRepository();
    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        // 1. param 정보 조회
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        // 2. member 정보 save
        Member member = new Member(username, age);
        repository.save(member);

        // 3. model로 넘겨준다.
        model.put("member", member);
        return "save-result";
    }
}
