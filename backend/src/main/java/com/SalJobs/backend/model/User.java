package com.SalJobs.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * Class for the User. Add the constructor, getters and setter. The @ stuff
 * corresponds to SQL notation, i.e. @ID == primary key, @Column == attribute. 
 * For other stuff like foreign keys, search up JPA annotations, jpa foreign key etc.
 */

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "email")
    private String email;
    
    @Column(name = "name")
    private String name;

	@Column(name = "password")
    private String password;
    
    public User() {

    }
    
    public User(long id, String email, String name, String password) {
    	this.id = id;
    	this.email = email;
    	this.name = name;
    	this.password = password;
    }
    
    public long getId() {
    	return id;
    }
    
    public void setId(long id) {
    	this.id = id;
    }
    
    public String getEmail() {
    	return email;
    }
    
    public void setEmail(String email) {
    	this.email = email;
    }
    
    public String getName() {
    	return name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public String getPassword() {
    	return password;
    }
    
    public void setPassword(String password) {
    	this.password = password;
    }
   
}
