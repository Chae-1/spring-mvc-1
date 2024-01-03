package hello.servlet.ch03.web.frontcontroller.v4.controller;

import hello.servlet.ch02.domain.member.Member;
import hello.servlet.ch02.domain.member.MemberRepository;
import hello.servlet.ch03.web.frontcontroller.v4.ControllerV4;
import java.util.List;
import java.util.Map;

public class MemberListControllerV4 implements ControllerV4 {
    private MemberRepository repository = MemberRepository.getRepository();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        List<Member> members = repository.findAll();
        model.put("members", members);
        return "members";
    }
}
