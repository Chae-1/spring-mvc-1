package hello.servlet.ch04.springmvc.v2;

import hello.servlet.ch02.domain.member.Member;
import hello.servlet.ch02.domain.member.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/springmvc/v2/members")
public class SpringMemberControllerV2 {
    private MemberRepository repository = MemberRepository.getRepository();

    @RequestMapping("/new-form")
    public ModelAndView newForm() {
        System.out.println("SpringMemberFormControllerV1.process");
        return new ModelAndView("new-form");
    }

    @RequestMapping
    public ModelAndView members() {
        List<Member> members = repository.findAll();

        ModelAndView mv = new ModelAndView("members");
        mv.addObject("members", members);
        return mv;
    }

    @RequestMapping("/save")
    public ModelAndView save(HttpServletRequest request) {

        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        repository.save(member);

        ModelAndView mv = new ModelAndView("save-result");
        mv.addObject("member", member);
        return mv;
    }
}
