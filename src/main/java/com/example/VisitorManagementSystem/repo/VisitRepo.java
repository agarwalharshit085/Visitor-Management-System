package com.example.VisitorManagementSystem.repo;

import com.example.VisitorManagementSystem.entity.Flat;
import com.example.VisitorManagementSystem.entity.Visit;
import com.example.VisitorManagementSystem.enums.VisitStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VisitRepo extends JpaRepository<Visit, Long> {

    List<Visit> findByStatusAndFlat(VisitStatus status, Flat flat);

    Page<Visit> findByStatusAndFlat(VisitStatus status, Flat flat, Pageable pageable);

    List<Visit> findByStatusAndCreatedDateLessThanEqual(VisitStatus visitStatus, Date date);
}
