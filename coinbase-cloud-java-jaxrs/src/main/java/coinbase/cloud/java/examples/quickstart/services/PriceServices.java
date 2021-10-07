package coinbase.cloud.java.examples.quickstart.services;

import coinbase.cloud.java.examples.quickstart.models.Price;
import coinbase.cloud.java.examples.quickstart.utils.RequestError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

/**
 * Program to provide "price" services.
 *
 * @author Suren K
 * @since 10-05-2021
 */
@ApplicationScoped
public class PriceServices {
    Client client = ClientBuilder.newClient();
    private static final Gson gsonObj = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
    private static final Logger LOG = Logger.getLogger(PriceServices.class.getName());

    /**
     * Service to fetch buy price.
     * API Used: https://api.coinbase.com/v2/prices/:currency_pair/buy
     * Docs:https://docs.cloud.coinbase.com/sign-in-with-coinbase/docs/api-prices#get-buy-price
     *
     * @return Prices from Coinbase API
     */
    public String getBuyPrice(String baseUrl) {
        String API_URL="/prices/BTC-USD/buy";

        try {
            Response response = client
                    .target(baseUrl + API_URL)
                    .request(MediaType.APPLICATION_JSON)                    
                    .get();
            JsonObject resultRawData = new Gson().fromJson(response.readEntity(String.class), JsonObject.class);
            Price price = null;

            if (resultRawData != null) {
                JsonObject dataObj = resultRawData.get("data").getAsJsonObject();
                price = gsonObj.fromJson(dataObj, Price.class);
            }

            return gsonObj.toJson(price);
        } catch (Exception e) {
            e.printStackTrace();
            RequestError requestError = new RequestError("OOPS! Failed fetching data", 200);
            return gsonObj.toJson(requestError);
        }
    } // End of getBuyPrice()

    /**
     * Service to fetch sell price.
     * API: https://api.coinbase.com/v2/prices/:currency_pair/sell
     * Docs: https://docs.cloud.coinbase.com/sign-in-with-coinbase/docs/api-prices#get-sell-price
     * @return
     */
    public String getSellPrice(String baseUrl) {
        String API_URL="/prices/BTC-USD/sell";

        try {
            Response response = client
                    .target(baseUrl + API_URL)
                    .request(MediaType.APPLICATION_JSON)
                    .get();
            JsonObject resultRawData = new Gson().fromJson(response.readEntity(String.class), JsonObject.class);
            Price price = null;

            if (resultRawData != null) {
                JsonObject dataObj = resultRawData.get("data").getAsJsonObject();
                price = gsonObj.fromJson(dataObj, Price.class);
            }

            return gsonObj.toJson(price);
        } catch (Exception e) {
            e.printStackTrace();
            RequestError requestError = new RequestError("OOPS! Failed fetching data", 200);
            return gsonObj.toJson(requestError);
        }
    } // End of getSellPrice()

    /**
     * Service to fetch spot price.
     * API: https://api.coinbase.com/v2/prices/:currency_pair/spot
     * Docs: https://docs.cloud.coinbase.com/sign-in-with-coinbase/docs/api-prices#get-spot-price
     * @return
     */
    public String getSpotPrice(String baseUrl) {
        String API_URL="/prices/BTC-USD/spot";

        try {
            Response response = client
                    .target(baseUrl + API_URL)
                    .request(MediaType.APPLICATION_JSON)
                    .get();
            JsonObject resultRawData = new Gson().fromJson(response.readEntity(String.class), JsonObject.class);
            Price price = null;

            if (resultRawData != null) {
                JsonObject dataObj = resultRawData.get("data").getAsJsonObject();
                price = gsonObj.fromJson(dataObj, Price.class);
            }

            return gsonObj.toJson(price);
        } catch (Exception e) {
            e.printStackTrace();
            RequestError requestError = new RequestError("OOPS! Failed fetching data", 200);
            return gsonObj.toJson(requestError);
        }
    } // End of getSpotPrice()

}