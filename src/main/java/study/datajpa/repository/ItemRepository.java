package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.datajpa.entity.Item;

/**
 * ItemRepository
 * <pre>
 * Describe here
 * </pre>
 *
 * @version 1.0,
 */
public interface ItemRepository extends JpaRepository<Item, Long> {
}
