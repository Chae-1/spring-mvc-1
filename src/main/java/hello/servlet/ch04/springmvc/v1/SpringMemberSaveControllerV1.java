package hello.servlet.ch04.springmvc.v1;

import hello.servlet.ch02.domain.member.Member;
import hello.servlet.ch02.domain.member.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SpringMemberSaveControllerV1 {

    private MemberRepository repository = MemberRepository.getRepository();
    @RequestMapping("/springmvc/v1/members/save")
    public ModelAndView process(HttpServletRequest request) {

        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        repository.save(member);

        ModelAndView mv = new ModelAndView("save-result");
        mv.addObject("member", member);
        return mv;
    }
}
