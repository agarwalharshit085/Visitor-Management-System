package com.example.VisitorManagementSystem.dto;

import lombok.*;

@SuppressWarnings("ALL")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDto {

    private String line1;

    private String line2;

    private String city;

    private String pinCode;
}
