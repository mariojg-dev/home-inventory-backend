package com.liftdevelops.homeitems.item;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liftdevelops.homeitems.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class ItemControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public ItemControllerIntegrationTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void testThatCreateBookReturnsHttpStatus201Created() throws Exception {
        ItemEntity itemEntity = TestDataUtil.createTestItemA();
        itemEntity.setId(null);
        //TODO complete IntegrationTests
        String itemJson = objectMapper.writeValueAsString(itemEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/items")
        );
    }



}
