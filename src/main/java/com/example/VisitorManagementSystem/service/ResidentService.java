package com.example.VisitorManagementSystem.service;

import com.example.VisitorManagementSystem.dto.AllPendingVisitsDTO;
import com.example.VisitorManagementSystem.dto.VisitDto;
import com.example.VisitorManagementSystem.entity.Flat;
import com.example.VisitorManagementSystem.entity.User;
import com.example.VisitorManagementSystem.entity.Visit;
import com.example.VisitorManagementSystem.entity.Visitor;
import com.example.VisitorManagementSystem.enums.VisitStatus;
import com.example.VisitorManagementSystem.exceptions.BadRequestException;
import com.example.VisitorManagementSystem.exceptions.NotFoundException;
import com.example.VisitorManagementSystem.repo.UserRepo;
import com.example.VisitorManagementSystem.repo.VisitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResidentService {

    @Autowired
    private VisitRepo visitRepo;

    @Autowired
    private UserRepo userRepo;



    @SuppressWarnings("SameReturnValue")
    public String updateVisit(Long id, VisitStatus visitStatus){
        if(visitStatus != VisitStatus.REJECTED && visitStatus != VisitStatus.APPROVED){
            throw new BadRequestException("Invalid state transition");
        }
        Visit visit = visitRepo.findById(id).get();
        if(visit == null){
            throw new NotFoundException("Visit not found");
        }
        if( VisitStatus.WAITING.equals(visit.getStatus())  /*visit.getStatus().equals(VisitStatus.WAITING)*/ ){
            visit.setStatus(visitStatus);
            visitRepo.save(visit);
        }
        else {
            throw new BadRequestException("Invalid state transition");
        }
        return "Done";
    }

    public List<VisitDto> getPendingVisits(Long userId){
        User user = userRepo.findById(userId).get();
        Flat flat = user.getFlat();
        List<Visit> visitList = visitRepo.findByStatusAndFlat(VisitStatus.WAITING, flat);
        List<VisitDto> visitDtoList = new ArrayList<>();
        for(Visit visit : visitList ){
            Visitor visitor = visit.getVisitor();
            VisitDto visitDto = VisitDto.builder()
                    .flatNumber(flat.getNumber())
                    .purpose(visit.getPurpose())
                    .noOfPeople(visit.getNoOfPeople())
                    .urlOfImage(visit.getImageUrl())
                    .visitorName(visitor.getName())
                    .visitorPhone(visitor.getPhone())
                    .status(visit.getStatus())
                    .idNumber(visitor.getIdNumber())
                    .build();
            visitDtoList.add(visitDto);
        }
        return visitDtoList;
    }

    public AllPendingVisitsDTO getPendingVisitByPage(Long userId, Integer pageNo, Integer pageSize){
        AllPendingVisitsDTO allPendingVisitsDTO = new AllPendingVisitsDTO();
        List<VisitDto> visitDtoList = new ArrayList<>();
        User user = userRepo.findById(userId).get();
        Flat flat = user.getFlat();

        Pageable pageable = Pageable.ofSize(pageSize)
                .withPage(pageNo);

        Page<Visit> visitPage = visitRepo.findByStatusAndFlat(VisitStatus.WAITING,flat,pageable);
        List<Visit> visitList = visitPage.stream().toList();
        for(Visit visit : visitList ){
            Visitor visitor = visit.getVisitor();
            VisitDto visitDto = VisitDto.builder()
                    .flatNumber(flat.getNumber())
                    .purpose(visit.getPurpose())
                    .noOfPeople(visit.getNoOfPeople())
                    .urlOfImage(visit.getImageUrl())
                    .visitorName(visitor.getName())
                    .visitorPhone(visitor.getPhone())
                    .status(visit.getStatus())
                    .idNumber(visitor.getIdNumber())
                    .build();
            visitDtoList.add(visitDto);
        }
        allPendingVisitsDTO.setVisits(visitDtoList);
        allPendingVisitsDTO.setTotalPages(visitPage.getTotalPages());
        allPendingVisitsDTO.setTotalRows(visitPage.getTotalElements());
        return allPendingVisitsDTO;
    }
}