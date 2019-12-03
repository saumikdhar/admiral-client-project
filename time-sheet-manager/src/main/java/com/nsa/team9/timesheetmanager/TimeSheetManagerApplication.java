package com.nsa.team9.timesheetmanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TimeSheetManagerApplication {

//    @Value("${gmail.username}")
//    private String username;
//
//    @Value("${gmail.password}")
//    private String password;

    static final Logger LOG = LoggerFactory.getLogger(TimeSheetManagerApplication.class);

    public static void main(String[] args) {
        LOG.debug("Starting app");
        SpringApplication.run(TimeSheetManagerApplication.class, args);
    }

//    @Bean
//    public ViewResolver viewResolver() {
//        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
//        templateResolver.setTemplateMode("XHTML");
//        templateResolver.setPrefix("views/");
//        templateResolver.setSuffix(".html");
//
//        SpringTemplateEngine engine = new SpringTemplateEngine();
//        engine.setTemplateResolver(templateResolver);
//
//        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//        viewResolver.setTemplateEngine(engine);
//        return viewResolver;
//    }
//
//    @RequestMapping(value = "/send", method = RequestMethod.POST)
//    public String sendEmail(@RequestBody EmailMessage emailMessage) throws IOException, MessagingException {
//        sendmail(emailMessage);
//        return "Email sent successfully";
//    }
//
//    public void sendmail(EmailMessage emailMessage) throws MessagingException, IOException {
//
//        Properties properties = new Properties();
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.smtp.host", "smtp.gmail.com");
//        properties.put("mail.smtp.port", "587");
//
//        Session session = Session.getInstance(properties,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(username, password);
//                    }
//                });
//
//        Message message = new MimeMessage(session);
//        message.setFrom(new InternetAddress(username, false));
//
//        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailMessage.getTo_address()));
//        message.setSubject(emailMessage.getSubject());
//        message.setContent(emailMessage.getBody(), "text/html");
//        message.setSentDate(new Date());
//
//        MimeBodyPart messageBodyPart = new MimeBodyPart();
//        messageBodyPart.setContent(emailMessage.getBody(), "text/html");
//
//        Multipart multipart = new MimeMultipart();
//        multipart.addBodyPart(messageBodyPart);
//        MimeBodyPart attachPart = new MimeBodyPart();
//
//        attachPart.attachFile("C:\\Users\\hp envy\\Downloads\\milky_way_mountain_by_yakub_nihat-wallpaper-3840x2160.jpg");
//
//        multipart.addBodyPart(attachPart);
//        message.setContent(multipart);
//        // sends the email
//        Transport.send(message);
//    }
}
