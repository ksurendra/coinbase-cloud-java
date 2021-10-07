package coinbase.cloud.java.examples.quickstart.models;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("username")
    private String username;

    @SerializedName("profile_location")
    private String profile_location;

    @SerializedName("profile_bio")
    private String profile_bio;

    @SerializedName("profile_url")
    private String profile_url;

    @SerializedName("avatar_url")
    private String avatar_url;

    @SerializedName("resource")
    private String resource;

    @SerializedName("resource_path")
    private String resource_path;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfile_location() {
        return profile_location;
    }

    public void setProfile_location(String profile_location) {
        this.profile_location = profile_location;
    }

    public String getProfile_bio() {
        return profile_bio;
    }

    public void setProfile_bio(String profile_bio) {
        this.profile_bio = profile_bio;
    }

    public String getProfile_url() {
        return profile_url;
    }

    public void setProfile_url(String profile_url) {
        this.profile_url = profile_url;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getResource_path() {
        return resource_path;
    }

    public void setResource_path(String resource_path) {
        this.resource_path = resource_path;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", profile_location='" + profile_location + '\'' +
                ", profile_bio='" + profile_bio + '\'' +
                ", profile_url='" + profile_url + '\'' +
                ", avatar_url='" + avatar_url + '\'' +
                ", resource='" + resource + '\'' +
                ", resource_path='" + resource_path + '\'' +
                '}';
    }
}
