package com.liftdevelops.homeitems.item;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liftdevelops.homeitems.TestDataUtil;
import com.liftdevelops.homeitems.storage.ContainerEntity;
import com.liftdevelops.homeitems.storage.ContainerRepository;
import com.liftdevelops.homeitems.user.model.UserEntity;
import com.liftdevelops.homeitems.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
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
@Transactional
class ItemControllerIntegrationTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;
    private final ContainerRepository containerRepository;

    private UserEntity testUSer;
    private ContainerEntity testContainer;

    @Autowired
    public ItemControllerIntegrationTest(MockMvc mockMvc, ObjectMapper objectMapper, UserRepository userRepository, ContainerRepository containerRepository) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.userRepository = userRepository;
        this.containerRepository = containerRepository;
    }

    @BeforeEach
    void setUpTestData() {
        userRepository.deleteAll();
        containerRepository.deleteAll();

        testContainer = createTestContainer();
        testUSer = createTestUser();
    }

    private UserEntity createTestUser() {
        UserEntity userEntity = TestDataUtil.createTestUserEntityA();
        return userRepository.save(userEntity);
    }

    private ContainerEntity createTestContainer() {
        ContainerEntity containerEntity = TestDataUtil.createTestContainerA();
        return containerRepository.save(containerEntity);
    }

    //TODO fix
    @Test
    void testThatCreateItemReturnsHttpStatus201Created() throws Exception {

        ItemDto itemDto = TestDataUtil.createTestItemDtoA();
        itemDto.setOwnerId(testUSer.getId());
        itemDto.setId(null);
        itemDto.setContainerId(testContainer.getId());

        String itemJson = objectMapper.writeValueAsString(itemDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(itemJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(itemDto.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(itemDto.getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber());
    }

    @Test
    void testThatGetItemsReturnsHttpStatus200Ok() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/items")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testThatGetItemByIdReturnsItemWhenExists() throws Exception {

        ItemDto itemDto = TestDataUtil.createTestItemDtoA();
        itemDto.setOwnerId(testUSer.getId());

        String itemJson = objectMapper.writeValueAsString(itemDto);
        String createResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(itemJson))
                .andReturn()
                .getResponse()
                .getContentAsString();

        ItemDto createdItem = objectMapper.readValue(createResult, ItemDto.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/items/" + createdItem.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(createdItem.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(itemDto.getName()));
    }

    @Test
    void testThatGetItemByIdReturnsNotFoundWhenItemDoesNotExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/items/99999")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    //TODO fix
    @Test
    void testThatUpdateItemReturnsUpdatedItem() throws Exception {
        ItemDto itemDto = TestDataUtil.createTestItemDtoA();

        String itemJson = objectMapper.writeValueAsString(itemDto);
        String createResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(itemJson))
                .andReturn()
                .getResponse()
                .getContentAsString();

        ItemDto createdItem = objectMapper.readValue(createResult, ItemDto.class);

        createdItem.setName("Updated Name");
        createdItem.setDescription("Updated Description");

        String updatedItemJson = objectMapper.writeValueAsString(createdItem);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/items/" + createdItem.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedItemJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(createdItem.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Updated Name"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Updated Description"));
    }

    @Test
    void testThatDeleteItemReturnsOkStatus() throws Exception {
        ItemDto itemDto = TestDataUtil.createTestItemDtoA();

        String itemJson = objectMapper.writeValueAsString(itemDto);
        String createResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(itemJson))
                .andReturn()
                .getResponse()
                .getContentAsString();

        ItemDto createdItem = objectMapper.readValue(createResult, ItemDto.class);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/items/" + createdItem.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}