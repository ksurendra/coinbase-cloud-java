package coinbase.cloud.java.examples.quickstart.services;

import coinbase.cloud.java.examples.quickstart.models.Token;
import coinbase.cloud.java.examples.quickstart.models.User;
import coinbase.cloud.java.examples.quickstart.utils.RequestError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

/**
 * Program to provide "User" services.
 *
 * @author Suren K
 * @since 10-06-2021
 */
@ApplicationScoped
public class UserServices {
    Client client = ClientBuilder.newClient();
    private static final Gson gsonObj = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
    private static final Logger LOG = Logger.getLogger(UserServices.class.getName());
    AuthenticationServices authenticationServices = new AuthenticationServices();

    /**
     * Get any user's public information with their ID.
     * API: https://api.coinbase.com/v2/users/:user_id
     * Docs: https://docs.cloud.coinbase.com/sign-in-with-coinbase/docs/api-users#show-a-user
     * @param baseUrl
     * @return
     */
    public String serviceShowAUser(String baseUrl) {
        LOG.info("UserServices.serviceShowAUser() {}");

        String API_URL="/users/067ade57-5d9e-5439-9b4c-b46a62fdf057";
        Response response;
        Token tokenObj = null;

        try {
            // Get Access Token
            tokenObj = getAccessToken();

            WebTarget base = client.target(baseUrl + API_URL);

            response = base
                        .request(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenObj.getAccess_token())
                        .get();

            // Check for access token
            if (response!=null && response.getStatus() == 401) {
                // If the response code is 401 - means, the access token expired. Fetch a new one. Re-run the api
                LOG.info(" --Access token expired. Fetching new one...");
                String refreshToken = tokenObj.getRefresh_token();
                LOG.info(" refreshToken="+refreshToken);
                tokenObj = getRefreshToken(refreshToken);

                response = base
                        .request(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenObj.getRefresh_token())
                        .get();
            }

            JsonObject resultRawData = new Gson().fromJson(response.readEntity(String.class), JsonObject.class);
            LOG.info("--serviceShowAUser data:" + resultRawData);
            User user = null;

            if (resultRawData != null) {
                if (resultRawData.has("data")) {
                    JsonObject dataObj = resultRawData.get("data").getAsJsonObject();
                    user = gsonObj.fromJson(dataObj, User.class);
                }
            }

            return gsonObj.toJson(user);
        } catch (Exception e) {
            e.printStackTrace();
            RequestError requestError = new RequestError("OOPS! Failed fetching User data", 200);
            return gsonObj.toJson(requestError);
        }
    } // End of getBuyPrice()

    /**
     *
     * @return
     */
    private Token getAccessToken() {
        LOG.info("UserServices.getAccessToken() {}");
        // Access token will be part of '/oauth/token' api response when called along with 'code'
        String code = getCode();
        return authenticationServices.getAccessToken(code);
    }

    /**
     *
     * @return new access token
     */
    private Token getRefreshToken(String refreshTokenRequest) {
        LOG.info("UserServices.getRefreshToken() {}");

        return authenticationServices.getRefreshToken(refreshTokenRequest);
    }

    /**
     * Get the Code the URL
     * @return Code
     */
    private String getCode() {
        LOG.info("UserServices.getCode() {}");
        // 'code' value will be part of the URL returned on calling '/oauth/authorize'  API
        return authenticationServices.getCode("0c95e87e0eb90deb3f43f364e30638542e6bf7cee70d4b0b62056cf30fde9df3");
    }

}
