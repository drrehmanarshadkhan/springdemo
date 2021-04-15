package skillshare.course.SpringDemo;

import skillshare.course.SpringDemo.event.TasksJson;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;


@ActiveProfiles("test")
//@RunWith(SpringRunner.class) enable it will give junit vintage initialisation error, use jupiter
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringDemoTest {

  private static final UUID getTaskUUID = UUID.fromString("61614667-d279-11e7-a5ac-f941ac8dfc39");

  @LocalServerPort
  int randomServerPort;
  @Autowired
  RestTemplate restTemplate;

  @Test
  public void testGetTaskById() throws URISyntaxException {


    TasksJson taskJson = new TasksJson(getTaskUUID,"ray","yo");
    final String baseUrl = "http://localhost:" + randomServerPort + "/users/tasks/61614667-d279-11e7-a5ac-f941ac8dfc39";
    URI uri = new URI(baseUrl);
    ResponseEntity<TasksJson> result = restTemplate.getForEntity(uri, TasksJson.class);

    Assert.assertEquals(200, result.getStatusCodeValue());
    Assert.assertEquals(true, result.hasBody());
    Assert.assertEquals(result.getBody().getOwner(),taskJson.getOwner());


    HttpHeaders httpHeaders = restTemplate.headForHeaders(baseUrl);
    assertTrue(httpHeaders.getContentType().includes(MediaType.APPLICATION_JSON));
  }
}
