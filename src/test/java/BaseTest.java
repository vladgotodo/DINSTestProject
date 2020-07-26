import io.restassured.RestAssured;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;

import static io.restassured.RestAssured.given;

public class BaseTest {
    Logger logger = LoggerFactory.getLogger(BaseTest.class);
    private static final String baseURI = System.getProperty("base.uri");//"https://jsonplaceholder.typicode.com";
    private static final String basePath = System.getProperty("base.path");//"/posts";

    @BeforeSuite
    public void setup() {
        BasicConfigurator.configure();
        logger.info("Logging started");
        logger.info("Setting RestAssured baseURI");
        RestAssured.baseURI = baseURI;
        logger.info("Setting RestAssured basePath");
        RestAssured.basePath = basePath;

        given()
                .log().ifValidationFails()
                .when()
                .get()
                .then()
                .statusCode(200).log().ifError();
    }
}
