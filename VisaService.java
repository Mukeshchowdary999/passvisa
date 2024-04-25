package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Visa;
import com.example.demo.model.VisaCancel;
import com.example.demo.repositories.VisaRepository;

@Service
public class VisaService {
	
	@Autowired
	private VisaRepository visaRepository;
	
	
	public void saveVisaData(Visa visa)
	{
		visaRepository.save(visa);
	}
	
	
	 public int generateRandomNumber() {

         return (int) (Math.random() * (9999 - 1000 + 1) + 1000);

	 }
	 
	 public double calculateRegistrationCost(Visa visa) {

	        // Calculate Registration Cost based on place and applicant's occupation

	        double cost = 0;

	        switch (visa.getOccupation()) {

	            case "Student":

	                switch (visa.getCountry()) {

	                    case "USA":

	                        cost = 3000;

	                        break;

	                    case "CHINA":

	                        cost = 1500;

	                        break;

	                    case "JAPAN":

	                        cost = 3500;

	                        break;

	                }

	                break;

	            case "Private Employee":

	                switch (visa.getCountry()) {

	                    case "USA":

	                        cost = 4500;

	                        break;

	                    case "CHINA":

	                        cost = 2000;

	                        break;

	                    case "JAPAN":

	                        cost = 4000;

	                        break;

	                }

	                break;

	            case "Government Employee":

	                switch (visa.getCountry()) {

	                    case "USA":

	                        cost = 5000;

	                        break;

	                    case "CHINA":

	                        cost = 3000;

	                        break;

	                    case "JAPAN":

	                        cost = 4500;

	                        break;

	                }

	                break;

	            case "Self Employed":

	                switch (visa.getCountry()) {

	                    case "USA":

	                        cost = 6000;

	                        break;

	                    case "CHINA":

	                        cost = 4000;

	                        break;

	                    case "JAPAN":

	                        cost = 9000;

	                        break;

	                }

	                break;

	            case "Retired Employee":

	                switch (visa.getCountry()) {

	                    case "USA":

	                        cost = 2000;

	                        break;

	                    case "CHINA":

	                        cost = 2000;

	                        break;

	                    case "JAPAN":

	                        cost = 1000;

	                        break;

	                }

	                break;

	        }

	        return cost;

	    }

	    public String generateVisaId(Visa visa) {

	        // Generate Visa ID based on applicant's occupation

	        String occupationCode = "";

	        switch (visa.getOccupation()) {

	            case "Student":

	                occupationCode = "STU";

	                break;

	            case "Private Employee":

	                occupationCode = "PE";

	                break;

	            case "Government Employee":

	                occupationCode = "GE";

	                break;

	            case "Self Employed":

	                occupationCode = "SE";

	                break;

	            case "Retired Employee":

	                occupationCode = "RE";

	                break;

	            default:

	                throw new IllegalArgumentException("Invalid occupation specified.");

	        }

	        return occupationCode + "-" + generateRandomNumber();

	    }

	    public double getExpiryDate(Visa visa) {

	        double visaPermit=0;

	        switch (visa.getOccupation()) {

	            case "Student":

	                visaPermit = 2;

	                break;

	            case "Private Employee":

	                visaPermit = 3;

	                break;

	            case "Government Employee":

	                visaPermit = 4;

	                break;

	            case "Self Employed":

	                visaPermit = 1;

	                break;

	            case "Retired Employee":

	                visaPermit = 1.5;

	                break;

	            default:

	                throw new IllegalArgumentException("Invalid occupation specified.");

	        }

	        return visaPermit;
	    }
	 
	    public double getCostAfter(VisaCancel visacancel) {
		 Visa visa=visaRepository.getVisaDetails(visacancel.getVisaNumber());
		 return calculateRegistrationCost(visa);
		 
	 }
	    public String getVisaOccupation(VisaCancel visacancel) {
			 Visa visa=visaRepository.getVisaDetails(visacancel.getVisaNumber());
			 return visa.getOccupation();
			 
		 }
}
