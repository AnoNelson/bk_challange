package com.challenge.challenge;

import com.challenge.challenge.controller.MainController;
import com.challenge.challenge.controller.PostController;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SmokeTest {
    @Autowired
    private PostController mainController;

    @Test
    void contextLoads() {
        assertThat(mainController).isNotNull();
    }

}
