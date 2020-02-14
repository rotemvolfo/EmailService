package factory.service.providers;
import com.sendgrid.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Sendgrid implements ISendEmail {

    @Override
    public void sendMessage(JSONObject message , String apiKey) throws JSONException, IOException
    {
        Email from = new Email( message.getString("from"),message.getString("from_name"));
        String subject = message.getString("subject");
        Email to = new Email(message.getString("to"),message.getString("to_name"));
        Content content = new Content("text/plain", message.getString("body"));
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }
}
