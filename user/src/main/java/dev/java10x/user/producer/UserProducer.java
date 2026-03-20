package dev.java10x.user.producer;

import dev.java10x.user.domain.UserModel;
import dev.java10x.user.dto.EmailDto;
import org.apache.catalina.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {


    final RabbitTemplate rabbitTemplate;


    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    private final String rountingKey = "email-queue";


    public void sendMessage(UserModel userModel){
        var emailDto = new EmailDto();
        emailDto.setId(userModel.getUserId());
        emailDto.setEmailTo(userModel.getEmail());
        emailDto.setEmailSubject("Bem Vindo");
        emailDto.setBody("Olá " + userModel.getName() + ", \n\nSeja bem vindo ao nosso sistema!\n\nAtenciosamente,\nEquipe de Suporte");


        rabbitTemplate.convertAndSend( "",rountingKey, emailDto);
    }
}
