package coinbase.cloud.java.examples.quickstart.means;

import coinbase.cloud.java.examples.quickstart.services.AuthenticationServices;
import coinbase.cloud.java.examples.quickstart.utils.APIConfig;
import io.helidon.microprofile.cors.CrossOrigin;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

/**
 * Program to enable /coinbase endpoint.
 *
 * @author Suren K
 * @since 10-05-2021
 */
@Path("/coinbase/oauth")
@RequestScoped
public class OauthMeans {
    private APIConfig apiConfig;
    private AuthenticationServices authenticationServices = new AuthenticationServices();
    private static final Logger LOG = Logger.getLogger(OauthMeans.class.getName());

    /**
     *
     * @param apiConfig
     */
    @Inject
    public OauthMeans(APIConfig apiConfig) {
        this.apiConfig = apiConfig;
    }

    /**
     * To enable localhost and specific servers
     */
    @OPTIONS
    @Path("/callback")
    @CrossOrigin(value = {"http://localhost:9393"},
            allowMethods = {HttpMethod.GET})
    public void oauthOptions() {
    }

    /**
     * Capture "code" from the redirected page.
     * @return Code
     */
    @Path("/callback")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCode(@QueryParam("code") String code, @QueryParam("state") String state) {
        LOG.info("OauthMeans.getCode() {}, code=" + code + ", state=" + state);

        return code;
        //return authenticationServices.serviceCode(apiConfig.getOauthBaseAccessUrl());
    }

    /**
     * To enable localhost and specific servers
     */
    @OPTIONS
    @Path("/token")
    @CrossOrigin(value = {"http://localhost:9393"},
            allowMethods = {HttpMethod.POST})
    public void oauthTokenOptions() {
    }

    /**
     * Capture "code" from the redirected page.
     * @return Token
     */
    @Path("/token")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getToken() {
        LOG.info("OauthMeans.getToken() {}");

        return authenticationServices.serviceToken(apiConfig.getOauthTokenUrl(), apiConfig.getClientId(), apiConfig.getClientSecret());
    }
}
