package factory.service.providers;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public interface ISendEmail {

    void sendMessage(JSONObject message , String key) throws JSONException, IOException;
}


