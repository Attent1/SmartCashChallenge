package br.com.fiap.SmartCash.Email;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService (JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    @Value("${spring.mail.username}")
    private String remetente;

    public void sendEmailText(String destinatario, String assunto, String conteudo){
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(remetente);
            simpleMailMessage.setTo(destinatario);
            simpleMailMessage.setSubject(assunto);
            simpleMailMessage.setText(conteudo);
            mailSender.send(simpleMailMessage);
    }

    @RabbitListener(queues = "email-queue")
    public void sendEmail(Email email){
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email.getDestinatario());
        mail.setSubject(email.getAssunto());
        mail.setText(email.getConteudo());

        mailSender.send(mail);
    }

}
