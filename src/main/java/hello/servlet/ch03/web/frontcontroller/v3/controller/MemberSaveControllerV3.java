package hello.servlet.ch03.web.frontcontroller.v3.controller;

import hello.servlet.ch02.domain.member.Member;
import hello.servlet.ch02.domain.member.MemberRepository;
import hello.servlet.ch03.web.frontcontroller.ModelView;
import hello.servlet.ch03.web.frontcontroller.v3.ControllerV3;
import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository repository = MemberRepository.getRepository();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        repository.save(member);

        ModelView modelView = new ModelView("save-result");
        modelView.getModel().put("member", member);
        return modelView;
    }
}
