package restAssuredWithWireMock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "restAssuredWithWireMock",
        plugin = {"pretty","html:target/cucumber-reports"}
)
public class Runner {

    private static final int PORT=8080;
    private static final String HOST="localhost";
    private static WireMockServer server= new WireMockServer(PORT);

    @BeforeClass
    public static void setup(){
        server.start();
        WireMock.configureFor(HOST,PORT);
        WireMock.stubFor(
                WireMock.get("/find/all/books")
                .willReturn(aResponse()
                .withHeader("Content-Type","application/json")
                .withStatus(200)
                .withBodyFile("listOfBooks.json"))
        );
        WireMock.stubFor(
                WireMock.get("/get/authorized/user")
                        .willReturn(aResponse()
                                .withHeader("Content-Type","application/json")
                                .withStatus(200)
                                .withStatusMessage("OK"))
        );
        WireMock.stubFor(
                WireMock.get("/get/oneBook/1")
                        .willReturn(aResponse()
                                .withHeader("Content-Type","application/json")
                                .withStatus(200)
                        .withBodyFile("oneBook.json"))
        );
        WireMock.stubFor(
                WireMock.get("/get/oneBook/withQueryParam?author=Alan")
                        .willReturn(aResponse()
                                .withHeader("Content-Type","application/json")
                                .withStatus(200)
                                .withBodyFile("oneBook.json"))
        );

        WireMock.stubFor(
                WireMock.post("/addBook")
                        .willReturn(aResponse()
                                .withHeader("Content-Type","application/json")
                                .withStatus(201)
                                .withStatusMessage("CREATED"))
        );

        WireMock.stubFor(
                WireMock.put("/updateBook")
                        .willReturn(aResponse()
                                .withHeader("Content-Type","application/json")
                                .withStatus(200)
                                .withStatusMessage("OK"))
        );

        WireMock.stubFor(
                WireMock.delete("/deleteBook")
                        .willReturn(aResponse()
                                .withHeader("Content-Type","application/json")
                                .withStatus(200)
                                .withStatusMessage("OK"))
        );
    }
    @AfterClass
    public static void shutDown(){
        if(null!=server && server.isRunning()){
            server.shutdown();
        }
    }
}
