
import org.json.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/mail")
public class EmailServlet extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        // reads the Post request to string buffer
        StringBuffer msgBuffer = new StringBuffer();
        String line;
        try
        {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                msgBuffer.append(line);
        }
        catch (Exception e)
        {
            throw new IOException("Error reading request to string buffer");
        }
        try
        {
            // creates json object from the string buffer
           JSONObject jsonObject = new JSONObject(msgBuffer.toString());

           String validationResult= MessageValidation.isMessageValid(jsonObject);

            if(validationResult.equalsIgnoreCase("message valid"))
                new SendMessage().send(jsonObject);
            else
             {
                 // send response to the user with error message
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                PrintWriter responseWriter = response.getWriter();
                JSONObject  json = new JSONObject ();
                json.put("Error", validationResult);
                // finally output the json string
                responseWriter.print(json.toString());
            }
        } catch (JSONException e) {
            throw new IOException("Error parsing JSON request string");
        }
    }
}
