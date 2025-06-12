package com.javaserver.javaserver.controllers;

import com.javaserver.javaserver.dtos.VendorRequestDTO.VendorRequest;
import com.javaserver.javaserver.dtos.VendorResponseDTO.VendorResponse;
import com.javaserver.javaserver.services.VendorValidationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class VendorValidationController {

    private final VendorValidationService validationService;

    @Autowired
    public VendorValidationController(VendorValidationService validationService) {
        this.validationService = validationService;
    }

    @PostMapping("/validate-vendor")
    public ResponseEntity<VendorResponse> validateVendor(@RequestBody VendorRequest request) {
        VendorResponse response = validationService.validateVendor(request);
        return ResponseEntity.ok(response);
    }
}
