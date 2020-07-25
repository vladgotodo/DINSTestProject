import io.restassured.RestAssured;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    Logger logger = LoggerFactory.getLogger(BaseTest.class);
    private static final String baseURI = "https://jsonplaceholder.typicode.com";
    private static final String basePath = "/posts";

    @BeforeClass
    public void setup() {
        BasicConfigurator.configure();
        logger.info("Logging started");
        logger.info("Setting RestAssured baseURI");
        RestAssured.baseURI = baseURI;
        logger.info("Setting RestAssured basePath");
        RestAssured.basePath = basePath;
    }
}