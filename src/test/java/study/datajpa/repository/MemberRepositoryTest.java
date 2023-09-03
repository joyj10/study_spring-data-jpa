package study.datajpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;

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
    void testMember() throws Exception {
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

}
