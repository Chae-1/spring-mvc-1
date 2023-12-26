package hello.servlet.ch02.domain.member;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;

public class MemberRepository {
    private Map<Long, Member> store = new HashMap<>();
    private static MemberRepository repository = new MemberRepository();
    private static Long sequence = 0L;

    private MemberRepository() {

    }

    public static MemberRepository getRepository() {
        return repository;
    }

    public Member save(Member member) {
        member.setId(sequence++);
        store.put(sequence, member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }



}
