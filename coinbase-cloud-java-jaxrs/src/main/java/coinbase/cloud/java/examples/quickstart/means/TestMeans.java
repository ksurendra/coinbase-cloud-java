package coinbase.cloud.java.examples.quickstart.means;

import coinbase.cloud.java.examples.quickstart.services.TestServices;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Program to enable /coinbase endpoint.
 *
 * @author Suren K
 * @since 10-05-2021
 */
@Path("/coinbase")
@RequestScoped
public class TestMeans {
    private TestServices testServices = new TestServices();

    /**
     * Sample endpoint to test primary endpoint
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getDefaultMessage() {
        return testServices.getTestServiceMessage();
    }
}
