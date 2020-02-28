package dev.yavuztas.boilerplate.springbootwebservice;

import dev.yavuztas.boilerplate.springbootwebservice.domain.Item;
import dev.yavuztas.boilerplate.springbootwebservice.domain.Property;
import dev.yavuztas.boilerplate.springbootwebservice.exception.UsernameNotFoundException;
import dev.yavuztas.boilerplate.springbootwebservice.service.IUserItemService;
import dev.yavuztas.boilerplate.springbootwebservice.service.UserItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration test for our {@link UserItemService}
 * It uses "dev" profile as default for testing the same data in-memory with H2 Database in order to remove the hassle of maintaining a running Mysql database
 * Also, it should perfectly run on Mysql if the profile is changed to "prod". Just make sure your schema does not have any tables already defined since it is created automatically.
 * Everytime you need to drop them manually otherwise tests may not work.
 *
 * @author Yavuz Tas
 */
@ActiveProfiles("dev")
@SpringBootTest
class UserItemServiceIntegrationTest {

    @Autowired
    private IUserItemService userItemService;

    @Test
    void whenNullUsernameGiven_ThrowsException() {
        assertThrows(UsernameNotFoundException.class, () -> userItemService.getUserItems(null));
    }

    @Test
    void whenEmptyUsernameWithNoSpacesGiven_ThrowsException() {
        assertThrows(UsernameNotFoundException.class, () -> userItemService.getUserItems(""));
    }

    @Test
    void whenEmptyUsernameWithSpacesGiven_ThrowsException() {
        assertThrows(UsernameNotFoundException.class, () -> userItemService.getUserItems(" "));
    }

    @Test
    void whenNonExistingUsernameGiven_ThrowsException() {
        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> userItemService.getUserItems("unknown"));

        assertEquals(exception.getUsername(), "unknown");
    }

    @Test
    void whenExistingUsernameGiven_UserItemShouldReturn() {
        List<Item> userItems = userItemService.getUserItems("bob");
        assertEquals(2, userItems.size());
    }


    @Test
    void whenUsernameCeciliaGiven_UserItemsShouldReturn() {
        List<Item> userItems = userItemService.getUserItems("bob");

        Item item1 = userItems.get(0);

        assertNotNull(item1);
        assertEquals(1, item1.getId());
        assertEquals("item1", item1.getName());
        assertEquals("game1", item1.getGame());
        assertEquals(LocalDate.parse("2012-08-12"), item1.getExpirationDate());
        assertEquals(1, item1.getQuantity());

        Item item2 = userItems.get(1);

        assertNotNull(item2);
        assertEquals(2, item2.getId());
        assertEquals("item2", item2.getName());
        assertEquals("game2", item2.getGame());
        assertEquals(LocalDate.parse("2012-08-29"), item2.getExpirationDate());
        assertEquals(2, item2.getQuantity());

    }

    @Test
    void whenUsernameCeciliaGiven_UserItemPropertiesShouldReturn() {
        List<Item> userItems = userItemService.getUserItems("bob");

        Item item1 = userItems.get(0);
        Item item2 = userItems.get(1);

        assertEquals(2, item1.getProperties().size());
        assertEquals(1, item2.getProperties().size());

        Iterator<Property> itemPropertiesIterator = item1.getProperties().iterator();
        Property property1 = itemPropertiesIterator.next();
        assertNotNull(property1);
        assertEquals(1, property1.getId());
        assertEquals("name1", property1.getName());
        assertEquals("value1", property1.getValue());

        Property property2 = itemPropertiesIterator.next();
        assertNotNull(property2);
        assertEquals(2, property2.getId());
        assertEquals("name2", property2.getName());
        assertEquals("value2", property2.getValue());

        itemPropertiesIterator = item2.getProperties().iterator();
        Property property3 = itemPropertiesIterator.next();
        assertNotNull(property3);
        assertEquals(3, property3.getId());
        assertEquals("name3", property3.getName());
        assertEquals("value3", property3.getValue());
    }

    @Test
    void whenUsernameAnaGiven_UserItemsShouldReturn() {
        List<Item> userItems = userItemService.getUserItems("john");

        assertEquals(1, userItems.size());

        Item item = userItems.get(0);

        assertNotNull(item);
        assertEquals(3, item.getId());
        assertEquals("item3", item.getName());
        assertEquals("game1", item.getGame());
        assertEquals(LocalDate.parse("2012-08-20"), item.getExpirationDate());
        assertEquals(3, item.getQuantity());

    }

    @Test
    void whenUsernameAnaGiven_UserItemPropertiesShouldReturnEmpty() {
        List<Item> userItems = userItemService.getUserItems("john");

        assertEquals(1, userItems.size());

        Item item = userItems.get(0);

        assertNotNull(item);
        assertEquals(3, item.getId());
        assertEquals("item3", item.getName());
        assertEquals("game1", item.getGame());
        assertEquals(LocalDate.parse("2012-08-20"), item.getExpirationDate());
        assertEquals(3, item.getQuantity());

        assertEquals(0, item.getProperties().size());

    }

}
