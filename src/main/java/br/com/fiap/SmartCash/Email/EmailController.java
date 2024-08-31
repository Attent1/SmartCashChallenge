package br.com.fiap.SmartCash.Email;

import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("email")

public class EmailController {
    
    private final EmailService emailService;

    public EmailController(EmailService emailService){
        this.emailService = emailService;
    }
    
    @PostMapping
    public String sendEmail(@RequestBody @Valid Email email) {
        try {
            emailService.sendEmailText(email.getDestinatario(), email.getAssunto(), email.getConteudo()); 
            return "Email enviado para " + email.getDestinatario();              
        } catch (Exception e) {
            return "Falha no envio" + e.getLocalizedMessage();
        }
        
    }    
    
}
