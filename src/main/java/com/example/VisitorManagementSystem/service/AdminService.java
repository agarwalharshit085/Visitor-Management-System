package com.example.VisitorManagementSystem.service;

import com.example.VisitorManagementSystem.dto.AddressDto;
import com.example.VisitorManagementSystem.dto.UserDto;
import com.example.VisitorManagementSystem.entity.Address;
import com.example.VisitorManagementSystem.entity.Flat;
import com.example.VisitorManagementSystem.entity.User;
import com.example.VisitorManagementSystem.enums.UserStatus;
import com.example.VisitorManagementSystem.repo.FlatRepo;
import com.example.VisitorManagementSystem.repo.UserRepo;
import com.example.VisitorManagementSystem.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@SuppressWarnings("ALL")
@Service
public class AdminService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private FlatRepo flatRepo;

    @Autowired
    private CommonUtil commonUtil;


    public Long createUser(UserDto userDto){

        AddressDto addressDto = userDto.getAddress();
        Address address = commonUtil.convertAddressDTOT(addressDto);
        Flat flat = null;
        if(userDto.getFlatNo() != null){
            flat = flatRepo.findByNumber(userDto.getFlatNo());
        }
        User user = User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .role(userDto.getRole())
                .idNumber(userDto.getIdNumber())
                .phone(userDto.getPhone())
                .flat(flat)
                .address(address)
                .status(UserStatus.ACTIVE)
                .build();
        user = userRepo.save(user);
        return user.getId();
    }
}
