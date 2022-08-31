package com.SalJobs.backend.model;

import javax.persistence.*;

@Entity
@Table(name = "Messages")
public class Message {
	
    public Message() {
    	
	}

	public Message(long id, String sender, String receiver, String body, String chatId) {
		this.id = id;
		this.sender = sender;
		this.receiver = receiver;
		this.body = body;
		this.chatId = chatId;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "sender")
    private String sender;
    
    @Column(name = "receiver")
    private String receiver;

	@Column(name = "body")
    private String body;
	
	@Column(name = "chatId")
    private String chatId;
    
    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getChatId() {
		return chatId;
	}

	public void setChatId(String chatId) {
		this.chatId = chatId;
	}

	
}