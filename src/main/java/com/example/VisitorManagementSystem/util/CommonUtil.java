package com.example.VisitorManagementSystem.util;

import com.example.VisitorManagementSystem.dto.AddressDto;
import com.example.VisitorManagementSystem.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class CommonUtil {

    public  Address convertAddressDTOT(AddressDto addressDto){
        return Address.builder()
                .line1(addressDto.getLine1())
                .line2(addressDto.getLine2())
                .city(addressDto.getCity())
                .pinCode(addressDto.getPinCode())
                .build();
    }
}
