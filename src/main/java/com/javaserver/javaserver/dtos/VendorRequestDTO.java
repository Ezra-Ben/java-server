package com.javaserver.javaserver.dtos;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

public class VendorRequestDTO {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VendorRequest {
        private String name;
        private String regNo;
        private String productBulk;
        private String businessName;
        private List<String> previousClients;
        private List<String> industryRatings;
        private List<String> trasactionHistory;
    }

}
