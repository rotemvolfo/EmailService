"# EmailService" 

Which   language,   framework   and   libraries   you   chose   and   why?

Language : java 

Server : Tomcat

Libraries :

Javax.servlet – To handle post requests. I used this package based on a previous experience, so setup time was fast for me. 

Jsoup – to handle the requirement of converting the html to plain text. 
My initial thought was to use regex but after a short research I found that creating regex expression might not cover all cases and will be hard to maintain over time. The jsoup is an open source library that has been developed specially for HTML parsing problems.  

Http Client - to create the post request to the MailGun server. basic library in Java(supported by Oracel ) to create client request.
sendGrid -java-latest – SendGrid Solution for java.
    
Tradeoffs   you   might   have   made,   anything   you   left   out,   or   what   you   might   do? 

My initial plan was to create a configuration file with a list of all the service providers that the server can support. ( I added the file under the resources folder in the project as an example ) .
the server will try to send message to one of the services in the list  , if it fails the program will continue to the next service provider and so on until succeed .
currently I have a function in the “SendMessage”  class that returns the default provider name (hard coded)  .  
In addition, if I had more time I would add tests to the code . 

Anything   else   you   wish   to   include?

 I used the Factory design pattern in order to simplify the adding of a new service provider in the future
and to switch easily between the providers .



 
