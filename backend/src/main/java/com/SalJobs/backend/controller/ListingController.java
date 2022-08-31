package com.SalJobs.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import com.SalJobs.backend.model.Listing;
import com.SalJobs.backend.repository.ListingRepository;

import java.util.*;

/*
 * API methods go here. @PostMapping means it's a post method, @GetMapping means
 * get method. etc. etc. 
 */

@CrossOrigin("*")
@RestController
@RequestMapping("/listings")
public class ListingController {

	@Autowired
	private ListingRepository listingRepository;

	@GetMapping("/getAll")
	public List<Listing> getAllListings() {
		List<Listing> result = listingRepository.findAll();
		return result;
	}

	@PostMapping("/create")
	public Listing createListing(@RequestBody Listing listing) {
		return listingRepository.save(listing);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteListing(@PathVariable("id") Long id) {
		System.out.println(id);
		listingRepository.deleteById(id);
	}
	
	@MessageMapping("/application")
    @SendTo("/listingSocket/public")
    public String receiveMessage(@Payload Long listingId){
		listingRepository.increment(listingId);
        return "Added";
    }

}
