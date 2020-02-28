package dev.yavuztas.boilerplate.springbootwebservice;

import dev.yavuztas.boilerplate.springbootwebservice.domain.Item;
import dev.yavuztas.boilerplate.springbootwebservice.domain.Property;
import dev.yavuztas.boilerplate.springbootwebservice.repository.UserRepository;
import dev.yavuztas.boilerplate.springbootwebservice.service.UserItemService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@TestConfiguration
public class UserWebserviceUnitTestConfig {

    @Bean
    public UserItemService userItemService() {

        // some mock data
        List<Item> mockItems = new ArrayList<>();

        Item item1 = new Item();
        item1.setId(1L);
        item1.setName("item1");
        item1.setGame("game1");
        item1.setExpirationDate(LocalDate.parse("2012-08-12"));
        item1.setQuantity(3L);

        Property property1 = new Property();
        property1.setId(2L);
        property1.setName("name1");
        property1.setValue("value1");
        item1.getProperties().add(property1);
        mockItems.add(item1);

        UserItemService userItemService = Mockito.mock(UserItemService.class);
        Mockito.when(userItemService.getUserItems(any())).thenReturn(mockItems);
        return userItemService;
    }

    @Bean
    public UserRepository userRepository() {
        return Mockito.mock(UserRepository.class);
    }

}
