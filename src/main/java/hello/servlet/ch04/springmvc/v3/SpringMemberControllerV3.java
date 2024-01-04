package hello.servlet.ch04.springmvc.v3;

import hello.servlet.ch02.domain.member.Member;
import hello.servlet.ch02.domain.member.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {
    private MemberRepository repository = MemberRepository.getRepository();

    @GetMapping("/new-form")
    public String newForm() {
        return "new-form";
    }

    @GetMapping
    public String members(Model model) {
        List<Member> members = repository.findAll();
        model.addAttribute("members", members);
        return "members";
    }

    @PostMapping("/save")
    public String save(
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model
    ) {
        Member member = new Member(username, age);
        repository.save(member);
        model.addAttribute("member", member);
        return "save-result";
    }
}
