package skillshare.course.SpringDemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.UUID;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void taskGet(){
        webTestClient.get().uri("/users/tasks/61614667-d279-11e7-a5ac-f941ac8dfc39").exchange().expectStatus().isOk();
        webTestClient.get().uri("/users/tasks/61614667-d279-11e7-a5ac-f941ac8dfc39").exchange().expectBody();
    }


}
