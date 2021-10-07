package coinbase.cloud.java.examples.quickstart.means;

import coinbase.cloud.java.examples.quickstart.services.UserServices;
import coinbase.cloud.java.examples.quickstart.utils.APIConfig;
import io.helidon.microprofile.cors.CrossOrigin;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

/**
 * Program to enable /coinbase/user endpoint.
 *
 * @author Suren K
 * @since 10-05-2021
 */
@Path("/coinbase/user")
@RequestScoped
public class UserMeans {
    private APIConfig apiConfig;
    private UserServices userServices = new UserServices();
    private static final Logger LOG = Logger.getLogger(UserMeans.class.getName());

    /**
     *
     * @param apiConfig
     */
    @Inject
    public UserMeans(APIConfig apiConfig) {
        this.apiConfig = apiConfig;
    }

    /**
     * To enable localhost and specific servers
     */
    @OPTIONS
    @Path("/show-a-user")
    @CrossOrigin(value = {"http://localhost:9393"},
            allowMethods = {HttpMethod.GET})
    public void showAUserOptions() {
    }

    /**
     * Return User info from Service
     * @return User information from Coinbase
     */
    @Path("/show-a-user")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getShowAUser() {
        return userServices.serviceShowAUser(apiConfig.getBaseAccessUrl());
    }
}
