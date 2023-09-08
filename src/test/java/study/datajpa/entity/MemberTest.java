package study.datajpa.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.repository.MemberRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * MemberTest
 * <pre>
 * Describe here
 * </pre>
 *
 * @version 1.0,
 */

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void testEntity() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        // 강제로 DB insert 쿼리 날림
        em.flush();

        // 영속성 컨텍스트 캐시 날림
        em.clear();

        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        for (Member member : members) {
            System.out.println("member = " + member);
            System.out.println("-> member.team = " + member.getTeam());
        }

        assertEquals("teamA", members.get(0).getTeam().getName());
    }

    @Test
    void jpaEventBaseEntity() throws InterruptedException {
        // given
        Member member = new Member("memeber1");
        memberRepository.save(member); // @PrePersist

        Thread.sleep(100);
        member.setUsername("member2");

        em.flush(); // @PreUpdate
        em.clear();

        // when
        Member findMember = memberRepository.findById(member.getId()).orElseThrow();

        // then
        System.out.println("### findMember = " + findMember);
        System.out.println("### findMember = " + findMember.getCreatedDate());
        System.out.println("### findMember = " + findMember.getLastModifiedDate());
        System.out.println("### findMember = " + findMember.getCreatedBy());
        System.out.println("### findMember = " + findMember.getLastModifiedBy());

        assertEquals("member2", findMember.getUsername());
    }

}
