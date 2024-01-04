package hello.servlet.ch04.springmvc.v1;

import hello.servlet.ch02.domain.member.Member;
import hello.servlet.ch02.domain.member.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SpringMemberListControllerV1 {

    private MemberRepository repository = MemberRepository.getRepository();

    @RequestMapping("/springmvc/v1/members")
    public ModelAndView process() {
        List<Member> members = repository.findAll();

        ModelAndView mv = new ModelAndView("members");
        mv.addObject("members", members);
        return mv;
    }
}
