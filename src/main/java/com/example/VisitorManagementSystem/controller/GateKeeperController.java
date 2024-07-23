package com.example.VisitorManagementSystem.controller;

import com.example.VisitorManagementSystem.dto.VisitDto;
import com.example.VisitorManagementSystem.dto.VisitorDto;
import com.example.VisitorManagementSystem.service.GateKeeperService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gatekeeper")
public class GateKeeperController
{
    @Autowired
    private GateKeeperService gateKeeperService;

    @GetMapping("/getVisitor")
    ResponseEntity<VisitorDto> getVisitorByIdNumber(@RequestParam String idNumber){
        VisitorDto visitorDto = gateKeeperService.getVisitor(idNumber);
        if(visitorDto == null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(visitorDto);
    }

    @PostMapping("/createVisitor")
    ResponseEntity<Long> createVisitor(@RequestBody @Valid VisitorDto visitorDto){
        Long id = gateKeeperService.createVisitor(visitorDto);
        return ResponseEntity.ok(id);
    }

    @PostMapping("/createVisit")
    ResponseEntity<Long> createVisit(@RequestBody @Valid VisitDto visitDto){
        Long id = gateKeeperService.createVisit(visitDto);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/markEntry/{id}")
    ResponseEntity<String> markEntry(@PathVariable Long id){
        return ResponseEntity.ok(gateKeeperService.markEntry(id));
    }

    @PutMapping("/markExit/{id}")
    ResponseEntity<String> markExit(@PathVariable Long id) throws BadRequestException {
        return ResponseEntity.ok(gateKeeperService.markExit(id));
    }
}
