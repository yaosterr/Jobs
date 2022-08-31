package com.SalJobs.backend.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.SalJobs.backend.model.Listing;

/*
 * This shit connects to MySQL database. It comes with prebuilt queries
 * like find
 */

public interface ListingRepository extends JpaRepository<Listing, Long> {
	/*
	 * this is an example of custom query it finds listings from the MYSQL database
	 * based on Status. Nothing else needed
	 */
	List<Listing> findByTitle(String title);

	List<Listing> findByCompany(String company);
	
	@Transactional
	@Modifying
	@Query("update Listing l set l.applicantCount = l.applicantCount + 1 where l.id = ?1")
	void increment(long id);

	// add custom query methods below
}