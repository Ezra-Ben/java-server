package com.javaserver.javaserver.dtos;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

public class VendorResponseDTO {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor

    public static class VendorResponse {
        private String name;
        private boolean valid;
        private List<String> validationErrors;
        
      
    }

}
