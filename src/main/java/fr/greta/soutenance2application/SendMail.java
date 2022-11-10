package fr.greta.soutenance2application;

import sendinblue.ApiClient;
import sendinblue.Configuration;
import sendinblue.auth.ApiKeyAuth;
import sibApi.TransactionalEmailsApi;
import sibModel.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * The type Send mail.
 */
public class SendMail {

    /**
     * Send mail.
     *
     * @param email    the email
     * @param filename the filename
     */
    public void sendMail(String email, String filename) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        // Configure API key authorization: api-key
        ApiKeyAuth apiKey = (ApiKeyAuth) defaultClient.getAuthentication("api-key");

        apiKey.setApiKey("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");

        try {
            TransactionalEmailsApi api = new TransactionalEmailsApi();
            SendSmtpEmailSender sender = new SendSmtpEmailSender();

//  Your email address to which your account is linked
            sender.setEmail("gretaEmail");
            sender.setName("name");
            List<SendSmtpEmailTo> toList = new ArrayList<>();
            SendSmtpEmailTo to = new SendSmtpEmailTo();

//  EMAIL of Receiver
            //to.setEmail("mapachanjie@gmail.com");
            to.setEmail(email);
            to.setName("name");
            toList.add(to);
            SendSmtpEmailAttachment attachment = new SendSmtpEmailAttachment();
            attachment.setName("test.txt");
//
////            Address of your file
          byte[] encode = Files.readAllBytes(Paths.get("C:\\Users\\mo\\reports\\" + filename+".txt"));
           attachment.setContent(encode);
           List<SendSmtpEmailAttachment> attachmentList = new ArrayList<SendSmtpEmailAttachment>();
          attachmentList.add(attachment);
            Properties headers = new Properties();
            headers.setProperty("Some-Custom-Name", "unique-id-1234");
            Properties params = new Properties();
            params.setProperty("parameter", "for your search");
            params.setProperty("subject", "Soutenance Report");
            SendSmtpEmail sendSmtpEmail = new SendSmtpEmail();
            sendSmtpEmail.setSender(sender);
            sendSmtpEmail.setTo(toList);
            sendSmtpEmail.setHtmlContent("<html><body><h1>Please find attached report for your perusal {{params.parameter}}</h1></body></html>");
            sendSmtpEmail.setSubject("{{params.subject}}");
            sendSmtpEmail.setAttachment(attachmentList);
            sendSmtpEmail.setHeaders(headers);
            sendSmtpEmail.setParams(params);
            CreateSmtpEmail response = api.sendTransacEmail(sendSmtpEmail);
            System.out.println(response.toString());
        } catch (Exception e) {
            System.out.println("Exception occurred:- " + e.getMessage());
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        SendMail sm = new SendMail();
        sm.sendMail("email.com", "report");

    }

}
