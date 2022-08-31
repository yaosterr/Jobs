package com.SalJobs.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SalJobs.backend.model.Message;

/*
 * This shit connects to MySQL database. It comes with prebuilt queries
 * like find
 */

public interface MessageRepository extends JpaRepository<Message, Long> {
    /*this is an example of custom query
     * it finds listings from the MYSQL database based on Status. Nothing else
     * needed
     */
	
	List <Message> findByChatId(String chatId);
	
	// add custom query methods below
}
