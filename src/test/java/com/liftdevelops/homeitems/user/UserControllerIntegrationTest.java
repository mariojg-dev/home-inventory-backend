package com.liftdevelops.homeitems.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liftdevelops.homeitems.TestDataUtil;
import com.liftdevelops.homeitems.user.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testThatCreateUserSuccessfullyThenReturnsHttp201Created() throws Exception {
        UserDto userDto = TestDataUtil.createTestUserDtoA();
        String userJson = objectMapper.writeValueAsString(userDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void testThatCreateUserSuccessfullyThenReturnsSavedUser() throws Exception {
        UserDto userDto = TestDataUtil.createTestUserDtoA();
        String userJson = objectMapper.writeValueAsString(userDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("TestUser"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("email@gmail.com"));
    }
}
