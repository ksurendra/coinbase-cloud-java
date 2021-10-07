package coinbase.cloud.java.examples.quickstart.models;

import com.google.gson.annotations.SerializedName;

public class RefreshTokenRequest {
    @SerializedName("grant_type")
    private String grant_type;

    @SerializedName("client_id")
    private String client_id;

    @SerializedName("client_secret")
    private String client_secret;

    @SerializedName("refresh_token")
    private String refresh_token;

    public RefreshTokenRequest(String grant_type, String client_id, String client_secret, String refresh_token) {
        this.grant_type = grant_type;
        this.client_id = client_id;
        this.client_secret = client_secret;
        this.refresh_token = refresh_token;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }
}
