package coinbase.cloud.java.examples.quickstart.means;

import coinbase.cloud.java.examples.quickstart.services.PriceServices;
import coinbase.cloud.java.examples.quickstart.utils.APIConfig;
import io.helidon.microprofile.cors.CrossOrigin;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

/**
 * Program to enable /coinbase endpoint.
 *
 * @author Suren K
 * @since 10-05-2021
 */
@Path("/coinbase/price")
@RequestScoped
public class PriceMeans {
    private APIConfig apiConfig;
    private PriceServices priceServices = new PriceServices();
    private static final Logger LOG = Logger.getLogger(PriceMeans.class.getName());

    /**
     *
     * @param apiConfig
     */
    @Inject
    public PriceMeans(APIConfig apiConfig) {
        this.apiConfig = apiConfig;
    }

    /**
     * To enable localhost and specific servers
     */
    @OPTIONS
    @Path("/buy-price")
    @CrossOrigin(value = {"http://localhost:9393"},
            allowMethods = {HttpMethod.GET})
    public void buyPriceOptions() {
    }

    /**
     * Return Buy Price from Service
     * @return Price information from Coinbase
     */
    @Path("/buy-price")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getBuyPrice() {
        return priceServices.getBuyPrice(apiConfig.getBaseAccessUrl());
    }

    @OPTIONS
    @Path("/sell-price")
    @CrossOrigin(value = {"http://localhost:9393"},
            allowMethods = {HttpMethod.GET})
    public void sellPriceOptions() {
    }

    /**
     * Return Sell Price from Service
     * @return
     */
    @Path("/sell-price")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSellPrice() {
        return priceServices.getSellPrice(apiConfig.getBaseAccessUrl());
    }

    @OPTIONS
    @Path("/spot-price")
    @CrossOrigin(value = {"http://localhost:9393"},
            allowMethods = {HttpMethod.GET})
    public void spotPriceOptions() {
    }

    /**
     * Return Spot Price from Service
     * @return
     */
    @Path("/spot-price")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSpotPrice() {
        return priceServices.getSpotPrice(apiConfig.getBaseAccessUrl());
    }
}