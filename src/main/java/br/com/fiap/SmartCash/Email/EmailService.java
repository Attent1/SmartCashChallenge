package br.com.fiap.SmartCash.Email;

import br.com.fiap.SmartCash.Email.dto.EmailDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    private final MessageSource messageSource;

    public EmailService (JavaMailSender mailSender, MessageSource messageSource){
        this.mailSender = mailSender;
        this.messageSource = messageSource;
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
    public void sendEmail(EmailDto emailDto){

        Locale usuarioLocale = LocaleContextHolder.getLocale();

        String subject = messageSource.getMessage("email.subject", null, usuarioLocale);
        String body = messageSource.getMessage("email.body", new Object[]{emailDto.getNome()}, usuarioLocale);
        
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(emailDto.getDestinatario());
        mail.setSubject(subject);
        mail.setText(body);

        mailSender.send(mail);
    }

}
