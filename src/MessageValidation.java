 import org.json.JSONException;
 import org.json.JSONObject;
 import java.util.HashSet;
 import java.util.Iterator;
 import java.util.Set;
 import java.util.regex.Pattern;

 public class MessageValidation {

     public static String isMessageValid(JSONObject message) throws JSONException {

         if(!isEmailValid((String)message.get("from")))
             return "from email address is not valid";
         if(!isEmailValid((String)message.get("to")))
             return "to email address is not valid";

         Set<String> set = new HashSet<>();
         // parameters that required in a message object
         set.add("to");
         set.add("to_name");
         set.add("from");
         set.add("from_name");
         set.add("subject");
         set.add("body");

         Iterator<String> msgKeys = message.keys();
         while(msgKeys.hasNext())
         {
             String keyStr = msgKeys.next();
                 if(set.contains(keyStr))
                 {
                     String content = (String)message.get(keyStr);
                     if(content.isEmpty())
                     {
                         return keyStr+" is empty";
                     }
                     set.remove(keyStr);
                 }
                 else
                   return  keyStr+" is not valid  field" ;
         }

       if(set.size()>0) // not all fields were send in the message
         return "missing fields in the message body";

      return "message valid";
     }

     private static boolean isEmailValid(String email) {
             String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ "[a-zA-Z0-9_+&*-]+)*@"+"(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
             Pattern pat = Pattern.compile(emailRegex);
             return pat.matcher(email).matches();
     }







 }
