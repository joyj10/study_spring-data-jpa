package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.datajpa.entity.Member;

/**
 * MemberRepository
 * <pre>
 * Describe here
 * </pre>
 *
 * @version 1.0,
 */

public interface MemberRepository extends JpaRepository<Member, Long> {

}
