package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() throws Exception {
        // given
        Member member = new Member();
        member.setName("kim");
        repository.save(member);

        // when
        Member result = repository.findById(member.getId()).get();

        // then
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("kim");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("kwon");
        repository.save(member2);

        // when
        Member result = repository.findByName("kwon").get();

        // then
        assertThat(result).isEqualTo(member2);
    }

    @Test
    public void findAll() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("kim");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("kwon");
        repository.save(member2);

        // when
        List<Member> result = repository.findAll();

        // then
        assertThat(result.size()).isEqualTo(2);
    }

}