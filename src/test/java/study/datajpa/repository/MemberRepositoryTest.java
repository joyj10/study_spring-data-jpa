package study.datajpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * MemberRepositoryTest
 * <pre>
 * Describe here
 * </pre>
 *
 * @version 1.0,
 */

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void testMember() {
        // given
        String username = "memberA";
        Member member = new Member(username);

        // when
        Member findMember = memberRepository.save(member);
        Member saveMember = memberRepository.findById(findMember.getId()).orElseThrow();

        // then
        assertEquals(findMember.getId(), saveMember.getId());
        assertEquals(saveMember.getUsername(), username);
    }

    @Test
    void findByUsernameAndAgeGreaterThan() {
        // given
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        // when
        List<Member> result = memberRepository.findByUsernameAndAgeGreaterThan("AAA", 15);

        // then
        assertEquals("AAA", result.get(0).getUsername());
        assertEquals(20, result.get(0).getAge());
        assertEquals(1, result.size());
    }

    @Test
    void testQuery() {
        // given
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        // when
        List<Member> result = memberRepository.findUser("AAA", 10);

        // then
        assertEquals("AAA", result.get(0).getUsername());
        assertEquals(10, result.get(0).getAge());
        assertEquals(1, result.size());
    }

}
