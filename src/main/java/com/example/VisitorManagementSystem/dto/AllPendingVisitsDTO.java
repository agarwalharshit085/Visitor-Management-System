package com.example.VisitorManagementSystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@SuppressWarnings("ALL")
@Getter
@Setter
public class AllPendingVisitsDTO {

    private List<VisitDto> visits;

    private Long totalRows;

    private Integer totalPages;
}
