package com.javaserver.javaserver.services;

import com.javaserver.javaserver.dtos.VendorRequestDTO.VendorRequest;
import com.javaserver.javaserver.dtos.VendorResponseDTO.VendorResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VendorValidationServiceImpl implements VendorValidationService {
    @Autowired
    private GoogleSearchService googleSearchService;

    @Override
    public VendorResponse validateVendor(VendorRequest request) {
        List<String> errors = new ArrayList<>();


        int bulk = parseIntSafe(request.getProductBulk());
        if (bulk <= 500) {
            errors.add("Product bulk must be greater than 500 units.");
        }

        
        if (!googleSearchService.isBusinessAvailableOnline(request.getBusinessName())) {
            errors.add("Business name not found online via Google Search.");
        }

        
        if (request.getPreviousClients() == null || request.getPreviousClients().size() < 5) {
            errors.add("Must have at least 5 previous clients.");
        }

        
        if (!isTransactionHistoryValid(request.getTrasactionHistory(), bulk)) {
            errors.add("Transaction history is insufficient based on product bulk.");
        }

        
        if (!hasRatingAboveThree(request.getIndustryRatings())) {
            errors.add("Must have at least one industry rating above 3.");
        }

        
        VendorResponse response = new VendorResponse();
        response.setName(request.getName());
        response.setValidationErrors(errors);
        response.setValid(errors.isEmpty());

        return response;
    }

    private boolean hasRatingAboveThree(List<String> ratings) {
        if (ratings == null) return false;
        for (String r : ratings) {
            try {
                int val = Integer.parseInt(r.replaceAll("[^0-9]", ""));
                if (val > 3) return true;
            } catch (NumberFormatException ignored) {}
        }
        return false;
    }

    private boolean isTransactionHistoryValid(List<String> history, int bulk) {
        if (history == null || history.isEmpty()) return false;
        int sum = 0;
        for (String h : history) {
            try {
                sum += Integer.parseInt(h.trim());
            } catch (NumberFormatException ignored) {}
        }
        return sum >= (bulk * 20);
    }

    private int parseIntSafe(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (Exception e) {
            return 0;
        }
    }

    
}