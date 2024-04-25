package com.example.demo.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Visa;
import com.example.demo.model.VisaCancel;
import com.example.demo.repositories.VisaCancelRepository;
import com.example.demo.repositories.VisaRepository;

@Service
public class VisaCancelService {
	
	@Autowired
	private VisaCancelRepository visaCancelrepository;
	
	public VisaCancel cancelVisa(VisaCancel visaCancellation) {

        // Calculate cancellation charge based on date difference
        double cancellationCharge = calculateCancellationCharge(visaCancellation);

        // Set cancellation charge and status to visaCancellation object
        visaCancellation.setCancellationCharge(cancellationCharge);
        visaCancellation.setStatus("Cancelled");

        // Save visa cancellation details to database
        return visaCancelrepository.save(visaCancellation);
    }

    // Business logic to calculate cancellation charge based on date difference
    private double calculateCancellationCharge(VisaCancel visaCancellation) {

        LocalDate currentDate = LocalDate.now();
        LocalDate issueDate = new java.sql.Date(visaCancellation.getDateOfIssue().getTime()).toLocalDate();
        Period period = Period.between(issueDate, currentDate);
        int monthsDifference = period.getMonths();
        VisaService visaservice=new VisaService();
        String occupation=visaservice.getVisaOccupation(visaCancellation);
        double cost=visaservice.getCostAfter(visaCancellation);
        // Logic to calculate cancellation charge based on months difference
        if (monthsDifference < 6) {
            if(occupation.equals("Student"))
            	return 0.15*cost;
            else if(occupation.equals("Private Employee"))
            	return 0.15*cost;
            else if(occupation.equals("Government Employee"))
            	return 0.12*cost;
            else if(occupation.equals("Self Employed"))
            	return 0.15*cost;
            else
            	return 0.10*cost;
        }
        else if (monthsDifference >= 6 && monthsDifference < 12) {
        	if(occupation.equals("Student"))
            	return 0.25*cost;
            else if(occupation.equals("Private Employee"))
            	return 0.25*cost;
            else if(occupation.equals("Government Employee"))
            	return 0.20*cost;
            else if(occupation.equals("Self Employed"))
            	return 0.25*cost;
            else
            	return 0.20*cost;
        }
        else {
        	if(occupation.equals("Student"))
            	return 0.25*cost;
            else if(occupation.equals("Private Employee"))
            	return 0.20*cost;
            else if(occupation.equals("Government Employee"))
            	return 0.25*cost;
            else if(occupation.equals("Self Employed"))
            	return 0.25*cost;
            else
            	return 0.20*cost;
        }
    }

}
