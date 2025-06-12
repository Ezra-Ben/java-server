package com.javaserver.javaserver.services;

import com.javaserver.javaserver.dtos.VendorRequestDTO.VendorRequest;
import com.javaserver.javaserver.dtos.VendorResponseDTO.VendorResponse;;

public interface VendorValidationService {
    VendorResponse validateVendor(VendorRequest vendorRequest);

}
