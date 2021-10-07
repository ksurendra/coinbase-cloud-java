package coinbase.cloud.java.examples.quickstart.services;

import coinbase.cloud.java.examples.quickstart.models.RefreshTokenRequest;
import coinbase.cloud.java.examples.quickstart.models.RequestToken;
import coinbase.cloud.java.examples.quickstart.models.Token;
import coinbase.cloud.java.examples.quickstart.utils.RequestError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

/**
 * ## Authentication Process
 * 1. Redirect users to request Coinbase access
 * 2. Coinbase redirects back to your site
 * 3. Exchange **code** for an access token
 * 4. Make an API call, that may require a token
 */
public class AuthenticationServices {

    Client client = ClientBuilder.newClient();
    private static final Gson gsonObj = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

    private static final Logger LOG = Logger.getLogger(AuthenticationServices.class.getName());

    /**
     * Eg: GET https://www.coinbase.com/oauth/authorize?
     *          response_type=code&
     *          client_id=1532c6...&
     *          redirect_uri=https%3A%2F%2Fexample.com%2Foauth%2Fcallback&
     *          state=134ef5504a94&
     *          scope=wallet:user:read,wallet:accounts:read
     */
    private String serviceCode() {
        try {

            return "";
        } catch (Exception e) {
            e.printStackTrace();
            RequestError requestError = new RequestError("OOPS! Failed fetching data", 200);
            return gsonObj.toJson(requestError);
        }
    }

    /**
     * API: https://api.coinbase.com/oauth/token
     *
     * @return
     */
    public String serviceToken(String oauthTokenUrl, String clientId, String clientSecret) {
        try {
            // Get Code
            String code = getCode(clientId);

            WebTarget base = client
                    .target(oauthTokenUrl);

            RequestToken requestToken = new RequestToken("authorization_code",
                                                code,
                                                clientId,
                                                clientSecret,
                                                "http://localhost:9393");
            Response response = base
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.json(requestToken));

            JsonObject resultRawData = new Gson().fromJson(response.readEntity(String.class), JsonObject.class);
            LOG.info("resultRawData = " + resultRawData);
            Token token = null;

            if (resultRawData != null) {
                token = gsonObj.fromJson(resultRawData, Token.class);
            }

            return gsonObj.toJson(token);
        } catch (Exception e) {
            e.printStackTrace();
            RequestError requestError = new RequestError("OOPS! Failed fetching data", 200);
            return gsonObj.toJson(requestError);
        }
    }

    /**
     * Eg: https://api.coinbase.com/oauth/token?
     *      grant_type=authorization_code&
     *      code=9d688..e&
     *      client_id=0c95e...3&
     *      client_secret=8105d...6&
     *      redirect_uri=http://localhost:9393
     *
     * @return
     */
    public Token getAccessToken(String code) {
        LOG.info("AuthenticationServices.getAccessToken() {}, Code="+code);
        Token token = null;

        try {
            WebTarget base = client
                    .target("https://api.coinbase.com/oauth/token");

            // with-code & without-refresh token
            RequestToken requestToken = new RequestToken("authorization_code",
                                                code,
                                                "0c95e87e0eb90deb3f43f364e30638542e6bf7cee70d4b0b62056cf30fde9df3",
                                                "8105ddd0376ee9e7c8fd6cb561760f25922f61be6b7a73acbd78a098a6971286",
                                                "http://localhost:9393");
            Response response = base
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.json(requestToken));

            JsonObject resultRawData = new Gson().fromJson(response.readEntity(String.class), JsonObject.class);
            LOG.info("getAccessToken - response = " + resultRawData);

            if (resultRawData != null) {
                token = gsonObj.fromJson(resultRawData, Token.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
            RequestError requestError = new RequestError("OOPS! Failed fetching data", 200);
            //return gsonObj.toJson(requestError);
        }

        return token;
    }

    /**
     * Get a new access token.
     *
     *   Eg: curl https://api.coinbase.com/oauth/token \
     *      -X POST \
     *      -d 'grant_type=refresh_token&
     *          client_id=YOUR_CLIENT_ID&
     *          client_secret=YOUR_CLIENT_SECRET&
     *          refresh_token=REFRESH_TOKEN'
     * @return
     */
    public Token getRefreshToken(String refreshTokenFromPreviousResponse) {
        LOG.info("AuthenticationServices.getRefreshToken() {}, refreshTokenFromPreviousResponse="+refreshTokenFromPreviousResponse);
        Token token = null;
        try {
            WebTarget base = client
                    .target("https://api.coinbase.com/oauth/token");

            // without-code & with-refresh token
            RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest("refresh_token",
                    "0c95e87e0eb90deb3f43f364e30638542e6bf7cee70d4b0b62056cf30fde9df3",
                    "8105ddd0376ee9e7c8fd6cb561760f25922f61be6b7a73acbd78a098a6971286",
                    refreshTokenFromPreviousResponse);

            Response response = base
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.json(refreshTokenRequest));

            JsonObject resultRawData = new Gson().fromJson(response.readEntity(String.class), JsonObject.class);
            LOG.info("getRefreshToken - response = " + resultRawData);

            if (resultRawData != null) {
                token = gsonObj.fromJson(resultRawData, Token.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
            RequestError requestError = new RequestError("OOPS! Failed fetching data", 200);
            //return gsonObj.toJson(requestError);
        }

        return token;
    }

    /**
     * Get code from application url
     * https://www.coinbase.com/oauth/authorize?
     * response_type=code&
     * client_id=1532..&
     * redirect_uri=https%3A%2F%2Fexample.com%2Foauth%2Fcallback&
     * state=134ef5504a94&
     * scope=wallet:user:read,wallet:accounts:read
     *
     * @return
     */
    protected String getCode(String clientId) {
        LOG.info("AuthenticationServices.getCode() {}");
        try {
            WebTarget target = client
                    .target("https://www.coinbase.com/oauth/authorize")
                    .queryParam("response_type", "code")
                    .queryParam("client_id", clientId)
                    .queryParam("redirect_uri", "http://localhost:9393/coinbase/oauth/callback")
                    .queryParam("state", "134ef5504a94")
                    .queryParam("scope", "wallet:user:read,wallet:accounts:read");

            Response response = target
                                    .request(MediaType.APPLICATION_JSON)
                                    .get();
            LOG.info("getCode - response = " + response);
            //@TODO To get code from response url

            return "4f6f3a11de4093e83c7bfb2039af96950dc8f4960696813b8b556f6055165264";
        } catch (Exception e) {
            e.printStackTrace();
            RequestError requestError = new RequestError("OOPS! Failed fetching data", 200);
            return gsonObj.toJson(requestError);
        }

    }

}
