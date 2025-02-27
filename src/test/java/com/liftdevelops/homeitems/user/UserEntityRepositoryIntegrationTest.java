package com.liftdevelops.homeitems.user;

import com.liftdevelops.homeitems.TestDataUtil;
import com.liftdevelops.homeitems.user.model.UserEntity;
import com.liftdevelops.homeitems.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.wavefront.WavefrontProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = WavefrontProperties.Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
class UserEntityRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    public UserEntityRepositoryIntegrationTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Test
    void testThatUserCanBeCreatedAndRetrieved() {
        UserEntity userEntity = TestDataUtil.createTestUserEntityA();
        userRepository.save(userEntity);
    }




}
