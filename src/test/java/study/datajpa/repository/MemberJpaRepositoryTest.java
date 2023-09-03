package study.datajpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * MemberJpaRepositoryTest
 * <pre>
 * Describe here
 * </pre>
 *
 * @version 1.0,
 */

@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {

    @Autowired MemberJpaRepository memberJpaRepository;

    @Test
    void testMember() throws Exception {
        // given
        String username = "memberA";
        Member member = new Member(username);

        // when
        Member findMember = memberJpaRepository.save(member);
        Member saveMember = memberJpaRepository.find(findMember.getId());

        // then
        assertEquals(findMember.getId(), saveMember.getId());
        assertEquals(saveMember.getUsername(), username);
    }

    @Test
    void basicCRUD() throws Exception {
        // given
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        memberJpaRepository.save(member1);
        memberJpaRepository.save(member2);

        // 단건 조회 검수
        Member findMember1 = memberJpaRepository.findById(member1.getId()).orElseThrow();
        Member findMember2 = memberJpaRepository.findById(member2.getId()).orElseThrow();
        assertEquals(findMember1, member1);
        assertEquals(findMember2, member2);

        // 리스트 조회 검증
        List<Member> all = memberJpaRepository.findAll();
        assertEquals(2, all.size());

        //카운트 검증
        long count = memberJpaRepository.count();
        assertEquals(2, count);

        //삭제 검증
        memberJpaRepository.delete(member1);
        memberJpaRepository.delete(member2);
        long deletedCount = memberJpaRepository.count();
        assertEquals(0, deletedCount);

    }
}
