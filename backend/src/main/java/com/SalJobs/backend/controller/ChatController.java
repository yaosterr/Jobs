package com.SalJobs.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.SalJobs.backend.model.Chat;
import com.SalJobs.backend.model.Listing;
import com.SalJobs.backend.model.Message;
import com.SalJobs.backend.repository.ChatRepository;
import com.SalJobs.backend.repository.ListingRepository;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * API methods go here. @PostMapping means it's a post method, @GetMapping means
 * get method. etc. etc. 
 */

@CrossOrigin("*")
@RestController
@RequestMapping("/chats")
public class ChatController {

    @Autowired
    private ChatRepository chatRepository;

    @CrossOrigin
    @GetMapping("/getChats/{userId}")
    public List<Chat> getAllChats(@PathVariable String userId){
    	List<Chat> result1 = chatRepository.findByRecruiterId(Long.parseLong(userId));
    	List<Chat> result2 = chatRepository.findByApplicantId(Long.parseLong(userId));
    	result1.removeAll(result2);
    	result1.addAll(result2);
        return result1;
    }

    @PostMapping("/create")
    public Chat createListing(@RequestBody Chat chat) {
        return chatRepository.save(chat);
    }
}

