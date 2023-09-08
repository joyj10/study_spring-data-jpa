package study.datajpa.repository;

import study.datajpa.entity.Member;

import java.util.List;

/**
 * MemberRepositoryCustom
 * <pre>
 * Describe here
 * </pre>
 *
 * @version 1.0,
 */
public interface MemberRepositoryCustom {
    List<Member> findMemberCustom();
}
