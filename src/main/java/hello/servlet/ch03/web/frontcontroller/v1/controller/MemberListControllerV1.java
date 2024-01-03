package hello.servlet.ch03.web.frontcontroller.v1.controller;

import hello.servlet.ch02.domain.member.Member;
import hello.servlet.ch02.domain.member.MemberRepository;
import hello.servlet.ch03.web.frontcontroller.v1.ControllerV1;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class MemberListControllerV1 implements ControllerV1 {

    private MemberRepository repository = MemberRepository.getRepository();
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Member> members = repository.findAll();

        request.setAttribute("members", members);

        String viewPath = "/WEB-INF/views/members.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
