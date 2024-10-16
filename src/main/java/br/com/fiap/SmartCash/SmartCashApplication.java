package br.com.fiap.SmartCash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Locale;

@SpringBootApplication
@Controller
@EnableCaching

public class SmartCashApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartCashApplication.class, args);
	}

	@Autowired
	private MessageSource messageSource;

	@RequestMapping("/")
	@ResponseBody
	public String home(Locale locale) {
		return messageSource.getMessage("fluxoCaixa.tipo.invalido", null, locale);
	}
	
}
