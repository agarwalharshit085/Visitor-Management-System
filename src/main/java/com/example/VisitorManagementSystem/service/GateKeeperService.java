package com.example.VisitorManagementSystem.service;

import com.example.VisitorManagementSystem.dto.AddressDto;
import com.example.VisitorManagementSystem.dto.VisitDto;
import com.example.VisitorManagementSystem.dto.VisitorDto;
import com.example.VisitorManagementSystem.entity.Address;
import com.example.VisitorManagementSystem.entity.Flat;
import com.example.VisitorManagementSystem.entity.Visit;
import com.example.VisitorManagementSystem.entity.Visitor;
import com.example.VisitorManagementSystem.enums.visitStatus;
import com.example.VisitorManagementSystem.exceptions.NotFoundException;
import com.example.VisitorManagementSystem.repo.FlatRepo;
import com.example.VisitorManagementSystem.repo.VisitRepo;
import com.example.VisitorManagementSystem.repo.VisitorRepo;
import com.example.VisitorManagementSystem.util.CommonUtil;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;

@Service
public class GateKeeperService {

    @Autowired
    private VisitorRepo visitorRepo;

    @Autowired
    private CommonUtil commonUtil;

    @Autowired
    private FlatRepo flatRepo;

    @Autowired
    private VisitRepo visitRepo;

    public VisitorDto getVisitor(String idNumber){
        Visitor visitor = visitorRepo.findByIdNumber(idNumber);
        VisitorDto visitorDto = null;
        if(visitor != null){
            visitorDto = VisitorDto.builder()
                    .name(visitor.getName())
                    .email(visitor.getEmail())
                    .phone(visitor.getPhone())
                    .idNumber(visitor.getIdNumber())
                    .build();
        }
        return visitorDto;
    }

    public Long createVisitor(VisitorDto visitorDto){
        AddressDto addressDto = visitorDto.getAddress();
        Address address = commonUtil.convertAddressDTOT(addressDto);
        Visitor visitor = Visitor.builder()
                .address(address)
                .name(visitorDto.getName())
                .email(visitorDto.getEmail())
                .phone(visitorDto.getPhone())
                .idNumber(visitorDto.getIdNumber())
                .build();

        visitor = visitorRepo.save(visitor);
        return visitor.getId();
    }

    public Long createVisit(VisitDto visitDto){
        Flat flat = flatRepo.findByNumber(visitDto.getFlatNumber());
        Visitor visitor = visitorRepo.findByIdNumber(visitDto.getIdNumber());
        Visit visit = Visit.builder()
                .flat(flat)
                .imageUrl(visitDto.getUrlOfImage())
                .noOfPeople(visitDto.getNoOfPeople())
                .purpose(visitDto.getPurpose())
                .visitor(visitor)
                .status(visitStatus.WAITING)
                .build();
        visit = visitRepo.save(visit);
        return visit.getId();
    }

    @Transactional
    public String markEntry(Long id){
        Visit visit = visitRepo.findById(id).get();
        if(visit == null){

        }
        if(visit.getStatus().equals(visitStatus.APPROVED)){
            visit.setInTime(new Date());
            // visitRepo.save(visit); // without Transactional
        }
        else{

        }
        return "Done";
    }


    @Transactional
    public String markExit(Long id) throws BadRequestException {
        Visit visit = visitRepo.findById(id).get();
        if(visit == null){
            throw new NotFoundException("Visit not Found");
        }
        if(visit.getStatus().equals(visitStatus.APPROVED) && visit.getInTime() != null){
            visit.setOutTime(new Date());
            visit.setStatus(visitStatus.COMPLETED);
        }
        else{
            throw new BadRequestException("Invalid State Transition");
        }
        return "Done";
    }
}
