import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Tests extends BaseTest {
    @Test
    public void checkFilterByQueryParametersWithoutSerialization() {
        when().
                get("?userId={id}", 5).
                then().
                statusCode(200).
                body("id[1]", equalTo(42));
    }

    @Test
    public void checkGetListOfAllResourcesSimple() {
        Response response = when().get();

        List<Post> returnedArtworks = Arrays.asList(response.getBody().as(Post[].class));

        assertThat(returnedArtworks.size(), greaterThanOrEqualTo(100));
    }

    @Test
    public void checkFilterByQueryParametersSimpleWithRandomUserId() {
        //assuming that every user must have at least one post
        int randomUserId = (int)(Math.random()*((10-1)+1))+1;

        Response response = when().get("?userId={id}", randomUserId);

        List<Post> returnedArtworks = Arrays.asList(response.getBody().as(Post[].class));

        assertThat(returnedArtworks.size(), greaterThanOrEqualTo(1));
    }

    @Test
    public void checkGetResourceByRandomIdSimple() {
        //assuming that every post's id is unique
        int randomPostId = (int)(Math.random()*((100-1)+1))+1;

        Response response = when().get("?id={id}", randomPostId);

        List<Post> returnedArtworks = Arrays.asList(response.getBody().as(Post[].class));

        assertThat(returnedArtworks.size(), equalTo(1));
    }
}