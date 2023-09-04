package study.datajpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    @Autowired MemberRepository memberRepository;
    @Autowired TeamRepository teamRepository;

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

    @Test
    void findUsernameList() {
        // given
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        // when
        List<String> result = memberRepository.findUsernameList();

        // then
        assertEquals("AAA", result.get(0));
        assertEquals(2, result.size());
    }

    @Test
    void findMemberDto() {
        // given
        Team team = new Team("teamA");
        teamRepository.save(team);

        Member m1 = new Member("AAA", 10);
        m1.setTeam(team);
        memberRepository.save(m1);

        // when
        List<MemberDto> result = memberRepository.findMemberDto();

        // then
        for (MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
            assertEquals("teamA", memberDto.getTeamName());
        }
    }

    @Test
    void findByNames() {
        // given
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        // when
        List<Member> result = memberRepository.findByNames(Arrays.asList("AAA", "BBB"));

        // then
        for (Member member : result) {
            System.out.println("member = " + member);
        }

        assertEquals("AAA", result.get(0).getUsername());
        assertEquals(2, result.size());
    }

    @Test
    void returnType() {
        // given
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        // when
        List<Member> result1 = memberRepository.findListByUsername("AAA");
        Member result2 = memberRepository.findMemberByUsername("AAA");
        Optional<Member> result3 = memberRepository.findOptionalByUsername("AAA");

        // then
        assertEquals("AAA", result1.get(0).getUsername());
        assertEquals("AAA", result2.getUsername());
        assertEquals("AAA", result3.orElseThrow().getUsername());
    }

}
