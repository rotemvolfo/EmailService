import factory.service.providers.EmailProvidersFactory;
import factory.service.providers.ISendEmail;
import org.json.*;
import org.jsoup.Jsoup;
import java.io.IOException;

public class SendMessage{

    public void send(JSONObject messageToSend) throws JSONException, IOException
    {
        try{
            convertBodyHtmlToText(messageToSend);
        }catch (Exception ex){
            throw new IOException("converting body message from Html to text failed");
        }

        JSONObject providerJson = readProviderFromConfigFile();
        String apiKey= providerJson.getString("key");
        String providerName=providerJson.getString("name");
        ISendEmail service = EmailProvidersFactory.getProvider(providerName);

        if (service!=null)
            service.sendMessage(messageToSend,apiKey);
    }

    private void convertBodyHtmlToText(JSONObject messageToSend) throws JSONException // used an external library named jsoup (version 1.12.2)
    {
        String plainText= Jsoup.parse((String)messageToSend.get("body")).text();
        messageToSend.put("body",plainText);
    }

    private JSONObject readProviderFromConfigFile() throws JSONException
    { // in the project folder/resources there is a json file named providers.json with key=provider ,key=api_key
      // by changing the provider name to "SendGrid " it will send Email to Sendgrid server . I didnt implement the actual reading from the file
        JSONObject providerJson=new JSONObject();
        providerJson.put("name", "Mailgun");
        providerJson.put("key", "4b1e8a1ec5e12bebaf989e213901f375-52b6835e-fe1b0b10");
       // providerJson.put("name", "Sendgrid");
       // providerJson.put("key", "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");

        return providerJson;
    }
}