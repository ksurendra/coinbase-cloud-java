package coinbase.cloud.java.examples.quickstart.utils;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Program to expose the projects configurations values.
 *
 * @author Suren K
 * @since 10-05-2021
 */
@ApplicationScoped
public class APIConfig {
    private final AtomicReference<String> clientId = new AtomicReference<>();
    private final AtomicReference<String> clientSecret = new AtomicReference<>();
    private final AtomicReference<String> oauthBaseAccessUrl = new AtomicReference<>();
    private final AtomicReference<String> oauthTokenUrl = new AtomicReference<>();
    private final AtomicReference<String> baseAccessUrl = new AtomicReference<>();

    @Inject
    public APIConfig(
            @ConfigProperty(name = "coinbase.cloud.client_id") String clientId,
            @ConfigProperty(name = "coinbase.cloud.client_secret") String clientSecret,
            @ConfigProperty(name = "coinbase.cloud.baseurl") String baseAccessUrl,
            @ConfigProperty(name = "coinbase.cloud.authentication.oauth.baseurl") String oauthBaseAccessUrl,
            @ConfigProperty(name = "coinbase.cloud.authentication.oauth.tokenurl") String oauthTokenUrl) {
        this.clientId.set(clientId);
        this.clientSecret.set(clientSecret);
        this.oauthBaseAccessUrl.set(oauthBaseAccessUrl);
        this.oauthTokenUrl.set(oauthTokenUrl);
        this.baseAccessUrl.set(baseAccessUrl);
    }

    public String getClientId() {
        return clientId.get();
    }

    void setClientId(String clientId) {
        this.clientId.set(clientId);
    }

    public String getClientSecret() {
        return clientSecret.get();
    }

    void setClientSecret(String clientSecret) {
        this.clientSecret.set(clientSecret);
    }

    public String getOauthBaseAccessUrl() {
        return oauthBaseAccessUrl.get();
    }

    void setOauthBaseAccessUrl(String oauthBaseAccessUrl) {
        this.oauthBaseAccessUrl.set(oauthBaseAccessUrl);
    }

    public String getBaseAccessUrl() {
        return baseAccessUrl.get();
    }

    void setBaseAccessUrl(String baseAccessUrl) {
        this.baseAccessUrl.set(baseAccessUrl);
    }

    public String getOauthTokenUrl() {
        return oauthTokenUrl.get();
    }

    void setOauthTokenUrl(String oauthTokenUrl) {
        this.oauthTokenUrl.set(oauthTokenUrl);
    }
}
