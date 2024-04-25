package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Visa;

@Repository
public interface VisaRepository extends JpaRepository<Visa,String>{
	
	String countByOccupation(String occupation);
	@Query("Select v from Visa v where v.visaId=?1")
	public Visa getVisaDetails(String visaId);
}
