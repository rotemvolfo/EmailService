package factory.service.providers;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;


public class Mailgun implements ISendEmail {

    private String _domain="sandbox2a03817f0865408e96d9daa0d2c5a8e1.mailgun.org";

    @Override
    public void sendMessage(JSONObject message , String apiKey){

        HttpClient client = HttpClient.newBuilder().build(); //creating HttpClient to send requests
        try {
            HttpRequest request = HttpRequest.newBuilder(URI.create("https://api.mailgun.net/v3/" + this._domain + "/messages"))
                    .header("Authorization", basicAuth(apiKey))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(convertJsonToQueryString(message)))
                    .build();

            HttpResponse<?> response = client.send(request, HttpResponse.BodyHandlers.discarding());
            //handle response here...
            System.out.println(response.statusCode());
            System.out.println(response.body());
            System.out.println(response.headers());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    private String basicAuth(String apiKey) {
        String auth = "api:"+apiKey;
        return "Basic " + new String(Base64.getEncoder().encode(auth.getBytes()));
    }
    private String convertJsonToQueryString(JSONObject message) throws JSONException
    {
       String queryStr= "from="+message.get("from_name")+" "+message.get("from")+"&"+"to="+message.get("to_name")+" "+message.get("to")+"&"+"subject="+message.get("subject")+
                        "&"+"text="+message.get("body");
        return queryStr;
  }
}
