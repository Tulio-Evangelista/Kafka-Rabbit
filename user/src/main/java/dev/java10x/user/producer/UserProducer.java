package dev.java10x.user.producer;

import dev.java10x.user.domain.UserModel;
import dev.java10x.user.dto.EmailDto;
import org.apache.catalina.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {


    public final RabbitTemplate rabbitTemplate;
    private String rountingKey = "email-queue";

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    @Scheduled(fixedDelay = 1000)
    public void sendMessage(UserModel userModel){
        var emailDto = new EmailDto();
        emailDto.setId(userModel.getUserId());
        emailDto.setEmailTo(userModel.getEmail());
        emailDto.setEmailSubject("Bem Vindo");
        emailDto.setBody("Olá " + userModel.getName() + ", \n\nSeja bem vindo ao nosso sistema!\n\nAtenciosamente,\nEquipe de Suporte");


        rabbitTemplate.convertAndSend( "",rountingKey, emailDto);
    }
}
