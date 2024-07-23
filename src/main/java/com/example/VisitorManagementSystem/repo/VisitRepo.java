package com.example.VisitorManagementSystem.repo;

import com.example.VisitorManagementSystem.entity.Flat;
import com.example.VisitorManagementSystem.entity.Visit;
import com.example.VisitorManagementSystem.enums.visitStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitRepo extends JpaRepository<Visit, Long> {

    List<Visit> findByStatusAndFlat(visitStatus status, Flat flat);
}
