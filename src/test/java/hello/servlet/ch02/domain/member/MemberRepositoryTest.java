package hello.servlet.ch02.domain.member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class MemberRepositoryTest {

    MemberRepository repository = MemberRepository.getRepository();

    @AfterEach
    void afterEach() {
        repository.clearStore();
    }

    @Test
    void save() {
        //given
        Member member = new Member();
        member.setUsername("chae");
        member.setAge(26);

        //when
        repository.save(member);

        //then
        Member findMember = repository.findById(member.getId());
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    void findAllI() {
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);

        repository.save(member1);
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);

        assertThat(result).contains(member1, member2);

    }
}