package coinbase.cloud.java.examples.quickstart.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import javax.enterprise.context.ApplicationScoped;
import java.util.logging.Logger;

/**
 * Program to provide test message for the services.
 *
 * @author Suren K
 * @since 10-06-2021
 */
@ApplicationScoped
public class TestServices {
    private static final Gson gsonObj = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
    private static final Logger LOG = Logger.getLogger(TestServices.class.getName());

    public String getTestServiceMessage() {
        JsonObject message = new JsonObject();
        message.addProperty("message", "The endpoint /coinbase is Available!");
        return gsonObj.toJson(message);
    }
}
