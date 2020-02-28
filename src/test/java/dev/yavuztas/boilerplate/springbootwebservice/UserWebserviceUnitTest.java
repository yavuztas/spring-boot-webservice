package dev.yavuztas.boilerplate.springbootwebservice;

import dev.yavuztas.boilerplate.springbootwebservice.controller.UserWebserviceController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit tests for our {@link UserWebserviceController}
 * It uses "dev" profile as default for testing the same data in-memory with H2 Database in order to remove the hassle of maintaining a running Mysql database
 * Also, it should perfectly run on Mysql if the profile is changed to "prod". Just make sure your schema does not have any tables already defined since it is created automatically.
 * Everytime you need to drop them manually otherwise tests may not work.
 *
 * @author Yavuz Tas
 */
@ActiveProfiles("dev")
@Import(UserWebserviceUnitTestConfig.class)
@WebMvcTest(controllers = UserWebserviceController.class)
class UserWebserviceUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenUsernameGiven_userItemsRequestShouldWork() throws Exception {
        String username = "test";
        mockMvc.perform(get("/services/user/" + username))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json("{\"username\":\"test\",\"item\":[{\"name\":\"item1\",\"game\":\"game1\",\"expirationDate\":\"2012-08-12\",\"quantity\":3,\"property\":[{\"name\":\"name1\",\"value\":\"value1\"}]}]}"));
    }

}
