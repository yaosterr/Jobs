package com.SalJobs.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SalJobs.backend.model.User;

/*
 * This shit connects to MySQL database. It comes with prebuilt queries
 * like find
 */

public interface UserRepository extends JpaRepository<User, Long> {
	/*this is an example of custom query
     * it finds users from the MYSQL database based on Email. Nothing else
     * needed
     */
	User findByEmail(String email);
}