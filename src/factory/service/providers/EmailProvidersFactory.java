package factory.service.providers;

public class EmailProvidersFactory {


    public static ISendEmail getProvider(String providerType) {

        if (providerType.equalsIgnoreCase("Sendgrid"))
            return new Sendgrid();
        else if (providerType.equalsIgnoreCase("Mailgun"))
            return new Mailgun();
        else
            return null;

     }
}

