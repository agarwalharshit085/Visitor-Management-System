package com.example.VisitorManagementSystem.dto;

import com.example.VisitorManagementSystem.enums.Role;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    @NotNull
    @Size(max = 255)
    private String name;
    @NotNull
    @Size(max = 255)
    private String email;

    @NotNull
    @Size(min = 10)
    private String phone;

    @NotNull
    private String idNumber;

    private Role role;

    private String flatNo;

    private AddressDto address;

}
