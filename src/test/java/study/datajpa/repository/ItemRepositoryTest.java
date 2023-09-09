package study.datajpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.datajpa.entity.Item;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ItemRepositoryTest
 * <pre>
 * Describe here
 * </pre>
 *
 * @version 1.0,
 */

@SpringBootTest
class ItemRepositoryTest {

    @Autowired ItemRepository itemRepository;

    @Test
    void save() {
        // given
        Item item = new Item("A");
        Item saveItem = itemRepository.save(item);

        // when then
        System.out.println("### saveItem = " + saveItem.getId());
        assertNotNull(saveItem.getId());
    }

}
