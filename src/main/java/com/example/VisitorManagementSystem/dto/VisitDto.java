package com.example.VisitorManagementSystem.dto;
import com.example.VisitorManagementSystem.enums.visitStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VisitDto {
    private visitStatus status;
    private Date inTime;
    private Date outTime;
    @NotNull
    @Size(max = 255)
    private String purpose;
    @Size(max = 255)
    private String urlOfImage;
    @NotNull
    private Integer noOfPeople;
    @NotNull
    private String idNumber;
    @NotNull
    private String flatNumber;
    private String visitorName;
    private String visitorPhone;
}
