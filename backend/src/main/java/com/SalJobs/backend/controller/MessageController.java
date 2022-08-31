package com.SalJobs.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import com.SalJobs.backend.model.Message;
import com.SalJobs.backend.repository.MessageRepository;

import java.util.List;

/*
 * API methods go here. @PostMapping means it's a post method, @GetMapping means
 * get method. etc. etc. 
 */

@CrossOrigin("*")
@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;
    
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("/getMessages/{chatId}")
    public List<Message> getAllMessages(@PathVariable String chatId){
    	List<Message> result = messageRepository.findByChatId(chatId);
        return result;
    }
    
    @MessageMapping("/private-message")
    public Message recMessage(@Payload Message message){
    	messageRepository.save(message);
        simpMessagingTemplate.convertAndSendToUser(message.getReceiver(),"/private",message);
        System.out.println("test"+message.toString());
        return message;
    }

}

