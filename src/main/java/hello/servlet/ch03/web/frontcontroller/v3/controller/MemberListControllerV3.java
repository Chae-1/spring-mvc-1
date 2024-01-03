package hello.servlet.ch03.web.frontcontroller.v3.controller;

import hello.servlet.ch02.domain.member.Member;
import hello.servlet.ch02.domain.member.MemberRepository;
import hello.servlet.ch03.web.frontcontroller.ModelView;
import hello.servlet.ch03.web.frontcontroller.v3.ControllerV3;
import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {

    private MemberRepository repository = MemberRepository.getRepository();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        List<Member> members = repository.findAll();

        ModelView mv = new ModelView("members");
        mv.getModel().put("members", members);
        return mv;
    }
}
